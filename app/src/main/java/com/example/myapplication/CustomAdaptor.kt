package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Fragment.FragmentActivity
import com.example.myapplication.Fragment.Udetails

class CustomAdaptor(private val mList: ArrayList<Data>?, private val context:Context) :
    RecyclerView.Adapter<CustomAdaptor.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_view_design, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val dataClass = mList!![position]
        holder.firstName.text = dataClass.name
        holder.lastName.text = dataClass.gender
        holder.email.text = dataClass.email
        holder.phone.text = dataClass.status
        holder.itemView.setOnClickListener {
        context.startActivity(Intent(context,FragmentActivity::class.java))
        }

    }
    override fun getItemCount(): Int {
        return mList!!.size
    }
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val firstName: TextView = itemView.findViewById(R.id.tv_r_firstName)
        val lastName: TextView = itemView.findViewById(R.id.tv_r_lastName)
        val email: TextView = itemView.findViewById(R.id.tv_r_email)
        val phone: TextView = itemView.findViewById(R.id.tv_r_phone)
    }
}