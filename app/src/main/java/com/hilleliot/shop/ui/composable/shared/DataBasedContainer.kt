package com.hilleliot.shop.ui.composable.shared

import androidx.compose.runtime.Composable
import com.hilleliot.shop.ui.state.DataUiState

@Composable
fun <T> DataBasedContainer(
    dataState: DataUiState<T>,
    dataPopulated: @Composable (T) -> Unit,
    dataEmpty: @Composable () -> Unit,
) {
    when (dataState) {
        is DataUiState.Populated -> dataPopulated(dataState.data)
        is DataUiState.Empty -> dataEmpty()
        is DataUiState.Initial -> Unit
    }
}
