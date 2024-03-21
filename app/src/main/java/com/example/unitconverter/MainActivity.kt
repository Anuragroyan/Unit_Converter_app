package com.example.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                     UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter() {
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("meters") }
    var outputUnit by remember { mutableStateOf("meters") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    var convertionFactor = remember { mutableStateOf(1.0) }
    var oconvertionFactor = remember { mutableStateOf(1.0) }

    fun convertUnits(){
        val inputValueDouble =  inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * convertionFactor.value * 100.0 / oconvertionFactor.value).roundToInt() / 100.0
        outputValue = result.toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(text = "Unit Converter")
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue, onValueChange = {
            inputValue=it
            convertUnits()
        },
            label = { Text(text = "Enter Value")})
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            // input box
            Box {
                // input button
                Button(onClick = { iExpanded = true }) {
                    Text(text = inputUnit)
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down"
                    )
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                    DropdownMenuItem(text = { Text(text = "Centimeter") }, onClick = {
                        iExpanded = false
                        inputUnit = "Centimeter"
                        convertionFactor.value = 0.01
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Meter") }, onClick = {
                        iExpanded = false
                        inputUnit = "Meter"
                        convertionFactor.value = 1.0
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {
                        iExpanded = false
                        inputUnit = "Feet"
                        convertionFactor.value = 0.3048
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Millimeters") }, onClick = {
                        iExpanded = false
                        inputUnit = "Millimeters"
                        convertionFactor.value = 0.001
                        convertUnits()
                    })
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            // output box
            Box {
                // output button
                Button(onClick = { oExpanded = true }) {
                    Text(text = outputUnit)
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down"
                    )
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                    DropdownMenuItem(text = { Text(text = "Centimeter") }, onClick = {
                        oExpanded = false
                        outputUnit = "Centimeter"
                        oconvertionFactor.value = 0.01
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Meter") }, onClick = {
                        oExpanded = false
                        outputUnit = "Meter"
                        oconvertionFactor.value = 1.0
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {
                        oExpanded = false
                        outputUnit = "Feet"
                        oconvertionFactor.value = 0.3048
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Millimeter") }, onClick = {
                        oExpanded = false
                        outputUnit = "Millimeters"
                        oconvertionFactor.value = 0.001
                        convertUnits()
                    })
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Results: $outputValue $outputUnit")
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()
}