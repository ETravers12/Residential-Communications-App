package com.capstone.residentialcommunicationsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.capstone.residentialcommunicationsapp.datamodels.Notifications

class Adapter(val notificationsList : MutableList<Notifications>) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val value = LayoutInflater.from(parent?.context).inflate(R.layout.tenant_home_recycler_view, parent, false)
        return ViewHolder(value)
    }

    override fun getItemCount(): Int {
        return notificationsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note : Notifications = notificationsList[position]
        holder?.tenantRecyclerAnnouncement?.text = note.message
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val tenantRecyclerAnnouncement = itemView.findViewById(R.id.tenantRecyclerAnnouncement) as TextView
    }
}