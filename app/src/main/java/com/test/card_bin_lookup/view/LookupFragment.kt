package com.test.card_bin_lookup.view

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.card_bin_lookup.*
import com.test.card_bin_lookup.databinding.FragmentLookupBinding
import com.test.card_bin_lookup.model.ResultDatabase
import com.test.card_bin_lookup.viewmodel.LookupViewModel
import com.test.card_bin_lookup.viewmodel.ResultViewModelFactory


class LookupFragment : Fragment() {

    private var _binding: FragmentLookupBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLookupBinding.inflate(inflater, container, false)
        val view = binding.root
        // Получение ссылку на данное приложение
        val application = requireNotNull(this.activity).application
        // Создание базы данных (если она еще не существует)
        // и получение ссылки на свойство taskDao
        val dao = ResultDatabase.getInstance(application).resultDao
        // Создание ViewModelFactory
        val viewModelFactory = ResultViewModelFactory(dao)
        // Получение viewModel с использованием ViewModelProvider
        val viewModel = ViewModelProvider(
            this,
            viewModelFactory
        )[LookupViewModel::class.java]
        // Установка viewModel в layout
        binding.viewModel = viewModel
        // Установка layout's lifecycle owner, чтобы он мог реагировать на изменения livedata
        binding.lifecycleOwner = viewLifecycleOwner
        // Создание адаптера
        val adapter = ResultItemAdapter()
        // Регитрация AdapterDataObserver для автоматической прокрутки списка в начало
        adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                println(positionStart)
                (binding.historyRecyclerview.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(
                    0,
                    0
                )
            }
        })
        // Установка разделителя в RecyclerView
        binding.historyRecyclerview.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        // Добавить адаптер к RecyclerView
        binding.historyRecyclerview.adapter = adapter
        // Установка данных в адаптер
        viewModel.lookupResults.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it)
            }
        }
        // По нажатию на кнопку "ПОИСК"
        binding.searchButton.setOnClickListener {
            val binToLookup = binding.binEdittext.text.toString()
            if (viewModel.isBinAppropriate(binToLookup)) {
                // Кнопка пропадает, появляется индикатор загрузки
                binding.searchProgressbar.visibility = View.VISIBLE
                binding.searchButton.visibility = View.INVISIBLE
                viewModel.lookup(binToLookup)
            } else {
                // Показать сообщение о неправильном формате введенного BIN
                showInappropriateBinMessage()
            }
            // Сброс показываемого BIN
            binding.binEdittext.text = null
        }
        // Отлеживать получение ответа на запрос
        viewModel.lookupResponse.observe(viewLifecycleOwner) { response ->
            // Кнопка появляется, индикатор загрузки пропадает
            binding.searchProgressbar.visibility = View.INVISIBLE
            binding.searchButton.visibility = View.VISIBLE
            if (response != null) {
                // Вывод сообщений об ошибках, если они есть
                when (response.code()) {
                    // Нет информации о данном BIN
                    404 -> showNoInformationAboutBinMessage()
                    // Достигнуто максимальное количество попыток
                    429 -> showMaxAttemptsAmountReachedMessage()
                    else -> {
                        if (response.body() == null) {
                            // Если все же получен пустой ответ
                            showEmptyAnswerReceivedMessage()
                        }
                    }
                }
            } else {
                // Произошла ошибка подключения
                showNoInternetConnectionMessage()
            }
        }
        return view
    }
    // Показать сообщение о неправильном формате BIN
    private fun showInappropriateBinMessage() {
        val toast = Toast.makeText(
            activity, R.string.bin_should_be_between_6_and_8,
            Toast.LENGTH_LONG
        )
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }
    // Показать сообщение об отсутствии информации о данном BIN
    private fun showNoInformationAboutBinMessage() {
        val toast = Toast.makeText(
            activity, getString(R.string.no_information_about_this_bin),
            Toast.LENGTH_LONG
        )
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }
    // Показать сообщение о достижении максимального количества запросов в минуту
    private fun showMaxAttemptsAmountReachedMessage() {
        val toast = Toast.makeText(
            activity, getString(R.string.reached_max_amount_of_tries),
            Toast.LENGTH_LONG
        )
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }
    // Показать сообщение о получении пустого ответа
    private fun showEmptyAnswerReceivedMessage() {
        val toast = Toast.makeText(
            activity, R.string.empty_answer_received,
            Toast.LENGTH_LONG
        )
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }
    // Показать сообщение об отсутствии подключения к интернету
    private fun showNoInternetConnectionMessage() {
        val toast = Toast.makeText(
            activity, getString(R.string.no_internet_connection),
            Toast.LENGTH_LONG
        )
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}