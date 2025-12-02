package com.Biby.post7

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.Biby.post7.databinding.ActivityBookDetailBinding
import com.bumptech.glide.Glide


class BookDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBookDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarDetail)
        supportActionBar?.title = "Book Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val title = intent.getStringExtra("title")
        val originalTitle = intent.getStringExtra("originalTitle")
        val releaseDate = intent.getStringExtra("releaseDate")
        val description = intent.getStringExtra("description")
        val pages = intent.getIntExtra("pages", 0)
        val cover = intent.getStringExtra("cover")

        binding.tvTitleDetail.text = title
        binding.tvOriginalTitleDetail.text = originalTitle
        binding.tvOriginalTitleDetail.visibility =
            if (originalTitle.isNullOrEmpty()) View.GONE else View.VISIBLE

        binding.tvReleaseDateDetail.text = releaseDate ?: ""
        binding.tvDescription.text = description ?: ""
        binding.tvPages.text = "Pages: $pages"

        Glide.with(this)
            .load(cover)
            .placeholder(R.drawable.ic_book_placeholder)
            .into(binding.imgCoverBig)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
