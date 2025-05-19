package com.nfctransaction.sdk.nfc;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004J\u000e\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u0004J\u0010\u0010\f\u001a\u0004\u0018\u00010\u00042\u0006\u0010\r\u001a\u00020\bJ\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u00042\u0006\u0010\r\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/nfctransaction/sdk/nfc/NfcUtils;", "", "()V", "DEVICE_ID_MIME_TYPE", "", "TAG", "TRANSACTION_MIME_TYPE", "createDeviceIdMessage", "Landroid/nfc/NdefMessage;", "deviceId", "createTransactionMessage", "transactionString", "parseDeviceIdMessage", "message", "parseTransactionMessage", "sdk_debug"})
public final class NfcUtils {
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String TAG = "NfcUtils";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String DEVICE_ID_MIME_TYPE = "application/com.nfctransaction.deviceid";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String TRANSACTION_MIME_TYPE = "application/com.nfctransaction.transaction";
    @org.jetbrains.annotations.NotNull
    public static final com.nfctransaction.sdk.nfc.NfcUtils INSTANCE = null;
    
    private NfcUtils() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final android.nfc.NdefMessage createDeviceIdMessage(@org.jetbrains.annotations.NotNull
    java.lang.String deviceId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final android.nfc.NdefMessage createTransactionMessage(@org.jetbrains.annotations.NotNull
    java.lang.String transactionString) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String parseDeviceIdMessage(@org.jetbrains.annotations.NotNull
    android.nfc.NdefMessage message) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String parseTransactionMessage(@org.jetbrains.annotations.NotNull
    android.nfc.NdefMessage message) {
        return null;
    }
}