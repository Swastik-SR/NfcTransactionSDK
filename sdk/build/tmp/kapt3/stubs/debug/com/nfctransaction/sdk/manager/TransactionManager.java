package com.nfctransaction.sdk.manager;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0012\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\rJ\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0011\u001a\u00020\u0006J\u0019\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0016"}, d2 = {"Lcom/nfctransaction/sdk/manager/TransactionManager;", "", "transactionDao", "Lcom/nfctransaction/sdk/data/TransactionDao;", "(Lcom/nfctransaction/sdk/data/TransactionDao;)V", "createTransactionString", "", "peerDeviceId", "amount", "", "direction", "Lcom/nfctransaction/sdk/data/TransactionDirection;", "getAllTransactions", "Landroidx/lifecycle/LiveData;", "", "Lcom/nfctransaction/sdk/data/Transaction;", "parseTransactionString", "transactionString", "saveTransaction", "", "transaction", "(Lcom/nfctransaction/sdk/data/Transaction;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sdk_debug"})
public final class TransactionManager {
    @org.jetbrains.annotations.NotNull
    private final com.nfctransaction.sdk.data.TransactionDao transactionDao = null;
    
    public TransactionManager(@org.jetbrains.annotations.NotNull
    com.nfctransaction.sdk.data.TransactionDao transactionDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String createTransactionString(@org.jetbrains.annotations.NotNull
    java.lang.String peerDeviceId, double amount, @org.jetbrains.annotations.NotNull
    com.nfctransaction.sdk.data.TransactionDirection direction) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.nfctransaction.sdk.data.Transaction parseTransactionString(@org.jetbrains.annotations.NotNull
    java.lang.String transactionString) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object saveTransaction(@org.jetbrains.annotations.NotNull
    com.nfctransaction.sdk.data.Transaction transaction, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.nfctransaction.sdk.data.Transaction>> getAllTransactions() {
        return null;
    }
}