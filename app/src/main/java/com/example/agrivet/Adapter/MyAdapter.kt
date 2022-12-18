package com.example.agrivet.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import com.example.agrivet.Models.Veterinarian
import com.example.agrivet.R

class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private val userList = ArrayList<Veterinarian>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.veterinarian_item,
            parent,false
        )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = userList[position]

        holder.name.text=currentitem.name
        holder.vetEmail.text=currentitem.email
        holder.contact.text=currentitem.contactNumber
        holder.link.text=currentitem.gmeet
        holder.clinic.text=currentitem.clinicAddress



    }

    override fun getItemCount(): Int {
       return userList.size
    }

    fun updateUserList(userList : List<Veterinarian>){

        this.userList.clear()
        this.userList.addAll(userList)
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val name : TextView = itemView.findViewById(R.id.tvname)
        val vetEmail : TextView = itemView.findViewById(R.id.tvemail)
        val contact : TextView = itemView.findViewById(R.id.tvcontactNumber)
        val link : TextView = itemView.findViewById(R.id.tvlink)
        val clinic : TextView = itemView.findViewById(R.id.tvclinic)


    }
}