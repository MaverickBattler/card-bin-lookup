package com.test.card_bin_lookup

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.test.card_bin_lookup.databinding.ResultItemBinding
import java.util.*


class ResultItemAdapter :
    ListAdapter<Result, ResultItemAdapter.ResultItemViewHolder>(ResultDiffItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultItemViewHolder =
        ResultItemViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: ResultItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ResultItemViewHolder(private val binding: ResultItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun inflateFrom(parent: ViewGroup): ResultItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ResultItemBinding.inflate(layoutInflater, parent, false)

                return ResultItemViewHolder(binding)
            }
        }

        fun bind(item: Result) {
            binding.result = item
            // Установка слушателей к TextView, нажатие на которых можно обработать
            binding.countryCoordinates.setOnClickListener {
                processCoordinatesClick(item.countryLatitude, item.countryLongitude)
            }
            binding.bankPhone.setOnClickListener {
                // TextView является GONE, если bankPhone == null, значит можно использовать !!
                processPhoneNumberClick(item.bankPhone!!)
            }
            binding.bankUrl.setOnClickListener {
                // TextView является GONE, если bankUrl == null, значит можно использовать !!
                processUrlClick(item.bankUrl!!)
            }
        }

        // Обработать клик на координаты
        private fun processCoordinatesClick(latitude: Int, longitude: Int) {
            try {
                val uri: String =
                    String.format(Locale.ENGLISH, "geo:%d,%d", latitude, longitude)
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
                binding.root.context.startActivity(intent)
            } catch (exception: Exception) {
                Log.i("Open map failure", exception.message.toString())
            }
        }

        // Обработать клик на номер телефона
        private fun processPhoneNumberClick(phone: String) {
            val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
            binding.root.context.startActivity(intent)
        }

        // Обработать клик на URL
        private fun processUrlClick(url: String) {
            val i = Intent(Intent.ACTION_VIEW)
            try {
                if (!url.startsWith("http://") && !url.startsWith("https://"))
                    i.data = Uri.parse("https://$url")
                else
                    i.data = Uri.parse(url)
                binding.root.context.startActivity(i)
            } catch (exception: Exception) {
                Log.i("Open URL failure", exception.message.toString())
            }
        }
    }
}