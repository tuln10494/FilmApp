package com.example.myapplication.models.seatselection

data class SeatsRowData(
    var rowName: String,
    var numberSeats: Int,
    var seatType: SeatType = SeatType.NORM,
    var voidSeats: Set<Int> = emptySet()
)

enum class SeatType {
    NORM, VIP, COUPLE
}