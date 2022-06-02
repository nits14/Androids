package com.nitesh.retrofit.api

import com.nitesh.retrofit.model.Post
import retrofit2.http.GET

interface SimpleApi {

    @GET("posts/1")
    suspend fun getPost() : Post
}