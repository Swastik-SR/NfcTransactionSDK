# NfcTransactionSDK

A simple Android SDK for handling offline NFC transactions between Android devices using NDEF messages.

## Features

- Simple NFC-based transaction system using NDEF messages
- Local storage of transactions using Room Database
- Clean and easy-to-use API
- Support for both sending and receiving transactions
- Transaction history tracking

## Requirements

- Android API level 21 or higher
- NFC hardware support
- AndroidX libraries

## Installation

Add the following to your app's `build.gradle`:

```gradle
dependencies {
    implementation project(':sdk')
}
```

## Usage

1. Initialize the SDK in your Application class or MainActivity:

```kotlin
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        NfcTransactionSDK.getInstance().initialize(this)
    }
}
```

2. Start listening for NFC transactions in your Activity:

```kotlin
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Start listening for NFC transactions
        NfcTransactionSDK.getInstance().startListening(this)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        // Handle incoming NFC transactions
        NfcTransactionSDK.getInstance().handleNfcIntent(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        // Stop listening for NFC transactions
        NfcTransactionSDK.getInstance().stopListening(this)
    }
}
```

3. Send a transaction:

```kotlin
// Send a transaction to another device
NfcTransactionSDK.getInstance().sendTransaction(
    activity = this,
    peerDeviceId = "device123",
    amount = 50.0
)
```

4. Observe transaction history:

```kotlin
NfcTransactionSDK.getInstance().getAllTransactions().observe(this) { transactions ->
    // Update UI with transaction list
    transactions.forEach { transaction ->
        // Handle transaction
    }
}
```

## Transaction Format

Transactions are stored in the following format:

```
txnId|peerDeviceId|amount|timestamp|direction
```

Example:

```
550e8400-e29b-41d4-a716-446655440000|device123|50.0|2024-03-18T10:30:00|SENT
```

## Permissions

Add the following permissions to your AndroidManifest.xml:

```xml
<uses-permission android:name="android.permission.NFC" />
<uses-feature android:name="android.hardware.nfc" android:required="true" />
```

## Notes

- The SDK handles NFC communication using NDEF messages
- All transactions are stored locally using Room Database
- The SDK is designed to work offline
- No encryption is implemented in this version
- Server sync functionality is not implemented in this version
