package com.example.examtask.calculator

import android.util.Log
import androidx.lifecycle.ViewModel
import net.objecthunter.exp4j.ExpressionBuilder
import kotlin.math.pow
import kotlin.math.sqrt

class CalculatorViewModel() : ViewModel() {

    fun factorial(n: Int): Int {
        // this method is use to find factorial
        return if (n == 1 || n == 0) 1 else n * factorial(n - 1)
    }

    fun percent(n: Double): Double {
        return n / 100.0
    }

    fun square(n: Double): Double {
        return n.pow(2)
    }

    fun getSqrt(n: Double): Double {
        return sqrt(n)
    }

    fun calculate(str: String): Double {
        try {
            val expression = ExpressionBuilder(str).build()
            val result = expression.evaluate()

            return result
        }
        catch (e:Exception) {
            Log.i("ERROR", "${e.message}")
        }
        return 0.0
    }
}