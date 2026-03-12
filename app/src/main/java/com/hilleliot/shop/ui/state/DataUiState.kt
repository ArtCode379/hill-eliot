package com.hilleliot.shop.ui.state

sealed class DataUiState<out T> {
    object Initial : DataUiState<Nothing>()
    object Empty : DataUiState<Nothing>()
    data class Populated<T>(val data: T) : DataUiState<T>()
}
