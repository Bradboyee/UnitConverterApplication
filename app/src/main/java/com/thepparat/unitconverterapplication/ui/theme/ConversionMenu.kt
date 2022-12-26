package com.thepparat.unitconverterapplication.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.thepparat.unitconverterapplication.data.Conversion

@Composable
fun ConversionMenu(
    list: List<Conversion>, modifier: Modifier = Modifier,
    convert: (Conversion) -> Unit
) {
    var displayText by remember {
        mutableStateOf("Select you conversion type")
    }
    var textFiledSize by remember {
        mutableStateOf(Size.Zero)
    }
    var expanded by remember {
        mutableStateOf(false)
    }

    val icon = if (expanded) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }
    Column {

        OutlinedTextField(
            value = displayText,
            onValueChange = { displayText = it },
            textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
            modifier = modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textFiledSize = coordinates.size.toSize()
                },
            label = { Text(text = "Conversation type") },
            trailingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = "icon",
                    modifier = modifier.clickable { expanded = !expanded }
                )
            }, readOnly = true
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = modifier.width(with(LocalDensity.current) { textFiledSize.width.toDp() })
        ) {
            list.forEach { conversation ->
                DropdownMenuItem(onClick = {
                    displayText = conversation.description
                    expanded = false
                    convert(conversation)
                }) {
                    Text(
                        text = conversation.description,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
