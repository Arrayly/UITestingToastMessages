package com.example.uitestingtoastmessages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun showToastClicked(view: View) {
        Toast.makeText(this,resources.getString(R.string.toast_message),Toast.LENGTH_SHORT).show()
    }
}
