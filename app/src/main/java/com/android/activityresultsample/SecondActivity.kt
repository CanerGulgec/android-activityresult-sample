package com.android.activityresultsample

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity(R.layout.activity_second) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val id = intent.getIntExtra("ID", -1)
        Log.v("intent extra", "id: $id")

        backBtn.setOnClickListener {
            setResult(Activity.RESULT_OK)
            finish()
        }
    }
}