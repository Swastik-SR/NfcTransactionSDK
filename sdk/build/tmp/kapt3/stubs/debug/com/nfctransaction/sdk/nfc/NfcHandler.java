package com.nfctransaction.sdk.nfc;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0016\u001a\u00020\u0017J\u001b\u0010\u0018\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0019\u001a\u00020\u001aH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001bJ\u0006\u0010\u001c\u001a\u00020\u001dJ\u001a\u0010\u001e\u001a\u00020\u001d2\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001d0 J\u0006\u0010!\u001a\u00020\u001dJ\u0006\u0010\"\u001a\u00020\u001dJ!\u0010#\u001a\u00020\u00172\u0006\u0010$\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u001aH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010%J!\u0010&\u001a\u00020\u00172\u0006\u0010\'\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u001aH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010%R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\nR\u001d\u0010\u000b\u001a\u0004\u0018\u00010\f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000eR\u001b\u0010\u0011\u001a\u00020\u00128BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0015\u0010\u0010\u001a\u0004\b\u0013\u0010\u0014\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006("}, d2 = {"Lcom/nfctransaction/sdk/nfc/NfcHandler;", "", "activity", "Landroid/app/Activity;", "(Landroid/app/Activity;)V", "TAG", "", "ndefFilters", "", "Landroid/content/IntentFilter;", "[Landroid/content/IntentFilter;", "nfcAdapter", "Landroid/nfc/NfcAdapter;", "getNfcAdapter", "()Landroid/nfc/NfcAdapter;", "nfcAdapter$delegate", "Lkotlin/Lazy;", "pendingIntent", "Landroid/app/PendingIntent;", "getPendingIntent", "()Landroid/app/PendingIntent;", "pendingIntent$delegate", "isNfcAvailable", "", "readDeviceId", "tag", "Landroid/nfc/Tag;", "(Landroid/nfc/Tag;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "startListening", "", "startNfcDiscovery", "onTagDiscovered", "Lkotlin/Function1;", "stopListening", "stopNfcDiscovery", "writeDeviceId", "deviceId", "(Ljava/lang/String;Landroid/nfc/Tag;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeTransaction", "transactionString", "sdk_debug"})
public final class NfcHandler {
    @org.jetbrains.annotations.NotNull
    private final android.app.Activity activity = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String TAG = "NfcHandler";
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy nfcAdapter$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy pendingIntent$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final android.content.IntentFilter[] ndefFilters = null;
    
    public NfcHandler(@org.jetbrains.annotations.NotNull
    android.app.Activity activity) {
        super();
    }
    
    private final android.nfc.NfcAdapter getNfcAdapter() {
        return null;
    }
    
    private final android.app.PendingIntent getPendingIntent() {
        return null;
    }
    
    public final void startListening() {
    }
    
    public final void stopListening() {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object readDeviceId(@org.jetbrains.annotations.NotNull
    android.nfc.Tag tag, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object writeDeviceId(@org.jetbrains.annotations.NotNull
    java.lang.String deviceId, @org.jetbrains.annotations.NotNull
    android.nfc.Tag tag, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object writeTransaction(@org.jetbrains.annotations.NotNull
    java.lang.String transactionString, @org.jetbrains.annotations.NotNull
    android.nfc.Tag tag, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    public final boolean isNfcAvailable() {
        return false;
    }
    
    public final void startNfcDiscovery(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super android.nfc.Tag, kotlin.Unit> onTagDiscovered) {
    }
    
    public final void stopNfcDiscovery() {
    }
}