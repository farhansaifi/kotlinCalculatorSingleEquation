package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // Some variables for help Decimal or Numeric values
    private var lastNumeric = false
    private var lastDot = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // Make a function instead of using onClickListener
    fun onDigit(view: View){

        // when press button to get a prefix button text and
        // This text set to our very first textView
        tvInput.append((view as Button).text)

        // This variable is define true here because
        // After some digit we put a decimal or " . "
        lastNumeric = true

        /*Toast.makeText(this,"Button Works",Toast.LENGTH_LONG).show()*/
    }

    // Make a function for clear button on calculator
    fun onClear (view: View){

        // when press clear button empty a very first textView
        tvInput.text = ""

        // When we clear a calculator screen
        // So we need to assign to default values in helping variables
        lastNumeric = false
        lastDot = false
    }

    // Make a function for Decimal "." button on calculator
    fun onDecimalPoint (view: View){

        // Check if the last pressed button is  decimal its not work
        if (lastNumeric && !lastDot){
            tvInput.append(".")

            // We need to assign to default values in helping variables
            lastNumeric = false
            lastDot = true
        }
    }

    fun onEqual (view: View){

        // First of all we check our input is end on numeric
        if (lastNumeric){

            // Make a variable and the assign first's textView's value
            var tvValue = tvInput.text.toString()
            var prefix = ""

            try {
                // Here we are making a condition we are able to multiple times subtraction
                // So we go on again and again subtract the digits
                if (tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)                }

                // Below snippet of code is for subtraction " - " functionality
                when {
                    tvValue.contains("-") -> {
                        // Now we split a string and make a equation
                        val splitValue = tvValue.split("-")

                        // These two variables for two parts of tvValue after split
                        // Example = tvValue = 75-25
                        var one  = splitValue[0] // 75
                        val two  = splitValue[1] // 25

                        // Here we have two parts of actual value
                        // After a equation we have a result this is a our value's part one
                        // After that we enter a second part of value
                        // And the equation goes on again and again
                        if (prefix.isNotEmpty()){
                            one = prefix + one
                        }

                        // Do the equation and gated result convert into string after that
                        // Assign in our first text view
                        tvInput.text = removeZeroAfterDot ((one.toDouble() - two.toDouble()).toString())

                    }
                    // Below snippet of code is for addition " + " functionality
                    tvValue.contains("+") -> {
                        // Now we split a string and make a equation
                        val splitValue = tvValue.split("+")

                        // These two variables for two parts of tvValue after split
                        // Example = tvValue = 75-25
                        var one  = splitValue[0] // 75
                        val two  = splitValue[1] // 25

                        // Here we have two parts of actual value
                        // After a equation we have a result this is a our value's part one
                        // After that we enter a second part of value
                        // And the equation goes on again and again
                        if (prefix.isNotEmpty()){
                            one = prefix + one
                        }

                        // Do the equation and gated result convert into string after that
                        // Assign in our first text view
                        tvInput.text = removeZeroAfterDot ((one.toDouble() + two.toDouble()).toString())

                    }
                    // Below snippet of code is for multiplication " * " functionality
                    tvValue.contains("*") -> {
                        // Now we split a string and make a equation
                        val splitValue = tvValue.split("*")

                        // These two variables for two parts of tvValue after split
                        // Example = tvValue = 75-25
                        var one  = splitValue[0] // 75
                        val two  = splitValue[1] // 25

                        // Here we have two parts of actual value
                        // After a equation we have a result this is a our value's part one
                        // After that we enter a second part of value
                        // And the equation goes on again and again
                        if (prefix.isNotEmpty()){
                            one = prefix + one
                        }

                        // Do the equation and gated result convert into string after that
                        // Assign in our first text view
                        tvInput.text = removeZeroAfterDot ((one.toDouble() * two.toDouble()).toString())

                    }
                    // Below snippet of code is for divide " / " functionality
                    tvValue.contains("/") -> {
                        // Now we split a string and make a equation
                        val splitValue = tvValue.split("/")

                        // These two variables for two parts of tvValue after split
                        // Example = tvValue = 75-25
                        var one  = splitValue[0] // 75
                        val two  = splitValue[1] // 25

                        // Here we have two parts of actual value
                        // After a equation we have a result this is a our value's part one
                        // After that we enter a second part of value
                        // And the equation goes on again and again
                        if (prefix.isNotEmpty()){
                            one = prefix + one
                        }

                        // Do the equation and gated result convert into string after that
                        // Assign in our first text view
                        tvInput.text =removeZeroAfterDot ((one.toDouble() / two.toDouble()).toString())

                    }
                }

            } catch (e:ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    // Removing unnecessary Dot " . " or Zero " 0 " form the result
    private fun removeZeroAfterDot (result: String) :String{
        var value = result
        if (result.contains(".0"))

            // Indexing start on 0
            value = result.substring(0,result.length - 2)
        return value
    }

    // Operators ( + , - , * , /)
    fun onOperator (view: View){
        if (lastNumeric && !isOperatorAdded(tvInput.text.toString())){
            tvInput.append((view as Button).text)

            // We need to assign to default values in helping variables
            lastNumeric = false
            lastDot = false
        }
    }

    // Make a function for added operators in textView
    // I am not make this function private Android Studio says me make it private
    // So I'll do it ('_') ('_')
    private fun isOperatorAdded(value: String) :Boolean{

        // Check and assure our calculator's first input is digit
        // Not a operator " - , + , * , / , . ,"
        return if (value.startsWith("-")){
            false
        }else{
            value.contains("/") ||value.contains("*")
                    || value.contains("+") || value.contains("-")
        }
    }
}