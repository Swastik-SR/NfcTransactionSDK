package com.nfctransaction.sdk.manager

import com.nfctransaction.sdk.data.Transaction
import com.nfctransaction.sdk.data.TransactionDao
import com.nfctransaction.sdk.data.TransactionDirection
import java.time.LocalDateTime
import java.util.UUID

class TransactionManager(private val transactionDao: TransactionDao) {

    fun createTransactionString(
        peerDeviceId: String,
        amount: Double,
        direction: TransactionDirection
    ): String {
        val transactionId = UUID.randomUUID().toString()
        val timestamp = LocalDateTime.now()
        return "$transactionId|$peerDeviceId|$amount|$timestamp|$direction"
    }

    fun parseTransactionString(transactionString: String): Transaction? {
        return try {
            val parts = transactionString.split("|")
            if (parts.size != 5) return null

            Transaction(
                id = parts[0],
                peerDeviceId = parts[1],
                amount = parts[2].toDouble(),
                timestamp = LocalDateTime.parse(parts[3]),
                direction = TransactionDirection.valueOf(parts[4])
            )
        } catch (e: Exception) {
            null
        }
    }

    suspend fun saveTransaction(transaction: Transaction) {
        transactionDao.insertTransaction(transaction)
    }

    fun getAllTransactions() = transactionDao.getAllTransactions()
} 