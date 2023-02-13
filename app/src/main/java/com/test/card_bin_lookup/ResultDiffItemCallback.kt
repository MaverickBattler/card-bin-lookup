package com.test.card_bin_lookup

import androidx.recyclerview.widget.DiffUtil

class ResultDiffItemCallback : DiffUtil.ItemCallback<Result>() {
    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean = (oldItem.bin == newItem.bin)

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean = (oldItem == newItem)
}