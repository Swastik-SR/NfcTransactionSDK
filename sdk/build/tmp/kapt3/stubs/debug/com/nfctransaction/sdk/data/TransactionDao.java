package com.nfctransaction.sdk.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H\'J\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J\u0019\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u0019\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000eH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000f\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0010"}, d2 = {"Lcom/nfctransaction/sdk/data/TransactionDao;", "", "getAllTransactions", "Landroidx/lifecycle/LiveData;", "", "Lcom/nfctransaction/sdk/data/Transaction;", "getUnsyncedTransactions", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertTransaction", "", "transaction", "(Lcom/nfctransaction/sdk/data/Transaction;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "markAsSynced", "transactionId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sdk_debug"})
@androidx.room.Dao
public abstract interface TransactionDao {
    
    @androidx.room.Query(value = "SELECT * FROM transactions ORDER BY timestamp DESC")
    @org.jetbrains.annotations.NotNull
    public abstract androidx.lifecycle.LiveData<java.util.List<com.nfctransaction.sdk.data.Transaction>> getAllTransactions();
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertTransaction(@org.jetbrains.annotations.NotNull
    com.nfctransaction.sdk.data.Transaction transaction, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM transactions WHERE synced = 0")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getUnsyncedTransactions(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.nfctransaction.sdk.data.Transaction>> $completion);
    
    @androidx.room.Query(value = "UPDATE transactions SET synced = 1 WHERE id = :transactionId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object markAsSynced(@org.jetbrains.annotations.NotNull
    java.lang.String transactionId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}