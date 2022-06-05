package com.nitesh.roomandnavgraph.data

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {

    val readAllData : LiveData<List<User>>  = userDao.realAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }
}