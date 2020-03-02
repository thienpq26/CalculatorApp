package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

class MainActivity : AppCompatActivity(), OnFragmentKeyboardListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun sendMessage(message: String, type: Boolean) {
        val manage = supportFragmentManager
        val fragmentDisplay = manage.findFragmentById(R.id.fragment_display) as DisplayFragment
        fragmentDisplay.setTextDisplay(message, type)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val manage = supportFragmentManager
        val fragmentDisplay = manage.findFragmentById(R.id.fragment_display) as DisplayFragment
        when (item.itemId) {
            R.id.menu_clear -> fragmentDisplay.setAC()
            R.id.menu_save -> fragmentDisplay.saveResult()
        }
        return super.onOptionsItemSelected(item)
    }
}

