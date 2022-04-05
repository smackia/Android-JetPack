package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_sactivity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SActivity : AppCompatActivity() {
    var data = ArrayList<Data>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sactivity)
        val test = intent.getStringArrayListExtra("DATA")
        getDataFromApi()
        btn_goback.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        btn_filter.setOnClickListener {
            bottomSheet()
        }
    }

    private fun getDataFromApi() {
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)
        val apiInterface = ApiInterface.create().getData()
        apiInterface.enqueue(object : Callback<getData> {
            override fun onResponse(call: Call<getData>?, response: Response<getData>?) {
                if (response!!.isSuccessful) {
                    data = response.body()?.data as ArrayList<Data>
//                    Toast.makeText(applicationContext, data.toString(), Toast.LENGTH_LONG).show()
                    val adapter = CustomAdaptor(data, applicationContext)
                    recyclerview.adapter = adapter
//                    filterData(data)
//                    namesSort(data)
                }
            }

            override fun onFailure(call: Call<getData>?, t: Throwable?) {
                Toast.makeText(applicationContext, t?.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun filterData(data: ArrayList<Data>, gender: Int) {
        val maleData = ArrayList<Data>()
        val femaleData = ArrayList<Data>()
        for (i in 0 until data.size) {
            if (data[i].gender == "male")
                maleData.add(data[i])
            else
                femaleData.add(data[i])
        }
        if (gender == 0)
            dataFill(maleData)
        else
            dataFill(femaleData)
//        namesSort(maleData,femaleData)
//        Toast.makeText(this,maleData.toString(),Toast.LENGTH_LONG).show()
    }

    private fun namesSort(filterData: ArrayList<Data>) {
        for (i in 0 until filterData.size) {
            for (j in i + 1 until filterData.size) {
                // to compare one string with other strings
                if (filterData[i].name > filterData[j].name) {
                    // swapping
                    var temp = filterData[i]
                    filterData[i] = filterData[j]
                    filterData[j] = temp

                }
            }
        }
        dataFill(data)

//        mergeSort(male)
//        mergeSort(female)
//        merge(filterData, male, female)
//        mergeSort(filterData)
//        Toast.makeText(this,filterData.toString(),Toast.LENGTH_LONG).show()
    }


    private fun bottomSheet() {
        val dialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.bottom_sheet, null)
        val btnClose = view.findViewById<Button>(R.id.btn_close)
        val btnNameFilter = view.findViewById<Button>(R.id.btn_name_filter)
        val btnMaleFilter = view.findViewById<Button>(R.id.btn_male_filter)
        val btnFemaleFilter = view.findViewById<Button>(R.id.btn_female_filter)


        btnClose.setOnClickListener {
            dialog.dismiss()
        }
        btnNameFilter.setOnClickListener {
            namesSort(data)
            dialog.dismiss()
        }

        btnMaleFilter.setOnClickListener {
            filterData(data, 0)
            dialog.dismiss()
        }

        btnFemaleFilter.setOnClickListener {
            filterData(data, 1)
            dialog.dismiss()
        }

        dialog.setCancelable(false)
        dialog.setContentView(view)
        dialog.show()

    }

    private fun dataFill(data: ArrayList<Data>) {
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(this)
        val adapter = CustomAdaptor(data, this)
        recyclerview.adapter = adapter
    }

}


//
//   private fun mergeSort(names:ArrayList<Data>) {
//        if (names.size >= 2) {
//            val left = ArrayList<Data>(names.size / 2)
//            val right = ArrayList<Data>(names.size - names.size / 2)
//            for (i in left.indices) {
//                left[i] = names[i]
//            }
//            for (i in right.indices) {
//                right[i] = names[i + names.size / 2]
//            }
//            mergeSort(left)
//            mergeSort(right)
//            merge(names, left, right)
//        }
//    }
//
//   private fun merge(names: ArrayList<Data>, left: ArrayList<Data>, right: ArrayList<Data>) {
//        var a = 0
//        var b = 0
//        for (i in names.indices) {
//            if (b >= right.size || a < left.size && left[a].name.compareTo(
//                    right[b].name,
//                    ignoreCase = true
//                ) < 0
//            ) {
//                names[i] = left[a]
//                a++
//            } else {
//                names[i] = right[b]
//                b++
//            }
//        }
//    }
