package com.capstone.residentialcommunicationsapp.datamodels

data class TenantIssue (
    val issue: Issue,
    val tenant: Tenant?
)