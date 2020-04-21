package com.capstone.residentialcommunicationsapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.capstone.residentialcommunicationsapp.datamodels.Issue
import com.capstone.residentialcommunicationsapp.datamodels.Maintenance
import com.capstone.residentialcommunicationsapp.datamodels.Notifications

class Adapter(val context: Context, val adapterDataList: List<Any>) : RecyclerView.Adapter<Adapter.ViewHolder<*>>() {

    companion object {
        private const val TENANT_HOME = 0
        private const val PROPERTY_HOME = 1
        private const val PROPERTY_MAINTENANCE = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<*> {
        return when (viewType) {
            TENANT_HOME -> {
                val view = LayoutInflater.from(context)
                    .inflate(R.layout.tenant_home_recycler_view, parent, false)
                NotificationsViewHolder(view)
            }
            PROPERTY_HOME -> {
                val view = LayoutInflater.from(context)
                    .inflate(R.layout.property_home_recycler_view, parent, false)
                IssuesViewHolder(view)
            }
            PROPERTY_MAINTENANCE -> {
                val view = LayoutInflater.from(context)
                    .inflate(R.layout.property_maintenance_recycler_view, parent, false)
                MaintenanceViewHolder(view)
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
            is IssuesViewHolder -> holder.bind(element as Issue)
            is MaintenanceViewHolder -> holder.bind(element as Maintenance)
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemViewType(position: Int): Int {
        val comparable = adapterDataList[position]
        return when (comparable) {
            is Notifications -> TENANT_HOME
            is Issue -> PROPERTY_HOME
            is Maintenance -> PROPERTY_MAINTENANCE
            else -> throw IllegalArgumentException("Invalid type of data " + position)
        }
    }

    abstract class ViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: T)
    }

    inner class NotificationsViewHolder(itemView: View) : ViewHolder<Notifications>(itemView) {

        override fun bind(item: Notifications) {
            val tenantRecyclerAnnouncement = itemView.findViewById(R.id.tenantRecyclerAnnouncement) as TextView
            tenantRecyclerAnnouncement?.text = item.message
        }
    }

    inner class IssuesViewHolder(itemView: View) : ViewHolder<Issue>(itemView) {

        override fun bind(item: Issue) {
            val propertyRecyclerIssuesUrgency = itemView.findViewById(R.id.propertyRecyclerIssuesUrgency) as TextView
            propertyRecyclerIssuesUrgency?.text = "Urgency Level: " + item.urgencyLevel.toString()

            val tenantRecyclerIssuesUrgency = itemView.findViewById(R.id.tenantRecyclerIssuesUrgency) as TextView
            tenantRecyclerIssuesUrgency?.text = "Urgency Level: " + item.urgencyLevel.toString()

            val propertyRecyclerIssuesType = itemView.findViewById(R.id.propertyRecyclerIssuesType) as TextView
            propertyRecyclerIssuesType?.text = "Type: " + item.type

            val tenantRecyclerIssuesType = itemView.findViewById(R.id.tenantRecyclerIssuesType) as TextView
            tenantRecyclerIssuesType?.text = "Type: " + item.type

            val propertyRecyclerIssuesDescription = itemView.findViewById(R.id.propertyRecyclerIssuesDescription) as TextView
            propertyRecyclerIssuesDescription?.text = "Description: " + item.description

            val tenantRecyclerIssuesDescription = itemView.findViewById(R.id.tenantRecyclerIssuesDescription) as TextView
            tenantRecyclerIssuesDescription?.text = "Description: " + item.description
        }
    }

    inner class MaintenanceViewHolder(itemView: View) : ViewHolder<Maintenance>(itemView) {

        override fun bind(item: Maintenance) {
            val propertyMaintenanceName = itemView.findViewById(R.id.propertyMaintenanceName) as TextView
            propertyMaintenanceName?.text = "Name: " + item.name

            val propertyMaintenancePhoneNumber = itemView.findViewById(R.id.propertyMaintenancePhoneNumber) as TextView
            propertyMaintenancePhoneNumber?.text = "Phone Number: " + item.phoneNumber
        }
    }
}