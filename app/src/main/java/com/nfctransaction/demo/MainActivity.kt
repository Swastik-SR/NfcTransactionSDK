package com.nfctransaction.demo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.nfctransaction.demo.databinding.ActivityMainBinding
import com.nfctransaction.sdk.NfcTransactionSDK
import com.nfctransaction.sdk.data.Transaction
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var transactionAdapter: TransactionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize SDK
        NfcTransactionSDK.getInstance().initialize(this)

        // Setup RecyclerView
        transactionAdapter = TransactionAdapter()
        binding.transactionsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = transactionAdapter
        }

        // Setup send button
        binding.sendButton.setOnClickListener {
            val amountStr = binding.amountInput.text.toString()

            if (amountStr.isBlank()) {
                Toast.makeText(this, "Please enter an amount", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Check NFC availability
            val sdk = NfcTransactionSDK.getInstance()
            if (!sdk.isNfcAvailable(this)) {
                Toast.makeText(this, "NFC is not available or enabled on this device", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            try {
                val amount = amountStr.toDouble()
                lifecycleScope.launch {
                    try {
                        sdk.sendTransaction(amount)
                        Toast.makeText(this@MainActivity, "Transaction sent! Now tap phones together.", Toast.LENGTH_LONG).show()
                    } catch (e: Exception) {
                        Toast.makeText(this@MainActivity, "Failed to send transaction: ${e.message}", Toast.LENGTH_LONG).show()
                        Log.e("MainActivity", "Transaction send error", e)
                    }
                }
                binding.amountInput.text?.clear()
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Invalid amount", Toast.LENGTH_SHORT).show()
            }
        }

        // Observe transactions
        NfcTransactionSDK.getInstance().getAllTransactions().observe(this) { transactions ->
            Log.d("MainActivity", "Transaction history updated: ${transactions.size} transactions")
            Toast.makeText(this, "Transaction history updated: ${transactions.size} transactions", Toast.LENGTH_SHORT).show()
            transactionAdapter.submitList(transactions)
        }
    }

    override fun onResume() {
        super.onResume()
        NfcTransactionSDK.getInstance().startListening(this)
    }

    override fun onPause() {
        super.onPause()
        NfcTransactionSDK.getInstance().stopListening(this)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        NfcTransactionSDK.getInstance().handleNfcIntent(intent)
    }
} 