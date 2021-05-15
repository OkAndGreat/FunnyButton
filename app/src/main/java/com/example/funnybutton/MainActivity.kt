package com.example.funnybutton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {
    private val btn: Button by lazy { findViewById(R.id.btn) }
    private val ll: LinearLayout by lazy { findViewById(R.id.ll) }
    private val layoutParams = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.WRAP_CONTENT,
        40.dp.toInt()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        layoutParams.setMargins(300,300,300,300)
        layoutParams.gravity=Gravity.CENTER
        val toAddBtn=Button(this)
        toAddBtn.text="test"
        toAddBtn.setBackgroundResource(R.drawable.shape_btn_bg)
        toAddBtn.setPadding(10,10,10,10)
        toAddBtn.layoutParams=layoutParams
        btn.setOnClickListener {
            ll.removeAllViews()
            ll.addView(toAddBtn,layoutParams)
        }
    }
}