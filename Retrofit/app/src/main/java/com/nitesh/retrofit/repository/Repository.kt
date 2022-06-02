package com.nitesh.retrofit.repository

import com.nitesh.retrofit.api.RetrofitInstance
import com.nitesh.retrofit.model.Post

class Repository {
    suspend fun getPost() : Post{
       return RetrofitInstance.api.getPost()
    }
}