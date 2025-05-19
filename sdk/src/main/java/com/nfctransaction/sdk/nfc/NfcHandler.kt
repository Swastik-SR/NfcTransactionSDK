package com.nfctransaction.sdk.nfc

import android.app.Activity
import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NfcAdapter
import android.nfc.NfcManager
import android.nfc.Tag
import android.nfc.tech.Ndef
import android.os.Build
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NfcHandler(private val activity: Activity) {
    private val TAG = "NfcHandler"
    private val nfcAdapter: NfcAdapter? by lazy {
        val nfcManager = activity.getSystemService(Activity.NFC_SERVICE) as NfcManager
        nfcManager.defaultAdapter
    }

    private val pendingIntent: PendingIntent by lazy {
        val intent = Intent(activity, activity.javaClass).apply {
            addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        }
        PendingIntent.getActivity(
            activity,
            0,
            intent,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
            } else {
                PendingIntent.FLAG_UPDATE_CURRENT
            }
        )
    }

    private val ndefFilters = arrayOf(
        IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED).apply {
            addDataType("application/com.nfctransaction.sdk")
            addDataType("application/com.nfctransaction.deviceid")
        }
    )

    fun startListening() {
        try {
            // Disable reader mode if it was active
            nfcAdapter?.disableReaderMode(activity)
            
            // Enable foreground dispatch for receiving
            nfcAdapter?.enableForegroundDispatch(activity, pendingIntent, ndefFilters, null)
            Log.d(TAG, "Started NFC foreground dispatch for receiving")
        } catch (e: Exception) {
            Log.e(TAG, "Error starting NFC foreground dispatch", e)
        }
    }

    fun stopListening() {
        try {
            nfcAdapter?.disableForegroundDispatch(activity)
            Log.d(TAG, "Stopped NFC foreground dispatch")
        } catch (e: Exception) {
            Log.e(TAG, "Error stopping NFC foreground dispatch", e)
        }
    }

    suspend fun readDeviceId(tag: Tag): String? = withContext(Dispatchers.IO) {
        try {
            Log.d(TAG, "Attempting to read device ID from NFC tag")
            val ndef = Ndef.get(tag)
            if (ndef != null) {
                ndef.connect()
                val message = ndef.cachedNdefMessage
                val deviceId = NfcUtils.parseDeviceIdMessage(message)
                ndef.close()
                if (deviceId != null) {
                    Log.d(TAG, "Successfully read device ID: $deviceId")
                } else {
                    Log.e(TAG, "No device ID found in NFC tag")
                }
                deviceId
            } else {
                Log.e(TAG, "Tag is not NDEF formatted")
                null
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error reading device ID from NFC tag", e)
            null
        }
    }

    suspend fun writeDeviceId(deviceId: String, tag: Tag): Boolean = withContext(Dispatchers.IO) {
        try {
            Log.d(TAG, "Attempting to write device ID to NFC tag")
            val ndef = Ndef.get(tag)
            if (ndef != null) {
                ndef.connect()
                val message = NfcUtils.createDeviceIdMessage(deviceId)
                if (ndef.maxSize < message.byteArrayLength) {
                    Log.e(TAG, "NFC tag too small for device ID")
                    return@withContext false
                }
                ndef.writeNdefMessage(message)
                ndef.close()
                Log.d(TAG, "Successfully wrote device ID to NFC tag")
                true
            } else {
                Log.e(TAG, "Tag is not NDEF formatted")
                false
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error writing device ID to NFC tag", e)
            false
        }
    }

    suspend fun writeTransaction(transactionString: String, tag: Tag): Boolean = withContext(Dispatchers.IO) {
        try {
            Log.d(TAG, "Attempting to write transaction to NFC tag")
            val ndef = Ndef.get(tag)
            if (ndef != null) {
                ndef.connect()
                val message = NfcUtils.createTransactionMessage(transactionString)
                if (ndef.maxSize < message.byteArrayLength) {
                    Log.e(TAG, "NFC tag too small for transaction data")
                    return@withContext false
                }
                ndef.writeNdefMessage(message)
                ndef.close()
                Log.d(TAG, "Successfully wrote transaction to NFC tag")
                true
            } else {
                Log.e(TAG, "Tag is not NDEF formatted")
                false
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error writing to NFC tag", e)
            false
        }
    }

    fun isNfcAvailable(): Boolean = nfcAdapter?.isEnabled == true

    fun startNfcDiscovery(onTagDiscovered: (Tag) -> Unit) {
        try {
            // Disable foreground dispatch if it was active
            nfcAdapter?.disableForegroundDispatch(activity)
            
            // Enable reader mode for sending
            Log.d(TAG, "Starting NFC reader mode for sending")
            nfcAdapter?.enableReaderMode(
                activity,
                { tag -> 
                    Log.d(TAG, "NFC tag discovered")
                    onTagDiscovered(tag)
                },
                NfcAdapter.FLAG_READER_NFC_A or NfcAdapter.FLAG_READER_NFC_B or NfcAdapter.FLAG_READER_NFC_F,
                null
            )
        } catch (e: Exception) {
            Log.e(TAG, "Error starting NFC reader mode", e)
        }
    }

    fun stopNfcDiscovery() {
        try {
            Log.d(TAG, "Stopping NFC reader mode")
            nfcAdapter?.disableReaderMode(activity)
        } catch (e: Exception) {
            Log.e(TAG, "Error stopping NFC reader mode", e)
        }
    }
} 