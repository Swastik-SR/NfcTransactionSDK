package com.nfctransaction.sdk.nfc

import android.nfc.NdefMessage
import android.nfc.NdefRecord
import java.nio.charset.StandardCharsets

object NfcUtils {
    private const val TAG = "NfcUtils"
    private const val DEVICE_ID_MIME_TYPE = "application/com.nfctransaction.deviceid"
    private const val TRANSACTION_MIME_TYPE = "application/com.nfctransaction.transaction"

    fun createDeviceIdMessage(deviceId: String): NdefMessage {
        val mimeRecord = NdefRecord.createMime(
            DEVICE_ID_MIME_TYPE,
            deviceId.toByteArray(StandardCharsets.UTF_8)
        )
        return NdefMessage(arrayOf(mimeRecord))
    }

    fun createTransactionMessage(transactionString: String): NdefMessage {
        val mimeRecord = NdefRecord.createMime(
            TRANSACTION_MIME_TYPE,
            transactionString.toByteArray(StandardCharsets.UTF_8)
        )
        return NdefMessage(arrayOf(mimeRecord))
    }

    fun parseDeviceIdMessage(message: NdefMessage): String? {
        return try {
            val record = message.records.firstOrNull()
            if (record != null && record.toMimeType() == DEVICE_ID_MIME_TYPE) {
                String(record.payload, StandardCharsets.UTF_8)
            } else null
        } catch (e: Exception) {
            null
        }
    }

    fun parseTransactionMessage(message: NdefMessage): String? {
        return try {
            val record = message.records.firstOrNull()
            if (record != null && record.toMimeType() == TRANSACTION_MIME_TYPE) {
                String(record.payload, StandardCharsets.UTF_8)
            } else null
        } catch (e: Exception) {
            null
        }
    }
} 