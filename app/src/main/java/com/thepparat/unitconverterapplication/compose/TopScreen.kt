package com.thepparat.unitconverterapplication.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.thepparat.unitconverterapplication.data.Conversion
import com.thepparat.unitconverterapplication.ui.theme.ConversionMenu
import java.math.RoundingMode
import java.text.DecimalFormat

@Composable
fun TopScreen(list: List<Conversion>, save: (message1: String, message2: String) -> Unit) {
    val selectedConversion: MutableState<Conversion?> = remember {
        mutableStateOf(null)
    }

    val inputText: MutableState<String> = remember {
        mutableStateOf("")
    }

    val typeValue = remember {
        mutableStateOf("0.0")
    }


    ConversionMenu(list = list) {
        selectedConversion.value = it
        typeValue.value = "0.0"
    }
    selectedConversion.value?.let {
        InputBlock(conversion = it, inputText = inputText) { input ->
            typeValue.value = input
        }
    }

    if (typeValue.value != "0.0") {
        val input = typeValue.value.toDouble()
        val multiply = selectedConversion.value?.multiplyBy ?: 0.00
        val result = input * multiply

        val df = DecimalFormat("#.####")
        df.roundingMode = RoundingMode.DOWN
        val roundedResult = df.format(result)

        val message1 = "${typeValue.value} ${selectedConversion.value?.convertFrom} is equal to :"
        val message2 = "$roundedResult ${selectedConversion.value?.convertTo}"
        save(message1, message2)
        ResultBlock(message1 = message1, message2 = message2)
    }

}

