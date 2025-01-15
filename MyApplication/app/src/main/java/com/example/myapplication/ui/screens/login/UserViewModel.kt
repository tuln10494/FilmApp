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
//private val userDao: UserDao
class UserViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {//private val userDao: UserDao

    fun login(email: String, password: String, onLoginResult: (Int?) -> Unit) {
        viewModelScope.launch {
//            val user = userDao.getUserByEmailAndPassword(email, password)
//            onLoginResult(user?.id)
            try {
                val user = userRepository.getUserByEmailAndPassword(email, password)
                onLoginResult(user?.id)
            } catch (e: Exception) {
                Log.e("UserViewModel", "Login error: ${e.message}")
                onLoginResult(null)
            }
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
    fun getUserById(userId: Int, onResult: (UserInfo?) -> Unit) {
        viewModelScope.launch {
            try {
                val user = userRepository.getUserById(userId)
                onResult(user)
            } catch (e: Exception) {
                Log.e("UserViewModel", "Error fetching user: ${e.message}")
                onResult(null)
            }
        }
    }
    fun updateUserImage(userId: Int, imageUri: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val user = userRepository.getUserById(userId)
                if (user != null) {
                    val updatedUser = user.copy(userImage = imageUri)
                    userRepository.update(updatedUser)
                    onResult(true)
                } else {
                    onResult(false)
                }
            } catch (e: Exception) {
                Log.e("UserViewModel", "Error updating user image: ${e.message}")
                onResult(false)
            }
        }
    }
}

