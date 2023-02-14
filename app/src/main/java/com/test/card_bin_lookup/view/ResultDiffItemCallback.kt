package com.test.card_bin_lookup.view

import androidx.recyclerview.widget.DiffUtil
import com.test.card_bin_lookup.model.Result

class ResultDiffItemCallback : DiffUtil.ItemCallback<Result>() {
    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean =
        (oldItem.bin == newItem.bin)

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean =
        (oldItem == newItem)
}