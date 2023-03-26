package com.example.examtask.calculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.examtask.R
import com.example.examtask.databinding.FragmentCalculatorBinding

class CalculatorFragment : Fragment() {

    //     Объявление переменных
    lateinit var tvsec: TextView
    lateinit var tvMain: TextView
    lateinit var bac: Button
    lateinit var bc: Button
    lateinit var bbrac1: Button
    lateinit var bbrac2: Button
    lateinit var bsin: Button
    lateinit var bcos: Button
    lateinit var btan: Button
    lateinit var blog: Button
    lateinit var bln: Button
    lateinit var bfact: Button
    lateinit var bsquare: Button
    lateinit var bsqrt: Button

    //    lateinit var binv: Button
    lateinit var b0: Button
    lateinit var b9: Button
    lateinit var b8: Button
    lateinit var b7: Button
    lateinit var b6: Button
    lateinit var b5: Button
    lateinit var b4: Button
    lateinit var b3: Button
    lateinit var b2: Button
    lateinit var b1: Button
    lateinit var bpi: Button
    lateinit var be: Button
    lateinit var bmul: Button
    lateinit var bminus: Button
    lateinit var bplus: Button
    lateinit var bequal: Button
    lateinit var bdot: Button
    lateinit var bdiv: Button

    private lateinit var viewModel: CalculatorViewModel
    private lateinit var viewModelFactory: CalculatorViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentCalculatorBinding>(
            inflater,
            R.layout.fragment_calculator,
            container, false
        )
        viewModelFactory = CalculatorViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(CalculatorViewModel::class.java)

        // Инициализация переменных
        tvsec = binding.idTVSecondary    // дополнительное текстовое представление
        tvMain = binding.idTVprimary     // основное текстовое представление
        bac = binding.bac
        bc = binding.bc
        bbrac1 = binding.bbrac1
        bbrac2 = binding.bbrac2
        bsin = binding.bsin
        bcos = binding.bcos
        btan = binding.btan
        blog = binding.blog
        bln = binding.bln
        bfact = binding.bfact
        bsquare = binding.bsquare
        bsqrt = binding.bsqrt
//        binv = binding.binv
        b0 = binding.b0
        b9 = binding.b9
        b8 = binding.b8
        b7 = binding.b7
        b6 = binding.b6
        b5 = binding.b5
        b4 = binding.b4
        b3 = binding.b3
        b2 = binding.b2
        b1 = binding.b1
        bpi = binding.bpi
        be = binding.be
        bmul = binding.bmul
        bminus = binding.bminus
        bplus = binding.bplus
        bequal = binding.bequal
        bdot = binding.bdot
        bdiv = binding.bdiv

        var bracket_count: Int = 0  // считает количество открытых и закрытых скобок

        // Устанавливаем слушателей по нажатию на соответствующие кнопки
        b1.setOnClickListener {
            tvMain.text = (tvMain.text.toString() + "1")
        }
        b2.setOnClickListener {
            tvMain.text = (tvMain.text.toString() + "2")
        }
        b3.setOnClickListener {
            tvMain.text = (tvMain.text.toString() + "3")
        }
        b4.setOnClickListener {
            tvMain.text = (tvMain.text.toString() + "4")
        }
        b5.setOnClickListener {
            tvMain.text = (tvMain.text.toString() + "5")
        }
        b6.setOnClickListener {
            tvMain.text = (tvMain.text.toString() + "6")
        }
        b7.setOnClickListener {
            tvMain.text = (tvMain.text.toString() + "7")
        }
        b8.setOnClickListener {
            tvMain.text = (tvMain.text.toString() + "8")
        }
        b9.setOnClickListener {
            tvMain.text = (tvMain.text.toString() + "9")
        }
        b0.setOnClickListener {
            val str: String = tvMain.text.toString()
            // проверка, чтобы вначале был только один 0
            if (!str.equals("0")) {
                tvMain.text = (tvMain.text.toString() + "0")
            }
        }
        bdot.setOnClickListener {
            val str: String = tvMain.text.toString()
            // проверка, что вначале нет точки без целой части числа
            if (!str.equals(".") and !str.isEmpty()) {
                tvMain.text = (tvMain.text.toString() + ".")
            } else
                tvMain.text = (tvMain.text.toString() + "0.")
            // проверка, что точка только одна
            // если символ точка не встретился ещё в сроке, то добавить точку
//            if (str.indexOf(".")==-1){
//                tvMain.text = (tvMain.text.toString() + ".")
//            }

        }
        bplus.setOnClickListener {
            // при нажатии на + проверяем: у пользователя уже есть операция плюс на экране
            // если операция плюс уже присутствует
            // то ничего делать не будем
            val str: String = tvMain.text.toString()
            if (str.isEmpty() || str.get(index = str.length - 1).toString().equals(".")
                || str.get(index = str.length - 1).toString().equals("+")
                || str.get(index = str.length - 1).toString().equals("-")
                || str.get(index = str.length - 1).toString().equals("*")
                || str.get(index = str.length - 1).toString().equals("/")
                || str.takeLast(3).equals("sin")
                || str.takeLast(3).equals("cos")
                || str.takeLast(3).equals("tan")
                || str.takeLast(3).equals("log")
                || str.takeLast(2).equals("ln")
            ) {
                Toast.makeText(
                    this.requireContext(),
                    "Please enter a valid number!",
                    Toast.LENGTH_SHORT
                ).show()
            } else
                tvMain.text = (tvMain.text.toString() + "+")
        }
        bdiv.setOnClickListener {
            val str: String = tvMain.text.toString()
            if (str.isEmpty() || str.get(index = str.length - 1).toString().equals(".")
                || str.get(index = str.length - 1).toString().equals("+")
                || str.get(index = str.length - 1).toString().equals("-")
                || str.get(index = str.length - 1).toString().equals("*")
                || str.get(index = str.length - 1).toString().equals("/")
                || str.takeLast(3).equals("sin")
                || str.takeLast(3).equals("cos")
                || str.takeLast(3).equals("tan")
                || str.takeLast(3).equals("log")
                || str.takeLast(2).equals("ln")
            ) {
                Toast.makeText(
                    this.requireContext(),
                    "Please enter a valid number!",
                    Toast.LENGTH_SHORT
                ).show()
            } else
                tvMain.text = (tvMain.text.toString() + "/")
        }
        bbrac1.setOnClickListener {
            tvMain.text = (tvMain.text.toString() + "(")
            bracket_count += 1
        }
        bbrac2.setOnClickListener {

            if (bracket_count>0){
                if(tvMain.text.toString().takeLast(1).equals("("))
                    Toast.makeText(
                        this.requireContext(),
                        "Error! You cannot close a parenthesis with an empty value!",
                        Toast.LENGTH_SHORT
                    ).show()
                else{
                    bracket_count -= 1
                    tvMain.text = (tvMain.text.toString() + ")")
                }
            }
            else
                Toast.makeText(
                    this.requireContext(),
                    "Error! Open brace '(' not found!",
                    Toast.LENGTH_SHORT
                ).show()

        }
        bpi.setOnClickListener {
            // при нажатии на кнопку Пи, к строке значения добавляется значение числа Пи с точностью в 10 знаков после запятой
            tvMain.text = (tvMain.text.toString() + "3.1415926535")
            tvsec.text = (bpi.text.toString())
        }
        be.setOnClickListener {
            // при нажатии на кнопку e, к строке значения добавляется значение числа e с точностью в 10 знаков после запятой
            tvMain.text = (tvMain.text.toString() + "2.7182818284")
            tvsec.text = (be.text.toString())
        }
        bsin.setOnClickListener {
            val str: String = tvMain.text.toString()
            if (str.isNotEmpty()){
                if (str.get(index = str.length - 1).toString().equals(".")
                        || str.takeLast(3).equals("sin")
                        || str.takeLast(3).equals("cos")
                        || str.takeLast(3).equals("tan")
                        || str.takeLast(3).equals("log")
                        || str.takeLast(2).equals("ln"))
                    Toast.makeText(
                        this.requireContext(),
                        "Please enter a valid number!",
                        Toast.LENGTH_SHORT
                    ).show()

                if (str.takeLast(1).equals("1")
                    || str.takeLast(1).equals("2")
                    || str.takeLast(1).equals("3")
                    || str.takeLast(1).equals("4")
                    || str.takeLast(1).equals("5")
                    || str.takeLast(1).equals("6")
                    || str.takeLast(1).equals("7")
                    || str.takeLast(1).equals("8")
                    || str.takeLast(1).equals("9")
                    || str.takeLast(1).equals("0"))
                    tvMain.text = (tvMain.text.toString() + "*sin")
            }
            else
                tvMain.text = (tvMain.text.toString() + "sin")
        }
        bcos.setOnClickListener {
            val str: String = tvMain.text.toString()
            if (str.isNotEmpty()){
                if (str.get(index = str.length - 1).toString().equals(".")
                    || str.takeLast(3).equals("sin")
                    || str.takeLast(3).equals("cos")
                    || str.takeLast(3).equals("tan")
                    || str.takeLast(3).equals("log")
                    || str.takeLast(2).equals("ln"))
                    Toast.makeText(
                        this.requireContext(),
                        "Please enter a valid number!",
                        Toast.LENGTH_SHORT
                    ).show()

                if (str.takeLast(1).equals("1")
                    || str.takeLast(1).equals("2")
                    || str.takeLast(1).equals("3")
                    || str.takeLast(1).equals("4")
                    || str.takeLast(1).equals("5")
                    || str.takeLast(1).equals("6")
                    || str.takeLast(1).equals("7")
                    || str.takeLast(1).equals("8")
                    || str.takeLast(1).equals("9")
                    || str.takeLast(1).equals("0"))
                    tvMain.text = (tvMain.text.toString() + "*cos")
            }
            else
                tvMain.text = (tvMain.text.toString() + "cos")
        }
        btan.setOnClickListener {
            val str: String = tvMain.text.toString()
            if (str.isNotEmpty()){
                if (str.get(index = str.length - 1).toString().equals(".")
                    || str.takeLast(3).equals("sin")
                    || str.takeLast(3).equals("cos")
                    || str.takeLast(3).equals("tan")
                    || str.takeLast(3).equals("log")
                    || str.takeLast(2).equals("ln"))
                    Toast.makeText(
                        this.requireContext(),
                        "Please enter a valid number!",
                        Toast.LENGTH_SHORT
                    ).show()

                if (str.takeLast(1).equals("1")
                    || str.takeLast(1).equals("2")
                    || str.takeLast(1).equals("3")
                    || str.takeLast(1).equals("4")
                    || str.takeLast(1).equals("5")
                    || str.takeLast(1).equals("6")
                    || str.takeLast(1).equals("7")
                    || str.takeLast(1).equals("8")
                    || str.takeLast(1).equals("9")
                    || str.takeLast(1).equals("0"))
                    tvMain.text = (tvMain.text.toString() + "*tan")
            }
            else
                tvMain.text = (tvMain.text.toString() + "tan")
        }
//        binv.setOnClickListener {
//            tvMain.text = (tvMain.text.toString() + "^" + "(-1)")
//        }
        bln.setOnClickListener {
            val str: String = tvMain.text.toString()
            if (str.isNotEmpty()){
                if (str.get(index = str.length - 1).toString().equals(".")
                    || str.takeLast(3).equals("sin")
                    || str.takeLast(3).equals("cos")
                    || str.takeLast(3).equals("tan")
                    || str.takeLast(3).equals("log")
                    || str.takeLast(2).equals("ln"))
                    Toast.makeText(
                        this.requireContext(),
                        "Please enter a valid number!",
                        Toast.LENGTH_SHORT
                    ).show()

                if (str.takeLast(1).equals("1")
                    || str.takeLast(1).equals("2")
                    || str.takeLast(1).equals("3")
                    || str.takeLast(1).equals("4")
                    || str.takeLast(1).equals("5")
                    || str.takeLast(1).equals("6")
                    || str.takeLast(1).equals("7")
                    || str.takeLast(1).equals("8")
                    || str.takeLast(1).equals("9")
                    || str.takeLast(1).equals("0"))
                    tvMain.text = (tvMain.text.toString() + "*ln")
            }
            else
                tvMain.text = (tvMain.text.toString() + "ln")
        }
        blog.setOnClickListener {
            val str: String = tvMain.text.toString()
            if (str.isNotEmpty()){
                if (str.get(index = str.length - 1).toString().equals(".")
                    || str.takeLast(3).equals("sin")
                    || str.takeLast(3).equals("cos")
                    || str.takeLast(3).equals("tan")
                    || str.takeLast(3).equals("log")
                    || str.takeLast(2).equals("ln"))
                    Toast.makeText(
                        this.requireContext(),
                        "Please enter a valid number!",
                        Toast.LENGTH_SHORT
                    ).show()

                if (str.takeLast(1).equals("1")
                    || str.takeLast(1).equals("2")
                    || str.takeLast(1).equals("3")
                    || str.takeLast(1).equals("4")
                    || str.takeLast(1).equals("5")
                    || str.takeLast(1).equals("6")
                    || str.takeLast(1).equals("7")
                    || str.takeLast(1).equals("8")
                    || str.takeLast(1).equals("9")
                    || str.takeLast(1).equals("0"))
                    tvMain.text = (tvMain.text.toString() + "*log")
            }
            else
                tvMain.text = (tvMain.text.toString() + "log")
        }

        bminus.setOnClickListener {
            // при нажатии на - проверяем: у пользователя уже есть операция минус на экране
            // если операция минус уже присутствует
            // то ничего делать не будем
            val str: String = tvMain.text.toString()
            if (str.isEmpty() || str.get(index = str.length - 1).toString().equals(".")
                || str.get(index = str.length - 1).toString().equals("+")
//                || str.get(index = str.length - 1).toString().equals("-")
                || str.get(index = str.length - 1).toString().equals("*")
                || str.get(index = str.length - 1).toString().equals("/")
            )
                tvMain.text = (tvMain.text.toString() + "-")
            else    // если функции sin пустые
                if (str.takeLast(3).equals("sin")
                    || str.takeLast(3).equals("cos")
                    || str.takeLast(3).equals("tan")
                    || str.takeLast(3).equals("log")
                    || str.takeLast(2).equals("ln")
                ) {
                    Toast.makeText(
                        this.requireContext(),
                        "Please enter a valid number!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (!str.get(index = str.length - 1).toString().equals("-")) {
                    tvMain.text = (tvMain.text.toString() + "-")
                }
        }
        bmul.setOnClickListener {
            // при нажатии на * проверяем: у пользователя уже есть операция произведения на экране
            // если операция произведения уже присутствует
            // то ничего делать не будем
            val str: String = tvMain.text.toString()
            if (str.isEmpty() || str.get(index = str.length - 1).toString().equals(".")
                || str.get(index = str.length - 1).toString().equals("+")
                || str.get(index = str.length - 1).toString().equals("-")
                || str.get(index = str.length - 1).toString().equals("*")
                || str.get(index = str.length - 1).toString().equals("/")
                || str.takeLast(3).equals("sin")
                || str.takeLast(3).equals("cos")
                || str.takeLast(3).equals("tan")
                || str.takeLast(3).equals("log")
                || str.takeLast(2).equals("ln")
            ) {
                Toast.makeText(
                    this.requireContext(),
                    "Please enter a valid number!",
                    Toast.LENGTH_SHORT
                ).show()
            } else
                tvMain.text = (tvMain.text.toString() + "*")
        }
        bsqrt.setOnClickListener {
            val str: String = tvMain.text.toString()

            if (str.isEmpty() || str.get(index = str.length - 1).toString().equals(".")
                || str.get(index = str.length - 1).toString().equals("+")
                || str.get(index = str.length - 1).toString().equals("-")
                || str.get(index = str.length - 1).toString().equals("*")
                || str.get(index = str.length - 1).toString().equals("/")
                || str.takeLast(3).equals("sin")
                || str.takeLast(3).equals("cos")
                || str.takeLast(3).equals("tan")
                || str.takeLast(3).equals("log")
                || str.takeLast(2).equals("ln")
            ) {
                // если введенное число пусто или заканчивается мат.операцией, мы показываем сообщение об ошибке
                Toast.makeText(
                    this.requireContext(),
                    "Please enter a valid number!",
                    Toast.LENGTH_SHORT
                ).show()
            } else

                if (viewModel.evaluate(str) < 0
                )     // если число под корнем отрицательное
                    Toast.makeText(
                        this.requireContext(),
                        "Error! Negative number under root!",
                        Toast.LENGTH_SHORT
                    ).show()
                else {
                    val d = viewModel.evaluate(str)    // считаем число под корнем
                    // вычисляем квадратный корень из заданного числа
                    val sqrt = Math.sqrt(d)
                    val longRes = sqrt.toLong()

                    if (sqrt == longRes.toDouble())
                        tvMain.setText(longRes.toString())  // пишем целое число в ответе
                     else
                        tvMain.setText(sqrt.toString())  // пишем рац число в ответе

                    if (d==d.toLong().toDouble())
                        tvsec.text = "√${d.toInt()}"
                    else
                        tvsec.text = "√${d}"
                }
        }
        bequal.setOnClickListener {
            val str: String = tvMain.text.toString()
            println(bracket_count)
            if (bracket_count!=0)
                Toast.makeText(
                    this.requireContext(),
                    "Error! Brackets '(' and ')' placed incorrectly!",
                    Toast.LENGTH_SHORT
                ).show()
            else {
                // в конце строки не должно быть каких-то операций и точки
                if (str.isEmpty() || str.get(index = str.length - 1).toString().equals(".")
                    || str.get(index = str.length - 1).toString().equals("+")
                    || str.get(index = str.length - 1).toString().equals("-")
                    || str.get(index = str.length - 1).toString().equals("*")
                    || str.get(index = str.length - 1).toString().equals("/")
                    || str.get(index = str.length - 1).toString().equals("^")
                    || str.get(index = str.length - 1).toString().equals("(")
                    || str.takeLast(3).equals("sin")
                    || str.takeLast(3).equals("cos")
                    || str.takeLast(3).equals("tan")
                    || str.takeLast(3).equals("log")
                    || str.takeLast(2).equals("ln")
                ) {
                    Toast.makeText(
                        this.requireContext(),
                        "Please enter a valid number!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    // по кнопке равенства (=) вычисляется результат
                    val result = viewModel.evaluate(str)
                    val longRes = result.toLong()
                    if (result == longRes.toDouble())
                        tvMain.setText(longRes.toString()) // пишем целое число в ответе
                    else
                        tvMain.setText(result.toString()) // пишем рац число в ответе
                    tvsec.text = str
                }
            }
        }
        bac.setOnClickListener {
            // при нажатии на кнопку очищаются основное и дополнительное текстовое представление
            tvMain.setText("")
            tvsec.setText("")
            bracket_count=0     // сбрасываем счётчик скобок
        }
        bc.setOnClickListener {
            // при нажатии на кнопку очищается последний символ,
            // также проверяется длина строки
            var str: String = tvMain.text.toString()

            if (str.isNotEmpty()) {
                if (str.takeLast(1).equals("s") || str.takeLast(1).equals("g"))
                    str = str.substring(0, str.length - 3)
                else if (str.takeLast(1).equals("n"))
                    if (str.takeLast(2).equals("ln"))
                        str = str.substring(0, str.length - 2)
                    else    // tan
                        str = str.substring(0, str.length - 3)
                else{
                    if (str.takeLast(1).equals(")"))
                        bracket_count += 1
                    if (str.takeLast(1).equals("("))
                        bracket_count -= 1
                    str = str.substring(0, str.length - 1)
                }
                tvMain.text = str
            }
        }
        bsquare.setOnClickListener {
            val str: String = tvMain.text.toString()
            if (str.isEmpty() || str.get(index = str.length - 1).toString().equals(".")
                || str.get(index = str.length - 1).toString().equals("+")
                || str.get(index = str.length - 1).toString().equals("-")
                || str.get(index = str.length - 1).toString().equals("*")
                || str.get(index = str.length - 1).toString().equals("/")
                || str.takeLast(3).equals("sin")
                || str.takeLast(3).equals("cos")
                || str.takeLast(3).equals("tan")
                || str.takeLast(3).equals("log")
                || str.takeLast(2).equals("ln")
            ) {
                // если введенное число пусто или заканчивается мат.операцией, мы показываем сообщение об ошибке
                Toast.makeText(
                    this.requireContext(),
                    "Please enter a valid number!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                // получаем значение и вычисляем квадрат числа
                val d = viewModel.evaluate(str)    // считаем число (выражение в скобках)
                val square = d * d
                val longRes = square.toLong()
                if (square == longRes.toDouble()) {
                    tvMain.setText(longRes.toString()) // пишем целое число в ответе
                    tvsec.text = "${d.toInt()}²"
                } else {
                    tvMain.setText(square.toString()) // пишем рац число в ответе
                    tvsec.text = "$d²"
                }
            }
        }
        bfact.setOnClickListener {
            val str: String = tvMain.text.toString()
            if (str.isEmpty() || str.get(index = str.length - 1).toString().equals(".")
                || str.get(index = str.length - 1).toString().equals("+")
                || str.get(index = str.length - 1).toString().equals("-")
                || str.get(index = str.length - 1).toString().equals("*")
                || str.get(index = str.length - 1).toString().equals("/")
                || str.takeLast(3).equals("sin")
                || str.takeLast(3).equals("cos")
                || str.takeLast(3).equals("tan")
                || str.takeLast(3).equals("log")
                || str.takeLast(2).equals("ln")) {
                // если введенное число пусто или заканчивается мат.операцией, мы показываем сообщение об ошибке
                Toast.makeText(
                    this.requireContext(),
                    "Please enter a valid number!",
                    Toast.LENGTH_SHORT
                ).show()
            } else  // если число под факториалом отрицательное
                if (viewModel.evaluate(str).toDouble() < 0)
                Toast.makeText(
                    this.requireContext(),
                    "Error! Negative number under factorial!",
                    Toast.LENGTH_SHORT).show()
            else {
                val res = viewModel.evaluate(str)   // вычисляем выражение под факториалом
                val longRes = res.toLong()
                if (res==longRes.toDouble()){
                    // пишем целое число в ответе
                    val value: Int = res.toString().toDouble().toInt()
                    val fact: Int = viewModel.factorial(value)
                    tvMain.setText(fact.toString())
                    tvsec.text = "${value}!"
                }
                else
                    Toast.makeText(
                        this.requireContext(),
                        "Error! Rational number under factorial!",
                        Toast.LENGTH_SHORT
                    ).show()

                }
        }

        return binding.root
    }

}