package com.example.myapplication.ui.screens.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.user.UserInfo
import com.example.myapplication.data.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor( private val userRepository: UserRepository) : ViewModel() {//private val userDao: UserDao

    fun login(email: String, password: String, onLoginResult: (Int?) -> Unit) {
        viewModelScope.launch {
            try {
                val user = userRepository.getUserByEmailAndPassword(email, password)
                onLoginResult(user?.id)
            } catch (e: Exception) {
                Log.e("UserViewModel", "Login error: ${e.message}")
                onLoginResult(null)
            }
        }
    }

    fun registerUser(
        userName: String,
        phoneNumber: String,
        email: String,
        password: String,
        birthday: String,
        gender: Int,
        favoriteCinema: String,
        province: String,
        district: String,
        onRegisterResult: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val userInfo = UserInfo(
                    userName = userName,
                    userPhoneNumber = phoneNumber,
                    email = email,
                    passWord = password,
                    userBirthday = birthday,
                    userGender = gender,
                    userFavoriteCinema = favoriteCinema,
                    userProvince = province,
                    userDistrict = district
                )
                userRepository.insert(userInfo)
                onRegisterResult(true) // Success callback
            } catch (e: Exception) {
                Log.e("UserViewModel", "Error registering user: ${e.message}")
                onRegisterResult(false) // Failure callback
            }
        }
    }
}