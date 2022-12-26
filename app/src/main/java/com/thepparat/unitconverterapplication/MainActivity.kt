package com.thepparat.unitconverterapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.thepparat.unitconverterapplication.compose.BaseScreen
import com.thepparat.unitconverterapplication.data.ConverterDatabase
import com.thepparat.unitconverterapplication.data.ConverterRepositoryImpl
import com.thepparat.unitconverterapplication.ui.theme.UnitConverterApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dao = ConverterDatabase.getInstance(application).converterDAO
        val repository = ConverterRepositoryImpl(dao)
        val factory = ConverterViewModelFactory(repository)
        setContent {
            UnitConverterApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BaseScreen(factory)
                }
            }
        }
    }
}
