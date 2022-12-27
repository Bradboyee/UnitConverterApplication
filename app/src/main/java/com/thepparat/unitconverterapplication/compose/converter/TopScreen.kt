package com.thepparat.unitconverterapplication.compose.converter

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thepparat.unitconverterapplication.data.Conversion
import java.math.RoundingMode
import java.text.DecimalFormat

@Composable
fun TopScreen(
    list: List<Conversion>,
    selectedConversion: MutableState<Conversion?>,
    inputText: MutableState<String>,
    typeValue: MutableState<String>,
    isLandScape: Boolean,
    save: (message1: String, message2: String) -> Unit
) {

    var isSave by remember {
        mutableStateOf(false)
    }

    Column(modifier = Modifier.verticalScroll(rememberScrollState()).padding(bottom = 50.dp)) {
        ConversionMenu(list = list, isLandScape = isLandScape) {
            selectedConversion.value = it
            typeValue.value = "0.0"
        }
        selectedConversion.value?.let {
            InputBlock(conversion = it, inputText = inputText, isLandScape) { input ->
                typeValue.value = input
                isSave = true
            }
        }

        if (typeValue.value != "0.0") {
            val input = typeValue.value.toDouble()
            val multiply = selectedConversion.value?.multiplyBy ?: 0.00
            val result = input * multiply

            val df = DecimalFormat("#.####")
            df.roundingMode = RoundingMode.DOWN
            val roundedResult = df.format(result)

            val message1 =
                "${typeValue.value} ${selectedConversion.value?.convertFrom} is equal to :"
            val message2 = "$roundedResult ${selectedConversion.value?.convertTo}"
            if (isSave) {
                save(message1, message2)
                isSave = false
            }
            ResultBlock(message1 = message1, message2 = message2)
        }
    }
}

