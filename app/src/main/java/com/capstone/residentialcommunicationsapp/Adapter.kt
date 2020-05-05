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
import com.capstone.residentialcommunicationsapp.datamodels.TenantIssue

class Adapter(val context: Context, val adapterDataList: List<Any>) : RecyclerView.Adapter<Adapter.ViewHolder<*>>() {

    companion object {
        private const val TENANT_HOME = 0
        private const val TENANT_ISSUES = 1
        private const val PROPERTY_MAINTENANCE = 2
        private const val PROPERTY_ISSUES = 4
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<*> {
        return when (viewType) {
            TENANT_HOME -> {
                val view = LayoutInflater.from(context)
                    .inflate(R.layout.tenant_home_recycler_view, parent, false)
                NotificationsViewHolder(view)
            }
            TENANT_ISSUES -> {
                val view = LayoutInflater.from(context)
                    .inflate(R.layout.tenant_view_issues_recycler_view, parent, false)
                IssuesViewHolder(view)
            }
            PROPERTY_MAINTENANCE -> {
                val view = LayoutInflater.from(context)
                    .inflate(R.layout.property_maintenance_recycler_view, parent, false)
                MaintenanceViewHolder(view)
            }
            PROPERTY_ISSUES -> {
                val view = LayoutInflater.from(context)
                    .inflate(R.layout.property_home_recycler_view, parent, false)
                TenantIssueViewHolder(view)
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
            is TenantIssueViewHolder -> holder.bind(element as TenantIssue)
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemViewType(position: Int): Int {
        val comparable = adapterDataList[position]
        return when (comparable) {
            is Notifications -> TENANT_HOME
            is Issue -> TENANT_ISSUES
            is Maintenance -> PROPERTY_MAINTENANCE
            is TenantIssue -> PROPERTY_ISSUES
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

    inner class TenantIssueViewHolder(itemView: View) : ViewHolder<TenantIssue>(itemView) {

        override fun bind(item: TenantIssue) {
            val propertyRecyclerIssuesPropertyId = itemView.findViewById(R.id.propertyRecyclerIssuesPropertyId) as TextView
            propertyRecyclerIssuesPropertyId?.text = "Property ID: " + item.tenant?.propId

            val propertyRecyclerIssuesBuildingNumber = itemView.findViewById(R.id.propertyRecyclerIssuesBuildingNumber) as TextView
            propertyRecyclerIssuesBuildingNumber?.text = "Building Number: " + item.tenant?.buildNum

            val propertyRecyclerIssuesUnitNumber = itemView.findViewById(R.id.propertyRecyclerIssuesUnitNumber) as TextView
            propertyRecyclerIssuesUnitNumber?.text = "Unit Number: " + item.tenant?.unitNum

            val propertyRecyclerIssuesUrgency = itemView.findViewById(R.id.propertyRecyclerIssuesUrgency) as TextView
            propertyRecyclerIssuesUrgency?.text = "Urgency Level: " + item.issue.urgencyLevel.toString()

            val propertyRecyclerIssuesType = itemView.findViewById(R.id.propertyRecyclerIssuesType) as TextView
            propertyRecyclerIssuesType?.text = "Type: " + item.issue.type

            val propertyRecyclerIssuesDescription = itemView.findViewById(R.id.propertyRecyclerIssuesDescription) as TextView
            propertyRecyclerIssuesDescription?.text = "Description: " + item.issue.description
        }
    }

    inner class IssuesViewHolder(itemView: View) : ViewHolder<Issue>(itemView) {

        override fun bind(item: Issue) {

            val tenantRecyclerIssuesUrgency = itemView.findViewById(R.id.tenantRecyclerIssuesUrgency) as TextView
            tenantRecyclerIssuesUrgency?.text = "Urgency Level: " + item.urgencyLevel.toString()

            val tenantRecyclerIssuesType = itemView.findViewById(R.id.tenantRecyclerIssuesType) as TextView
            tenantRecyclerIssuesType?.text = "Type: " + item.type

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