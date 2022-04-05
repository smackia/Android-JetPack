package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private var data = ArrayList<String>()
//    private val day = arrayOf<Any>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
//    private val month = arrayOf<Any>("Jan", "Feb", "March", "April", "May");
//    private val year = arrayOf<Any>(2001, 2002, 2003, 2004, 2005, 2006, 2007, 2008, 2009, 2010)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUi()
    }



    private fun setupUi(){
        spinnerFill(arrayOf<Any>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), spin_date)
        spinnerFill(arrayOf<Any>("Jan", "Feb", "March", "April", "May"), spin_month)
        spinnerFill(arrayOf<Any>(2001, 2002, 2003, 2004, 2005, 2006, 2007, 2008, 2009, 2010), spin_year)

        btn_submit.setOnClickListener {
            if (validation()) {
                val intent = Intent(this, SActivity::class.java)
                intent.putStringArrayListExtra("DATA", data)
                startActivity(intent)
                finish()
            }
        }

    }

    private fun validation(): Boolean {
        var firstName = et_firstName.text.toString();
        var lastName = et_lastName.text.toString();
        var email = et_email.text.toString();
        var phoneNumber = et_phone.text.toString();
        var flag = 0;
        if (firstName.isEmpty()) {
            popUp("First Name")
            return false
        } else if (lastName.isEmpty()) {
            popUp("Last Name")
            return false
        } else if (!emailValidation(email)) {
            popUp("Email")
            return false
        } else if (phoneNumber.isEmpty()) {
            popUp("Phone")
            return false
        } else if (phoneNumber.length != 10) {
            popUp("Phone")
            return false
        }
        flag = 1;
        if (flag == 1) {
            data.add(firstName)
            data.add(lastName)
            data.add(email)
            data.add(phoneNumber)
            return true
        }
        return true
    }

    private fun popUp(s: String) {
        Toast.makeText(this, s + "Can not be Empty", Toast.LENGTH_LONG).show()
    }

    private fun emailValidation(email: String): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        if (email.isEmpty())
            return false
        else if (email.matches(emailPattern.toRegex())) {
            return true
        }
        return false
    }

    private fun spinnerFill(data: Array<Any>, spin_id: Spinner) {
        spin_id.onItemSelectedListener = this
        val ad: ArrayAdapter<*> = ArrayAdapter<Any?>(
            this,
            android.R.layout.simple_spinner_item,
            data
        )

        ad.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )


        spin_id.adapter = ad
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        p0?.getItemAtPosition(p2)

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }



}