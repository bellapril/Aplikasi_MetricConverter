// MainActivity.kt
package com.example.metricconverter

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var inputValue: EditText
    private lateinit var fromUnitSpinner: Spinner
    private lateinit var toUnitSpinner: Spinner
    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputValue = findViewById(R.id.inputValue)
        fromUnitSpinner = findViewById(R.id.fromUnitSpinner)
        toUnitSpinner = findViewById(R.id.toUnitSpinner)
        resultTextView = findViewById(R.id.resultTextView)
        val convertButton: Button = findViewById(R.id.convertButton)

        val units = listOf("Millimeter", "Centimeter", "Meter", "Kilometer")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, units)
        fromUnitSpinner.adapter = adapter
        toUnitSpinner.adapter = adapter

        convertButton.setOnClickListener {
            convertUnits()
        }
    }

    @SuppressLint("DefaultLocale", "SetTextI18n")
    private fun convertUnits() {
        val fromUnit = fromUnitSpinner.selectedItem.toString()
        val toUnit = toUnitSpinner.selectedItem.toString()
        val input = inputValue.text.toString().toDoubleOrNull()

        if (input != null) {
            val result = convertLength(input, fromUnit, toUnit)
            resultTextView.text = String.format("Result: %.4f %s", result, toUnit)
        } else {
            resultTextView.text = "Please enter a valid number."
        }
    }

    private fun convertLength(value: Double, fromUnit: String, toUnit: String): Double {
        val meterValue = when (fromUnit) {
            "Millimeter" -> value / 1000
            "Centimeter" -> value / 100
            "Meter" -> value
            "Kilometer" -> value * 1000
            else -> 0.0
        }

        return when (toUnit) {
            "Millimeter" -> meterValue * 1000
            "Centimeter" -> meterValue * 100
            "Meter" -> meterValue
            "Kilometer" -> meterValue / 1000
            else -> 0.0
        }
    }
}
