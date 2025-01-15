package com.example.myapplication.models.ticket

data class Cinema(
    val id: Int,
    val name: String,
    val address: String,
    val timeSlots: List<String>
)