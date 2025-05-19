package com.nfctransaction.sdk;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0005\u0018\u0000  2\u00020\u0001:\u0001 B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\rJ\u0006\u0010\u0010\u001a\u00020\u0004J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014J\u000e\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\u0019\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u001cH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001dJ\u000e\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010\u001f\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u0019R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006!"}, d2 = {"Lcom/nfctransaction/sdk/NfcTransactionSDK;", "", "()V", "TAG", "", "context", "Landroid/content/Context;", "deviceId", "nfcHandler", "Lcom/nfctransaction/sdk/nfc/NfcHandler;", "transactionManager", "Lcom/nfctransaction/sdk/manager/TransactionManager;", "getAllTransactions", "Landroidx/lifecycle/LiveData;", "", "Lcom/nfctransaction/sdk/data/Transaction;", "getDeviceId", "handleNfcIntent", "", "intent", "Landroid/content/Intent;", "initialize", "isNfcAvailable", "", "activity", "Landroid/app/Activity;", "sendTransaction", "amount", "", "(DLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "startListening", "stopListening", "Companion", "sdk_debug"})
public final class NfcTransactionSDK {
    @org.jetbrains.annotations.NotNull
    private final java.lang.String TAG = "NfcTransactionSDK";
    private android.content.Context context;
    private com.nfctransaction.sdk.manager.TransactionManager transactionManager;
    @org.jetbrains.annotations.Nullable
    private com.nfctransaction.sdk.nfc.NfcHandler nfcHandler;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String deviceId = null;
    @kotlin.jvm.Volatile
    @org.jetbrains.annotations.Nullable
    private static volatile com.nfctransaction.sdk.NfcTransactionSDK instance;
    @org.jetbrains.annotations.NotNull
    public static final com.nfctransaction.sdk.NfcTransactionSDK.Companion Companion = null;
    
    private NfcTransactionSDK() {
        super();
    }
    
    public final void initialize(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
    }
    
    public final void startListening(@org.jetbrains.annotations.NotNull
    android.app.Activity activity) {
    }
    
    public final void stopListening(@org.jetbrains.annotations.NotNull
    android.app.Activity activity) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object sendTransaction(double amount, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    public final void handleNfcIntent(@org.jetbrains.annotations.NotNull
    android.content.Intent intent) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.nfctransaction.sdk.data.Transaction>> getAllTransactions() {
        return null;
    }
    
    public final boolean isNfcAvailable(@org.jetbrains.annotations.NotNull
    android.app.Activity activity) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getDeviceId() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0004R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/nfctransaction/sdk/NfcTransactionSDK$Companion;", "", "()V", "instance", "Lcom/nfctransaction/sdk/NfcTransactionSDK;", "getInstance", "sdk_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.nfctransaction.sdk.NfcTransactionSDK getInstance() {
            return null;
        }
    }
}