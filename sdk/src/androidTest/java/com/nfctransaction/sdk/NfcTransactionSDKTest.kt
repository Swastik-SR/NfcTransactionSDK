package com.nfctransaction.sdk

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nfctransaction.sdk.data.Transaction
import com.nfctransaction.sdk.data.TransactionDirection
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*
import com.nfctransaction.sdk.nfc.NfcUtils
import android.util.Log

@RunWith(AndroidJUnit4::class)
class NfcTransactionSDKTest {
    private lateinit var sdk: NfcTransactionSDK
    private lateinit var context: Context

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
        sdk = NfcTransactionSDK.getInstance()
        sdk.initialize(context)
    }

    @Test
    fun testTransactionCreation() {
        val peerDeviceId = "testDevice123"
        val amount = 50.0

        // Create a transaction
        val transaction = Transaction(
            id = "test123",
            peerDeviceId = peerDeviceId,
            amount = amount,
            timestamp = java.time.LocalDateTime.now(),
            direction = TransactionDirection.SENT
        )

        // Verify transaction properties
        assertEquals(peerDeviceId, transaction.peerDeviceId)
        assertEquals(amount, transaction.amount, 0.0)
        assertEquals(TransactionDirection.SENT, transaction.direction)
    }

    @Test
    fun testTransactionStringFormat() {
        val peerDeviceId = "testDevice123"
        val amount = 50.0
        val direction = TransactionDirection.SENT

        // Create transaction string
        val transactionString = "$peerDeviceId|$amount|$direction"

        // Verify format
        val parts = transactionString.split("|")
        assertEquals(3, parts.size)
        assertEquals(peerDeviceId, parts[0])
        assertEquals(amount.toString(), parts[1])
        assertEquals(direction.name, parts[2])
    }

    @Test
    fun testDatabaseOperations() = runBlocking {
        // Create test transaction
        val transaction = Transaction(
            id = "test123",
            peerDeviceId = "testDevice123",
            amount = 50.0,
            timestamp = java.time.LocalDateTime.now(),
            direction = TransactionDirection.SENT
        )

        // Get all transactions
        val transactions = sdk.getAllTransactions()
        assertNotNull(transactions)
    }

    @Test
    fun testDeviceIdGeneration() {
        // Get device ID
        val deviceId = sdk.getDeviceId()
        
        // Verify device ID format (UUID format)
        assertNotNull(deviceId)
        assertTrue(deviceId.matches(Regex("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}")))
    }

    @Test
    fun testNfcUtilsDeviceIdMessage() {
        val deviceId = "test-device-id-123"
        
        // Create device ID message
        val message = NfcUtils.createDeviceIdMessage(deviceId)
        
        // Parse device ID message
        val parsedDeviceId = NfcUtils.parseDeviceIdMessage(message)
        
        // Verify device ID
        assertNotNull(parsedDeviceId)
        assertEquals(deviceId, parsedDeviceId)
    }

    @Test
    fun testNfcUtilsTransactionMessage() {
        val transactionString = "peer123|50.0|SENT"
        
        // Create transaction message
        val message = NfcUtils.createTransactionMessage(transactionString)
        
        // Parse transaction message
        val parsedTransactionString = NfcUtils.parseTransactionMessage(message)
        
        // Verify transaction string
        assertNotNull(parsedTransactionString)
        assertEquals(transactionString, parsedTransactionString)
    }

    @Test
    fun testSdkRunningStatus() {
        // Initialize SDK with application context
        val sdk = NfcTransactionSDK.getInstance()
        sdk.initialize(context)
        assertNotNull("SDK instance should not be null", sdk)
        
        // Check if device ID is generated
        val deviceId = sdk.getDeviceId()
        assertNotNull("Device ID should not be null", deviceId)
        assertTrue("Device ID should be in UUID format", 
            deviceId.matches(Regex("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}")))
        
        // Check if we can get transactions
        val transactions = sdk.getAllTransactions()
        assertNotNull("Transactions LiveData should not be null", transactions)
        
        // Log SDK status
        Log.d("NfcTransactionSDKTest", "SDK initialized successfully")
        Log.d("NfcTransactionSDKTest", "Device ID: $deviceId")
    }
} 