package com.example.ravikant.gcm

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast

import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (intent.extras != null) {
            for (key in intent.extras.keySet()) {
                val value = intent.extras.get(key)
                Log.d(TAG, "Key: $key Value: $value")
            }
        }
        // [END handle_data_extras]

        val subscribeButton = findViewById(R.id.subscribeButton) as Button
        subscribeButton.setOnClickListener {
            // [START subscribe_topics]
            FirebaseMessaging.getInstance().subscribeToTopic("news")
            // [END subscribe_topics]

            // Log and toast
            val msg = getString(R.string.msg_subscribed)
            Log.d(TAG, msg)
            Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
        }

        val logTokenButton = findViewById(R.id.logTokenButton) as Button
        logTokenButton.setOnClickListener {
            // Get token
            val token = FirebaseInstanceId.getInstance().token

            // Log and toast
            val msg = getString(R.string.msg_token_fmt, token)
            Log.d(TAG, msg)
            Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        private val TAG = "MainActivity"
    }
}
