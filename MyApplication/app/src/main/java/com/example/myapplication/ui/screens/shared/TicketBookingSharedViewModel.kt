package com.example.myapplication.ui.screens.shared

import androidx.lifecycle.ViewModel
import com.example.myapplication.data.movie.Movie
import com.example.myapplication.models.ticket.Cinema
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class TicketBookingSharedViewModel @Inject constructor() : ViewModel() {
    private val _selectedMovie: MutableStateFlow<Movie?> = MutableStateFlow(null)
    val selectedMovie = _selectedMovie.asStateFlow()

    private val _selectedCinema: MutableStateFlow<Cinema?> = MutableStateFlow(null)
    val selectedCinema = _selectedCinema.asStateFlow()

    private val _selectedDate: MutableStateFlow<LocalDate?> = MutableStateFlow(null)
    val selectedDate = _selectedDate.asStateFlow()

    private val _selectedHour: MutableStateFlow<String?> = MutableStateFlow(null)
    val selectedHour = _selectedHour.asStateFlow()

    fun setSelectedMovie(movie: Movie) {
        _selectedMovie.update {
            movie
        }
    }

    fun setSelectedCinema(cinema: Cinema) {
        _selectedCinema.update {
            cinema
        }
    }

    fun setSelectedDate(date: LocalDate) {
        _selectedDate.update {
            date
        }
    }

    fun setSelectedHour(hour: String) {
        _selectedHour.update {
            hour
        }
    }
}