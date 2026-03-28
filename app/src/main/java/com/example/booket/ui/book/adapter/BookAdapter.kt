package com.example.booket.ui.book.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.booket.R
import com.example.booket.data.remote.model.Book
import com.example.booket.databinding.ItemBookBinding

class BookAdapter : ListAdapter<Book, BookAdapter.BookViewHolder>(DIFF_CALLBACK) {

    class BookViewHolder(private val binding: ItemBookBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Book) {
            binding.apply {
                tvTitle.text = item.title
                tvAuthors.text = item.authors.joinToString(", ")

                Glide.with(ivThumbnail)
                    .load(item.thumbnail.takeIf { it.isNotBlank() }) // 빈값 방지
                    .placeholder(R.drawable.loading) // 로딩 중
                    .error(android.R.drawable.ic_menu_report_image) // 실패 시
                    .fallback(R.drawable.loading) // null일 때
                    .centerCrop() // 꽉 채우기
                    .into(ivThumbnail) // 적용
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Book>() {
            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem.title == newItem.title
            }
            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem == newItem
            }
        }
    }
}