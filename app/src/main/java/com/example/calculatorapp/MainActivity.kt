package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.calculatorapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var operand1: Double? = null
    private var operator: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button0.setOnClickListener { appendToInput("0") }
        binding.button1.setOnClickListener { appendToInput("1") }
        binding.button2.setOnClickListener { appendToInput("2") }
        binding.button3.setOnClickListener { appendToInput("3") }
        binding.button4.setOnClickListener { appendToInput("4") }
        binding.button5.setOnClickListener { appendToInput("5") }
        binding.button6.setOnClickListener { appendToInput("6") }
        binding.button7.setOnClickListener { appendToInput("7") }
        binding.button8.setOnClickListener { appendToInput("8") }
        binding.button9.setOnClickListener { appendToInput("9") }
        binding.buttonDecimal.setOnClickListener { appendToInput(".") }

        binding.buttonAdd.setOnClickListener { operate("+") }
        binding.buttonSubtract.setOnClickListener { operate("-") }
        binding.buttonMultiply.setOnClickListener { operate("*") }
        binding.buttonDivide.setOnClickListener { operate("/") }

        binding.buttonEquals.setOnClickListener { calculate() }
        binding.buttonClear.setOnClickListener { clearInput() }
    }

    private fun appendToInput(value: String) {
        binding.inputTextView.append(value)
    }

    private fun operate(op: String) {
        try {
            operand1 = binding.inputTextView.text.toString().toDouble()
            operator = op
            binding.inputTextView.text.clear()
        } catch (e: NumberFormatException) {
            binding.inputTextView.text.clear()
            binding.inputTextView.hint = "Invalid input"
        }
    }

    private fun calculate() {
        try {
            val operand2 = binding.inputTextView.text.toString().toDouble()
            val result = when (operator) {
                "+" -> operand1!! + operand2
                "-" -> operand1!! - operand2
                "*" -> operand1!! * operand2
                "/" -> operand1!! / operand2
                else -> operand2
            }
            binding.inputTextView.text.clear()
            binding.inputTextView.text.append(result.toString())
            operand1 = null
            operator = null
        } catch (e: NumberFormatException) {
            binding.inputTextView.text.clear()
            binding.inputTextView.hint = "Invalid input"
        }
    }

    private fun clearInput() {
        binding.inputTextView.text.clear()
        binding.inputTextView.hint = "0"
        operand1 = null
        operator = null
    }
}