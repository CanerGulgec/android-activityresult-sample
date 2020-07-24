package com.android.activityresultsample

import android.Manifest
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityResultBtn.setOnClickListener {
            openSecondActivity.launch(
                Intent(this, SecondActivity::class.java).apply {
                    putExtra("ID", 1)
                }
            )
        }

        permissionBtn.setOnClickListener {
            requestPermission.launch(Manifest.permission.CALL_PHONE)
        }

        multiplePermissionBtn.setOnClickListener {
            requestMultiplePermissions.launch(
                arrayOf(
                    Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            )
        }
    }

    // Activity result contract
    private val openSecondActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                Log.v("Result OK", "")
            }
        }

    // Request permission contract
    private val requestPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) Log.v("Permission granted", "")
            else Log.e("Permission denied", "")
        }

    // Request multiple permissions contract
    private val requestMultiplePermissions =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            permissions.entries.forEach {
                if (it.value) {
                    Log.v("Permission granted", it.key + " " + it.value)
                } else {
                    Log.e("Permission denied", it.key + " " + it.value)
                }
            }
        }
}