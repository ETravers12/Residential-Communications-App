package com.capstone.residentialcommunicationsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.capstone.residentialcommunicationsapp.datamodels.Tenant
import com.capstone.residentialcommunicationsapp.datamodels.TenantViewModel

class CreateTenant : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_tenant)

        var name = findViewById<EditText>(R.id.tenantName)
        var buildNum = findViewById<EditText>(R.id.tenantBuildingNumber)
        var unitNum = findViewById<EditText>(R.id.tenantUnitNumber)
        var propId = findViewById<EditText>(R.id.tenantPropertyId)
        var username = findViewById<EditText>(R.id.tenantUsername)
        var password = findViewById<EditText>(R.id.tenantPassword)

        val createTenantError = findViewById<TextView>(R.id.createTenantError)

        val TenantModel: TenantViewModel by viewModels()

        fun isValid(name: String, buildNum: Int, unitNum: Int, propId: Int, username: String, password: String) : Boolean {
            var validity = true

            if (name.isNullOrEmpty() || buildNum <= 0 || unitNum <= 0 || propId <= 0 || username.isNullOrEmpty()
                || password.isNullOrEmpty()) {
                validity = false
            }

            return validity
        }

        val createTenantSubmitBtn = findViewById<Button>(R.id.createTenantSubmitBtn)
        createTenantSubmitBtn.setOnClickListener {
            if (isValid(name.text.toString(), buildNum.text.toString().toIntOrNull() ?: -1,
                    unitNum.text.toString().toIntOrNull() ?: -1, propId.text.toString().toIntOrNull() ?: -1,
                    username.text.toString(), password.text.toString())) {
                TenantModel.createTenant(name.text.toString(), buildNum.text.toString().toIntOrNull() ?: -1,
                    unitNum.text.toString().toIntOrNull() ?: -1, propId.text.toString().toIntOrNull() ?: -1,
                    username.text.toString(), password.text.toString())
            }
            else {
                createTenantError.setText("Invalid information or an empty field was entered. Please try again.")
            }
        }

        TenantModel.tenantCreationLiveData.observe(this, Observer<Tenant> { tenant ->
            if (tenant != null) {
                val intent = Intent(this, TenantPortalActivity::class.java)
                startActivity(intent)
            }
            else {
                createTenantError.setText("An error has occurred! Please try again.")
            }
        })
    }
}
