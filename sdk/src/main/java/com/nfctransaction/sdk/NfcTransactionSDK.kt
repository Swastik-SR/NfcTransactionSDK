package com.nfctransaction.sdk

import android.app.Activity
import android.content.Context
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.Ndef
import android.util.Log
import androidx.lifecycle.LiveData
import com.nfctransaction.sdk.data.Transaction
import com.nfctransaction.sdk.data.TransactionDatabase
import com.nfctransaction.sdk.data.TransactionDirection
import com.nfctransaction.sdk.manager.TransactionManager
import com.nfctransaction.sdk.nfc.NfcHandler
import com.nfctransaction.sdk.nfc.NfcUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.UUID

class NfcTransactionSDK private constructor() {
    private val TAG = "NfcTransactionSDK"
    private lateinit var context: Context
    private lateinit var transactionManager: TransactionManager
    private var nfcHandler: NfcHandler? = null
    private val deviceId = UUID.randomUUID().toString() // Unique device ID

    companion object {
        @Volatile
        private var instance: NfcTransactionSDK? = null

        fun getInstance(): NfcTransactionSDK {
            return instance ?: synchronized(this) {
                instance ?: NfcTransactionSDK().also { instance = it }
            }
        }
    }

    fun initialize(context: Context) {
        this.context = context.applicationContext
        val database = TransactionDatabase.getDatabase(context)
        transactionManager = TransactionManager(database.transactionDao())
    }

    fun startListening(activity: Activity) {
        Log.d(TAG, "Starting NFC listening mode")
        nfcHandler = NfcHandler(activity).also { 
            it.startListening()
        }
    }

    fun stopListening(activity: Activity) {
        Log.d(TAG, "Stopping NFC listening mode")
        nfcHandler?.stopListening()
        nfcHandler = null
    }

    suspend fun sendTransaction(amount: Double) {
        val handler = nfcHandler ?: throw IllegalStateException("NFC handler not initialized. Call startListening first.")
        try {
            handler.startNfcDiscovery { tag ->
                CoroutineScope(Dispatchers.IO).launch {
                    // Read peer's device ID
                    val peerDeviceId = handler.readDeviceId(tag)
                    if (peerDeviceId != null) {
                        // Write our device ID
                        handler.writeDeviceId(deviceId, tag)
                        // Create and save transaction
                        val transaction = Transaction(
                            id = UUID.randomUUID().toString(),
                            peerDeviceId = peerDeviceId,
                            amount = amount,
                            timestamp = LocalDateTime.now(),
                            direction = TransactionDirection.SENT
                        )
                        transactionManager.saveTransaction(transaction)
                        // Write transaction to NFC tag
                        val transactionString = transaction.toString()
                        handler.writeTransaction(transactionString, tag)
                    }
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error sending transaction", e)
            throw e
        }
    }

    fun handleNfcIntent(intent: android.content.Intent) {
        val handler = nfcHandler ?: return
        
        if (NfcAdapter.ACTION_NDEF_DISCOVERED == intent.action) {
            Log.d(TAG, "Received NDEF discovered intent")
            val tag: Tag? = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG)
            if (tag == null) {
                Log.e(TAG, "No NFC tag found in intent")
                return
            }
            
            val ndef = Ndef.get(tag)
            val ndefMessage = ndef.cachedNdefMessage ?: return
            
            // First check if this is a device ID message
            val deviceIdMessage = NfcUtils.parseDeviceIdMessage(ndefMessage)
            if (deviceIdMessage != null) {
                Log.d(TAG, "Received device ID: $deviceIdMessage")
                // Write our device ID back
                CoroutineScope(Dispatchers.IO).launch {
                    handler.writeDeviceId(deviceId, tag)
                }
                return
            }

            // If not a device ID message, try parsing as transaction
            val transactionString = NfcUtils.parseTransactionMessage(ndefMessage)
            transactionString?.let { string ->
                Log.d(TAG, "Parsed transaction string from NFC tag")
                val transaction = transactionManager.parseTransactionString(string)
                transaction?.let { txn ->
                    // Create a new transaction with RECEIVED direction
                    val receivedTransaction = txn.copy(
                        direction = TransactionDirection.RECEIVED,
                        timestamp = LocalDateTime.now()
                    )
                    CoroutineScope(Dispatchers.IO).launch {
                        transactionManager.saveTransaction(receivedTransaction)
                        Log.d(TAG, "Saved received transaction")
                    }
                }
            }
        }
    }

    fun getAllTransactions(): LiveData<List<Transaction>> {
        return transactionManager.getAllTransactions()
    }

    fun isNfcAvailable(activity: Activity): Boolean {
        return NfcHandler(activity).isNfcAvailable()
    }

    fun getDeviceId(): String = deviceId
} 