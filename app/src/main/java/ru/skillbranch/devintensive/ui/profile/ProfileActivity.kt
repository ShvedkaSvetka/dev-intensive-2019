package ru.skillbranch.devintensive.ui.profile

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.skillbranch.devintensive.R
import ru.skillbranch.devintensive.models.Bender

class ProfileActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("M_MainActivity.kt", "onCreate")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("M_MainActivity.kt", "Restart")
    }

    override fun onStart() {
        super.onStart()
        Log.d("M_MainActivity.kt", "onStart")

    }

    override fun onResume() {
        super.onResume()
        Log.d("M_MainActivity.kt", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("M_MainActivity.kt", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("M_MainActivity.kt", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("M_MainActivity.kt", "onDestory")
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        Log.d("M_MainActivity.kt", "onSaveInstanceState")
    }

}
