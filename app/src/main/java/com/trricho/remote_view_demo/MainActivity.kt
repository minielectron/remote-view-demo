package com.trricho.remote_view_demo

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private val requestNotificationPermission = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { granted ->
        if (granted) {
            showDemoNotification()
        } else {
            Toast.makeText(this, R.string.permission_required, Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        CustomNotificationHelper.createChannel(this)

        findViewById<MaterialButton>(R.id.btn_show).setOnClickListener {
            runWithNotificationPermission { showDemoNotification() }
        }

        findViewById<MaterialButton>(R.id.btn_update).setOnClickListener {
            runWithNotificationPermission {
                NotificationState.bumpProgress()
                CustomNotificationHelper.show(this)
            }
        }

        findViewById<MaterialButton>(R.id.btn_dismiss).setOnClickListener {
            CustomNotificationHelper.dismiss(this)
        }
    }

    private fun showDemoNotification() {
        NotificationState.reset()
        CustomNotificationHelper.show(this)
    }

    private fun runWithNotificationPermission(block: () -> Unit) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            block()
            return
        }
        when {
            ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_GRANTED -> block()

            else -> requestNotificationPermission.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }
}
