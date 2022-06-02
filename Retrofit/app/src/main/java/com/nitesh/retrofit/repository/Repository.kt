package com.nitesh.retrofit.repository

import com.nitesh.retrofit.api.RetrofitInstance
import com.nitesh.retrofit.model.Post
import retrofit2.Response

class Repository {
    suspend fun getPost() : Response<Post>{
       return RetrofitInstance.api.getPost()
    }

    suspend fun getPost2(number: Int) : Response<Post>{
        return RetrofitInstance.api.getPost2(number)

    }
}