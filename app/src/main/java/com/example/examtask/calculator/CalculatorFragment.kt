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
    lateinit var bpercent: Button
    lateinit var bdegree: Button
    lateinit var bsquarerootdegree: Button

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

    private var bracket_count: Int = 0  // считает количество открытых и закрытых скобок\
    private var tvsecString = ""
    private var tvmainString = ""

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
        bdegree = binding.bdegree
        bpercent = binding.bpercent
        bsquarerootdegree = binding.bsquarerootdegree

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

        // Если у нас есть какие-то сохраненные зеначения (не равны null), то записываем эти значения в наши переменные
        if (savedInstanceState != null) {
            bracket_count = savedInstanceState.getInt("key_bracket_count")
            tvmainString = savedInstanceState.getString("key_tvmain_string").toString()
            tvsecString = savedInstanceState.getString("key_tvsec_string").toString()

            tvMain.text = tvmainString
            tvsec.text = tvsecString
        }

        // Устанавливаем слушателей по нажатию на соответствующие кнопки
        b1.setOnClickListener {
            tvMain.text = (tvMain.text.toString() + "1")
            tvmainString = tvMain.text.toString()

        }
        b2.setOnClickListener {
            tvMain.text = (tvMain.text.toString() + "2")
            tvmainString = tvMain.text.toString()
        }
        b3.setOnClickListener {
            tvMain.text = (tvMain.text.toString() + "3")
            tvmainString = tvMain.text.toString()
        }
        b4.setOnClickListener {
            tvMain.text = (tvMain.text.toString() + "4")
            tvmainString = tvMain.text.toString()
        }
        b5.setOnClickListener {
            tvMain.text = (tvMain.text.toString() + "5")
            tvmainString = tvMain.text.toString()
        }
        b6.setOnClickListener {
            tvMain.text = (tvMain.text.toString() + "6")
            tvmainString = tvMain.text.toString()
        }
        b7.setOnClickListener {
            tvMain.text = (tvMain.text.toString() + "7")
            tvmainString = tvMain.text.toString()
        }
        b8.setOnClickListener {
            tvMain.text = (tvMain.text.toString() + "8")
            tvmainString = tvMain.text.toString()
        }
        b9.setOnClickListener {
            tvMain.text = (tvMain.text.toString() + "9")
            tvmainString = tvMain.text.toString()
        }
        b0.setOnClickListener {
            val str: String = tvMain.text.toString()
            // проверка, чтобы вначале был только один 0
            if (!str.equals("0")) {
                tvMain.text = (tvMain.text.toString() + "0")
                tvmainString = tvMain.text.toString()
            }
        }
        bdot.setOnClickListener {
            val str: String = tvMain.text.toString()
            // проверка, что вначале нет точки без целой части числа
            if (!str.equals(".") and !str.isEmpty()) {
                tvMain.text = (tvMain.text.toString() + ".")
            } else
                tvMain.text = (tvMain.text.toString() + "0.")
            tvmainString = tvMain.text.toString()
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
            tvmainString = tvMain.text.toString()
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
            tvmainString = tvMain.text.toString()
        }
        bbrac1.setOnClickListener {
            tvMain.text = (tvMain.text.toString() + "(")
            tvmainString = tvMain.text.toString()
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
                    tvmainString = tvMain.text.toString()
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
            tvmainString = tvMain.text.toString()
            tvsec.text = bpi.text.toString()
            tvsecString = tvsec.text.toString()
        }
        be.setOnClickListener {
            // при нажатии на кнопку e, к строке значения добавляется значение числа e с точностью в 10 знаков после запятой
            tvMain.text = (tvMain.text.toString() + "2.7182818284")
            tvmainString = tvMain.text.toString()
            tvsec.text = be.text.toString()
            tvsecString = tvsec.text.toString()
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
            tvmainString = tvMain.text.toString()
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
            tvmainString = tvMain.text.toString()
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
            tvmainString = tvMain.text.toString()
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
            tvmainString = tvMain.text.toString()
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
            tvmainString = tvMain.text.toString()
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
            tvmainString = tvMain.text.toString()
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
            tvmainString = tvMain.text.toString()
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

                if (viewModel.calculate(str) < 0
                )     // если число под корнем отрицательное
                    Toast.makeText(
                        this.requireContext(),
                        "Error! Negative number under root!",
                        Toast.LENGTH_SHORT
                    ).show()
                else {
                    val d = viewModel.calculate(str)    // считаем число под корнем
                    // вычисляем квадратный корень из заданного числа
                    val sqrt = viewModel.getSqrt(d)
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
            tvmainString = tvMain.text.toString()
            tvsecString = tvsec.text.toString()
        }

        bdegree.setOnClickListener {
            tvMain.text = tvMain.text.toString() + "^"
            tvmainString = tvMain.text.toString()
        }

        bsquarerootdegree.setOnClickListener {
            tvMain.text = tvMain.text.toString() + "^(1/"
            bracket_count += 1
            tvmainString = tvMain.text.toString()
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
                    val result = viewModel.calculate(str)
                    val longRes = result.toLong()
                    if (result == longRes.toDouble())
                        tvMain.setText(longRes.toString()) // пишем целое число в ответе
                    else
                        tvMain.setText(result.toString()) // пишем рац число в ответе
                    tvsec.text = str
                }
            }
            tvmainString = tvMain.text.toString()
            tvsecString = tvsec.text.toString()
        }
        bac.setOnClickListener {
            // при нажатии на кнопку очищаются основное и дополнительное текстовое представление
            tvMain.setText("")
            tvsec.setText("")
            tvmainString = tvMain.text.toString()
            tvsecString = tvsec.text.toString()
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
            tvmainString = tvMain.text.toString()
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
                val d = viewModel.calculate(str)    // считаем число (выражение в скобках)
                val square = viewModel.square(d)
                val longRes = square.toLong()
                if (square == longRes.toDouble()) {
                    tvMain.setText(longRes.toString()) // пишем целое число в ответе
                    tvsec.text = "${d.toInt()}²"
                } else {
                    tvMain.setText(square.toString()) // пишем рац число в ответе
                    tvsec.text = "$d²"
                }
            }
            tvmainString = tvMain.text.toString()
            tvsecString = tvsec.text.toString()
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
                if (viewModel.calculate(str).toDouble() < 0)
                Toast.makeText(
                    this.requireContext(),
                    "Error! Negative number under factorial!",
                    Toast.LENGTH_SHORT).show()
            else {
                val res = viewModel.calculate(str)   // вычисляем выражение под факториалом
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
            tvmainString = tvMain.text.toString()
            tvsecString = tvsec.text.toString()
        }

        bpercent.setOnClickListener {
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
            } else  // если число под процентом отрицательное
                if (viewModel.calculate(str).toDouble() < 0)
                    Toast.makeText(
                        this.requireContext(),
                        "Error! Negative number under percent!",
                        Toast.LENGTH_SHORT).show()
                else {
                    val res = viewModel.calculate(str)   // вычисляем выражение под процентом
                    val longRes = res.toLong()
                    // пишем вещественное число в ответе
                    val value: Double = res.toString().toDouble()
                    val percent: Double = viewModel.percent(value)
                    tvMain.setText(percent.toString())
                    if (res==longRes.toDouble()){
                        tvsec.text = "${value.toInt()}%"
                    }
                    else
                        tvsec.text = "${value}%"
                }
            tvmainString = tvMain.text.toString()
            tvsecString = tvsec.text.toString()
        }


        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("key_bracket_count", bracket_count)
        outState.putString("key_tvmain_string", tvmainString)
        outState.putString("key_tvsec_string", tvsecString)
    }
}