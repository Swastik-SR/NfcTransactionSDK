package com.nfctransaction.sdk.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey
    val id: String,
    val peerDeviceId: String,
    val amount: Double,
    val timestamp: LocalDateTime,
    val direction: TransactionDirection,
    val synced: Boolean = false
)

enum class TransactionDirection {
    SENT,
    RECEIVED
} 