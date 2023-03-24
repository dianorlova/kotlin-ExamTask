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
        val binding = DataBindingUtil.inflate<FragmentCalculatorBinding>(inflater,
            R.layout.fragment_calculator,
            container, false)
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
            tvMain.text = (tvMain.text.toString() + "0")
        }
        bdot.setOnClickListener {
            tvMain.text = (tvMain.text.toString() + ".")
        }
        bplus.setOnClickListener {
            tvMain.text = (tvMain.text.toString() + "+")
        }
        bdiv.setOnClickListener {
            if (tvMain.text.isEmpty())
                Toast.makeText(this.requireContext(), "Please enter a valid number!", Toast.LENGTH_SHORT).show()
            else
                tvMain.text = (tvMain.text.toString() + "/")
        }
        bbrac1.setOnClickListener {
            tvMain.text = (tvMain.text.toString() + "(")
        }
        bbrac2.setOnClickListener {
            tvMain.text = (tvMain.text.toString() + ")")
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
            tvMain.text = (tvMain.text.toString() + "sin")
        }
        bcos.setOnClickListener {
            tvMain.text = (tvMain.text.toString() + "cos")
        }
        btan.setOnClickListener {
            tvMain.text = (tvMain.text.toString() + "tan")
        }
//        binv.setOnClickListener {
//            tvMain.text = (tvMain.text.toString() + "^" + "(-1)")
//        }
        bln.setOnClickListener {
            tvMain.text = (tvMain.text.toString() + "ln")
        }
        blog.setOnClickListener {
            tvMain.text = (tvMain.text.toString() + "log")
        }

        bminus.setOnClickListener {
            // при нажатии на - проверяем: у пользователя уже есть операция минус на экране
            // если операция минус уже присутствует
            // то ничего делать не будем
            val str: String = tvMain.text.toString()
            if (!str.get(index = str.length - 1).toString().equals("-")) {
                tvMain.text = (tvMain.text.toString() + "-")
            }
        }
        bmul.setOnClickListener {
            // при нажатии на * проверяем: у пользователя уже есть операция произведения на экране
            // если операция произведения уже присутствует
            // то ничего делать не будем
            val str: String = tvMain.text.toString()
            if (!str.get(index = str.length - 1).toString().equals("*")) {
                tvMain.text = (tvMain.text.toString() + "*")
            }
        }
        bsqrt.setOnClickListener {
            val str: String = tvMain.text.toString()
            if (str.isEmpty()) {
                // если введенное число пусто, мы показываем сообщение об ошибке
                Toast.makeText(this.requireContext(), "Please enter a valid number!", Toast.LENGTH_SHORT).show()
            } else {
                // вычисляем квадратный корень из заданного числа
                val r = Math.sqrt(str.toDouble())
                // получаем результат и выводим в виде строки
                val result = r.toString()
                tvMain.setText(result)
            }
        }
        bequal.setOnClickListener {
            val str: String = tvMain.text.toString()
            // по кнопке равенства (=) вычисляется результат
            val result: Double = viewModel.evaluate(str)
            // получаем результат и выводим в виде строки
            val r = result.toString()
            tvMain.setText(r)
            tvsec.text = str
        }
        bac.setOnClickListener {
            // при нажатии на кнопку очищаются основное и дополнительное текстовое представление
            tvMain.setText("")
            tvsec.setText("")
        }
        bc.setOnClickListener {
            // при нажатии на кнопку очищается последний символ,
            // также проверяется длина строки
            var str: String = tvMain.text.toString()
            if (!str.equals("")) {
                str = str.substring(0, str.length - 1)
                tvMain.text = str
            }
        }
        bsquare.setOnClickListener {
            if (tvMain.text.toString().isEmpty()) {
                // если введенное число пусто, мы показываем сообщение об ошибке
                Toast.makeText(this.requireContext(), "Please enter a valid number!", Toast.LENGTH_SHORT).show()
            } else {
                // получаем значение и вычисляем квадрат числа
                val d: Double = tvMain.getText().toString().toDouble()
                val square = d * d
                tvMain.setText(square.toString())
                tvsec.text = "$d²"
            }
        }
        bfact.setOnClickListener {
            if (tvMain.text.toString().isEmpty()) {
                // if the entered number is empty we are displaying an error message.
                Toast.makeText(this.requireContext(), "Please enter a valid number!", Toast.LENGTH_SHORT).show()
            } else {
                // on below line we are getting int value
                // and calculating the factorial value of the entered number.
                val value: Int = tvMain.text.toString().toInt()
                val fact: Int = viewModel.factorial(value)
                tvMain.setText(fact.toString())
                tvsec.text = "$value`!"
            }

        }

        return binding.root
    }






}