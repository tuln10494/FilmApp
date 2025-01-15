package com.example.myapplication.ui.screens.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor() : ViewModel() {//private val userDao: UserDao

    fun login(email: String, password: String, onLoginResult: (Long?) -> Unit) {
        viewModelScope.launch {
//            val user = userDao.getUserByEmailAndPassword(email, password)
//            onLoginResult(user?.id)

        }
    }

//    fun register(email: String, password: String, onRegisterResult: (Long?) -> Unit) {
//        viewModelScope.launch {
////            val newUser = User(email = email, password = password)
////            userDao.insert(newUser)
//            val insertedUser = userDao.getUserByEmailAndPassword(email, password)
//            Log.d("User", "New user registered with ID: ${insertedUser?.id}")
//            onRegisterResult(insertedUser?.id)
//        }
//    }
}