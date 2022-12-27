package com.thepparat.unitconverterapplication.compose.history

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import com.thepparat.unitconverterapplication.data.ConversionResult

@Composable
fun HistoryList(
    list: State<List<ConversionResult>>,
    onCloseTask: (ConversionResult) -> Unit,
) {
    LazyColumn() {
        items(items = list.value, key = { item -> item.id }) { item ->
            HistoryItem(messagePart1 = item.message1, messagePart2 = item.message2, onClose = {
                onCloseTask(item)
            })
        }
    }

}