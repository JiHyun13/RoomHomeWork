package com.example.roomhomeworkdefaultrepo


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(val data: ArrayList<User>, private val onClick: (data : User, position : Int)-> Unit) : RecyclerView.Adapter<Adapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name = itemView.findViewById<TextView>(R.id.name_tv)
        val birthday = itemView.findViewById<TextView>(R.id.birthday_tv)
        val email = itemView.findViewById<TextView>(R.id.email_tv)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.activity_main, parent, false)
        val holder = ViewHolder(v)

        v.setOnClickListener{
            onClick.invoke(data[holder.adapterPosition], holder.adapterPosition)
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = data[position].name
        holder.birthday.text = data[position].birthday
        holder.email.text = data[position].email
    }

    override fun getItemCount(): Int = data.size

}