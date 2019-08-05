package ru.skillbranch.devintensive.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_profile.*
import ru.skillbranch.devintensive.R

class ProfileActivity : AppCompatActivity() {
    companion object {
        const val IS_EDIT_MODE = "IS_EDIT_MODE"
    }

    var isEditMode = false
    lateinit var viewFields: Map<String, TextView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        Log.d("M_MainActivity.kt", "onCreate")
        initViews(savedInstanceState)
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

    private fun initViews(savedInstanceState: Bundle?) {
        viewFields = mapOf(
            "nickname" to tv_nick_name,
            "rank" to tv_rank,
            "firstName" to et_first_name,
            "lastName" to et_last_name,
            "about" to et_about,
            "repository" to et_repository,
            "rating" to tv_rating,
            "respect" to tv_respect
        )

        btn_edit.setOnClickListener {

            isEditMode = isEditMode.not()
            showCurrentMode(isEditMode)
        }
    }

    private fun showCurrentMode(isEdit: Boolean) {
        val info = viewFields.filter { setOf("firstName", "lastName", "about", "repository").contains(it.key) }

        for((_,v) in info){
            v as EditText
            v.isFocusable = isEdit
            v.isFocusableInTouchMode = isEdit
            v.isEnabled = isEdit
            v.background.alpha = if(isEdit) 255 else 0
        }

        ic_eye.visibility = if(isEdit) View.GONE else View.VISIBLE
        wr_about.isCounterEnabled = isEdit
    }

}
