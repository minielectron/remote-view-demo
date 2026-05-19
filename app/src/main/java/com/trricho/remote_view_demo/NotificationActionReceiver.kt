package com.trricho.remote_view_demo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.app.NotificationManagerCompat

class NotificationActionReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            ACTION_MARK_DONE -> {
                NotificationState.markDone()
                CustomNotificationHelper.show(context)
                Toast.makeText(
                    context,
                    R.string.notification_done_toast,
                    Toast.LENGTH_SHORT,
                ).show()
            }

            ACTION_SNOOZE -> {
                NotificationState.snooze()
                CustomNotificationHelper.show(context)
                Toast.makeText(
                    context,
                    R.string.notification_snooze_toast,
                    Toast.LENGTH_SHORT,
                ).show()
            }

            ACTION_DISMISS -> {
                NotificationManagerCompat.from(context)
                    .cancel(NotificationState.NOTIFICATION_ID)
            }
        }
    }

    companion object {
        const val ACTION_MARK_DONE = "com.trricho.remote_view_demo.ACTION_MARK_DONE"
        const val ACTION_SNOOZE = "com.trricho.remote_view_demo.ACTION_SNOOZE"
        const val ACTION_DISMISS = "com.trricho.remote_view_demo.ACTION_DISMISS"
    }
}
