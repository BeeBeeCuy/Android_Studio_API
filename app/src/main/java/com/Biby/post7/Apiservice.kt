package com.Biby.post7

import com.Biby.post7.Book
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("en/books")
    fun getBooks(): Call<List<Book>>
}