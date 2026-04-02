package com.example.booket.ui.book.fragment.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.booket.data.remote.client.RetrofitClient.kakaoApi
import com.example.booket.databinding.FragmentSearchBinding
import com.example.booket.repository.BookRepository
import com.example.booket.ui.book.adapter.BookAdapter
import com.example.booket.ui.book.model.BookUiState
import com.example.booket.ui.book.viewmodel.BookViewModel
import com.example.booket.ui.book.viewmodel.BookViewModelFactory
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null // nullable
    private val binding get() = _binding!! // non-null (생명주기 내 안전)

    private lateinit var bookAdapter: BookAdapter

    private val bookViewModel: BookViewModel by viewModels {
        BookViewModelFactory(BookRepository(kakaoApi))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeUiState() // 상태 관찰
        setupRecyclerView() // RecyclerView 설정
        setupSearchListener() // 검색 이벤트 설정
    }

    private fun setupRecyclerView() {
        bookAdapter = BookAdapter()

        binding.recyclerview.apply {
            adapter = bookAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true) // 아이템 크기 고정 -> 성능 최적화
        }
    }

    private fun setupSearchListener() = with(binding) {
        btnSearch.setOnClickListener {
            val query = edtSearch.text.toString().trim()
            if (query.isEmpty()) return@setOnClickListener
            bookViewModel.searchBooks(query)
        }
    }

    // UI 상태 구독
    private fun observeUiState() = with(viewLifecycleOwner) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                bookViewModel.uiState.collect { state ->
                    when (state) {
                        is BookUiState.Success -> { bookAdapter.submitList(state.data) }
                        is BookUiState.Error -> { Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show() }
                        else -> Unit
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null // 메모리 누수 방지
    }
}