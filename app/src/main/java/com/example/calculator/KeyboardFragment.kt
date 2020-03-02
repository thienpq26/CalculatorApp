package com.example.calculator

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_keyboard.*
import java.lang.RuntimeException

class KeyboardFragment : Fragment() {

    var listener: OnFragmentKeyboardListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_keyboard, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //numbers
        button_num0.setOnClickListener { listener!!.sendMessage("0", true) }
        button_num1.setOnClickListener { listener!!.sendMessage("1", true) }
        button_num2.setOnClickListener { listener!!.sendMessage("2", true) }
        button_num3.setOnClickListener { listener!!.sendMessage("3", true) }
        button_num4.setOnClickListener { listener!!.sendMessage("4", true) }
        button_num5.setOnClickListener { listener!!.sendMessage("5", true) }
        button_num6.setOnClickListener { listener!!.sendMessage("6", true) }
        button_num7.setOnClickListener { listener!!.sendMessage("7", true) }
        button_num8.setOnClickListener { listener!!.sendMessage("8", true) }
        button_num9.setOnClickListener { listener!!.sendMessage("9", true) }

        //operators
        button_ac.setOnClickListener { listener!!.sendMessage("AC", false) }
        button_backspace.setOnClickListener { listener!!.sendMessage("+/-", false) }
        button_percent.setOnClickListener { listener!!.sendMessage("%", false) }
        button_div.setOnClickListener { listener!!.sendMessage("/", false) }
        button_mul.setOnClickListener { listener!!.sendMessage("*", false) }
        button_sub.setOnClickListener { listener!!.sendMessage("-", false) }
        button_sum.setOnClickListener { listener!!.sendMessage("+", false) }
        button_result.setOnClickListener { listener!!.sendMessage("=", false) }
        button_dot.setOnClickListener { listener!!.sendMessage(".", false) }
        button_left.setOnClickListener { listener!!.sendMessage("(", false) }
        button_right.setOnClickListener { listener!!.sendMessage(")", false) }
        button_sin.setOnClickListener { listener!!.sendMessage("sin(", false) }
        button_cos.setOnClickListener { listener!!.sendMessage("cos(", false) }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentKeyboardListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentKeyboardListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}
