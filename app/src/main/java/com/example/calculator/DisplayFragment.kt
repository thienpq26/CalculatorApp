package com.example.calculator

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_display.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class DisplayFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_display, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val sharedPreferences =
            activity!!.getSharedPreferences("data_result", Context.MODE_PRIVATE)
        var result = sharedPreferences.getString("result", null)
        if (result != null) {
            text_one.text = result
        }
    }

    fun setTextDisplay(message: String, type: Boolean) {
        if (!type) {
            when (message) {
                "=" -> {
                    if (text_one.text.isEmpty() || text_two.text.isEmpty()) {
                        if (!text_one.text.toString().contains("sin") && !text_one.text.toString().contains("cos")) {
                            Toast.makeText(context, "Input number, please!!", Toast.LENGTH_SHORT).show()
                        } else if (!text_one.text.toString().contains(")")) {
                            Toast.makeText(context, "Input number, please!!", Toast.LENGTH_SHORT).show()
                        } else {
                            text_two.text = "0+"
                            calculating()
                            text_three.text = ""
                        }
                    } else if (text_one.text.toString().compareTo("%") != 0) {
                        calculating()
                    } else {
                        text_three.text = ""
                        var x = text_two.text.toString().toDouble()
                        text_two.text = text_two.text.toString() + "%"
                        text_one.text = "= " + (x / 100)
                    }
                }
                "AC" -> {
                    text_one.text = ""
                    text_two.text = ""
                    text_three.text = ""
                }
                "+/-" -> {
                    text_one.text = text_one.text.toString().substring(0, text_one.text.length - 1)
                }
                "." -> {
                    if (text_one.text.isEmpty()) {
                        Toast.makeText(context, "Error number!", Toast.LENGTH_SHORT).show()
                    } else {
                        text_one.append(message)
                    }
                }
                else -> {
                    if (text_one.text.toString().contains("(")) {
                        text_one.append(message)
                    } else if (message.compareTo(")") == 0) {
                        text_one.append(message)
                    } else if (text_one.text.toString().compareTo("/") == 0 || text_one.text.toString().compareTo("*") == 0 ||
                        text_one.text.toString().compareTo("-") == 0 || text_one.text.toString().compareTo("+") == 0) {
                        text_one.append(message)
                    } else {
                        text_three.text = ""
                        if (text_one.text.toString().contains("=")) {
                            text_two.text =
                                text_one.text.substring(text_one.text.indexOf("=") + 1, text_one.text.length)
                        } else {
                            text_two.text = text_one.text.toString()
                        }
                        text_one.text = message
                    }
                }
            }
        } else {
            text_one.append(message)
        }
    }

    fun calculating() {
        try {
            text_three.text = text_two.text.toString()
            text_two.text = text_one.text.toString()
            val expression =
                ExpressionBuilder(text_three.text.toString() + text_two.text.toString()).build()
            val result = expression.evaluate()
            val longResult = result.toLong()
            if (result == longResult.toDouble()) text_one.text = "= " + longResult.toString()
            else text_one.text = "= " + result.toString()
        } catch (e: Exception) {
            Log.d("d", "msg_exception: " + e.message)
        }
    }

    fun setAC() {
        text_one.text = ""
        text_two.text = ""
        text_three.text = ""
    }

    fun saveResult() {
        var result = text_one.text.toString().substring(2, text_one.text.length)
        val sharedPreferences =
            activity!!.getSharedPreferences("data_result", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("result", result)
        editor.commit()
    }
}

