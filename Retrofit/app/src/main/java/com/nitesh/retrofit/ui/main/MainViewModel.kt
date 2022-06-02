package com.nitesh.retrofit.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nitesh.retrofit.model.Post
import com.nitesh.retrofit.repository.Repository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {

    val myresponse : MutableLiveData<Post> = MutableLiveData()
    fun getPost(){
        viewModelScope.launch {
            val reposnse = repository.getPost()
            myresponse.value = reposnse
        }
    }
}