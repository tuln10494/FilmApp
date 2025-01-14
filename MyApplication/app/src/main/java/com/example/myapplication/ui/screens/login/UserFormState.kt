package com.example.myapplication.ui.screens.login

data class UserFormState(
    val name: String = "",
    val phoneNumber: String = "",
    val email: String = "",
    val password: String = "",
    val dateOfBirth: String = "",
    val gender: String = "",
    val region: String = "",
    val district: String = "",
    val preferTheater: String = "",
    val agreements: Map<String, Boolean> = mapOf(
        "agreement1" to false,
        "agreement2" to false,
        "agreement3" to false,
        "agreement4" to false
    )
) {
    fun isFormValid() = with(this) {
        agreements.values.all { it } // Check if all agreements are checked
    }
}

