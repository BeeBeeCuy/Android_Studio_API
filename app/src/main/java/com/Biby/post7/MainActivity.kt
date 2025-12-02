package com.Biby.post7

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.Biby.post7.BookAdapter
import com.Biby.post7.databinding.ActivityMainBinding
import com.Biby.post7.Book
import com.Biby.post7.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvBooks.layoutManager = LinearLayoutManager(this)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Harry Potter Books"
        fetchBooks()
    }

    private fun fetchBooks() {
        binding.progressBar.visibility = View.VISIBLE

        val call = RetrofitClient.apiService.getBooks()
        call.enqueue(object : Callback<List<Book>> {
            override fun onResponse(call: Call<List<Book>>, response: Response<List<Book>>) {
                binding.progressBar.visibility = View.GONE

                if (response.isSuccessful) {
                    val books = response.body() ?: emptyList()
                    binding.rvBooks.adapter = BookAdapter(books)
                }
            }

            override fun onFailure(call: Call<List<Book>>, t: Throwable) {
                binding.progressBar.visibility = View.GONE
            }
        })
    }
}
