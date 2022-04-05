package com.example.myapplication.Fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_fragment.*
import kotlinx.android.synthetic.main.bottom_sheet.*

class FragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        val gt=Udetails()
        supportFragmentManager.beginTransaction().replace(R.id.container,gt).commit()
        btn_male_filter.setOnClickListener {
            Toast.makeText(this,"Male btn is clicked",Toast.LENGTH_LONG).show()
        }
    }
}