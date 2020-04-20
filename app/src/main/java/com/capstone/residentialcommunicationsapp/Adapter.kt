package com.capstone.residentialcommunicationsapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.capstone.residentialcommunicationsapp.datamodels.Notifications

class Adapter(val context: Context, val adapterDataList: List<Any>) : RecyclerView.Adapter<Adapter.ViewHolder<*>>() {

    companion object {
        private const val TENANT_HOME = 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<*> {
        return when (viewType) {
            TENANT_HOME -> {
                val view = LayoutInflater.from(context)
                    .inflate(R.layout.tenant_home_recycler_view, parent, false)
                NotificationsViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int {
        return adapterDataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder<*>, position: Int) {
        val element = adapterDataList[position]
        when (holder) {
            is NotificationsViewHolder -> holder.bind(element as Notifications)
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemViewType(position: Int): Int {
        val comparable = adapterDataList[position]
        return when (comparable) {
            is Notifications -> TENANT_HOME
            else -> throw IllegalArgumentException("Invalid type of data " + position)
        }
    }

    abstract class ViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: T)
    }

    //----------------FamilyViewHolder | FamilyDataModel------------
    inner class NotificationsViewHolder(itemView: View) : ViewHolder<Notifications>(itemView) {

        override fun bind(item: Notifications) {
            val tenantRecyclerAnnouncement = itemView.findViewById(R.id.tenantRecyclerAnnouncement) as TextView
            tenantRecyclerAnnouncement?.text = item.message
        }
    }
}