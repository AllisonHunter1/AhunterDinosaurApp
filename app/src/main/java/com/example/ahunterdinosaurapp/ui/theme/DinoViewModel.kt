package com.example.ahunterdinosaurapp.ui.theme


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ahunterdinosaurapp.network.DinoApi
import com.example.ahunterdinosaurapp.network.DinoPhoto
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface DinoUiState {
    data class Success(val photos: List<DinoPhoto>) : DinoUiState
    object Error : DinoUiState
    object Loading : DinoUiState
}

class DinosViewModel : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var dinoUiState: DinoUiState by mutableStateOf(DinoUiState.Loading)
        private set

    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getDinoPhotos()
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates
     * [MarsPhoto] [List] [MutableList].
     */
    fun getDinoPhotos() {
        viewModelScope.launch {
            dinoUiState = try {
                DinoUiState.Success(DinoApi.retrofitService.getPhotos())
            } catch (e: IOException) {
                DinoUiState.Error
            }
        }
    }
}