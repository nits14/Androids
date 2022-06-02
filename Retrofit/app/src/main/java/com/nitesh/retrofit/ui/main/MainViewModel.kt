package com.nitesh.retrofit.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nitesh.retrofit.model.Post
import com.nitesh.retrofit.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {

    val myresponse : MutableLiveData<Response<Post>> = MutableLiveData()
    val myresponse2 : MutableLiveData<Response<Post>> = MutableLiveData()
    fun getPost(){
        viewModelScope.launch {
            val reposnse = repository.getPost()
            myresponse.value = reposnse
        }
    }

    fun getPost2(number: Int){
        viewModelScope.launch {
            val response = repository.getPost2(number)
            myresponse2.value = response
        }
    }
}