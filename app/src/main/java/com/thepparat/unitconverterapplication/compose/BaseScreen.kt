package com.thepparat.unitconverterapplication.compose

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.thepparat.unitconverterapplication.ConverterViewModel
import com.thepparat.unitconverterapplication.ConverterViewModelFactory
import com.thepparat.unitconverterapplication.compose.converter.TopScreen
import com.thepparat.unitconverterapplication.compose.history.HistoryScreen

@Composable
fun BaseScreen(
    factory: ConverterViewModelFactory,
    modifier: Modifier = Modifier,
    converterViewModel: ConverterViewModel = viewModel(factory = factory)
) {
    val list = converterViewModel.getConversion()
    val history = converterViewModel.resultList.collectAsState(initial = emptyList())
    val configuration = LocalConfiguration.current
    var isLandScape by remember {
        mutableStateOf(false)
    }

    when (configuration.orientation) {
        //แนวนอน
        Configuration.ORIENTATION_LANDSCAPE -> {
            isLandScape = true
            Row(
                modifier = modifier
                    .padding(30.dp)
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                TopScreen(
                    list,
                    converterViewModel.selectedConversion,
                    converterViewModel.inputText,
                    converterViewModel.typeValue,
                    isLandScape = isLandScape,
                ) { message1, message2 ->
                    converterViewModel.addResult(message1, message2)
                }
                Spacer(modifier = modifier.width(10.dp))
                HistoryScreen(history,
                    onCloseTask = {
                        converterViewModel.removeResult(it)
                    }, onClearAllTask = {
                        converterViewModel.clearAll()
                    })
            }
        }
        else -> {
            isLandScape = false
            Column(modifier = modifier.padding(30.dp)) {
                TopScreen(
                    list,
                    converterViewModel.selectedConversion,
                    converterViewModel.inputText,
                    converterViewModel.typeValue, isLandScape = isLandScape
                ) { message1, message2 ->
                    converterViewModel.addResult(message1, message2)
                }
                Spacer(modifier = modifier.height(20.dp))
                HistoryScreen(history,
                    onCloseTask = {
                        converterViewModel.removeResult(it)
                    }, onClearAllTask = {
                        converterViewModel.clearAll()
                    })
            }
        }
    }

}