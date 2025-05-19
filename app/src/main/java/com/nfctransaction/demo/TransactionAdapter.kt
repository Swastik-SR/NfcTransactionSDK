package com.nfctransaction.demo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nfctransaction.demo.databinding.ItemTransactionBinding
import com.nfctransaction.sdk.data.Transaction
import java.time.format.DateTimeFormatter

class TransactionAdapter : ListAdapter<Transaction, TransactionAdapter.TransactionViewHolder>(TransactionDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val binding = ItemTransactionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TransactionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class TransactionViewHolder(
        private val binding: ItemTransactionBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm")

        fun bind(transaction: Transaction) {
            binding.apply {
                transactionIdText.text = "ID: ${transaction.id.take(8)}..."
                peerDeviceIdText.text = "To: ${transaction.peerDeviceId}"
                amountText.text = String.format("$%.2f", transaction.amount)
                timestampText.text = transaction.timestamp.format(formatter)
                directionText.text = transaction.direction.name
            }
        }
    }

    private class TransactionDiffCallback : DiffUtil.ItemCallback<Transaction>() {
        override fun areItemsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
            return oldItem == newItem
        }
    }
} 