package com.nfctransaction.sdk.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TransactionDao {
    @Query("SELECT * FROM transactions ORDER BY timestamp DESC")
    fun getAllTransactions(): LiveData<List<Transaction>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: Transaction)

    @Query("SELECT * FROM transactions WHERE synced = 0")
    suspend fun getUnsyncedTransactions(): List<Transaction>

    @Query("UPDATE transactions SET synced = 1 WHERE id = :transactionId")
    suspend fun markAsSynced(transactionId: String)
} 