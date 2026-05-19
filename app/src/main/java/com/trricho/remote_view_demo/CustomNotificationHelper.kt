package com.trricho.remote_view_demo

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat

/**
 * Builds a custom notification using [RemoteViews] — a serialized list of UI
 * instructions parceled to System UI, which inflates the layout and replays them.
 */
object CustomNotificationHelper {

    private const val CHANNEL_ID = "remote_views_demo"

    fun createChannel(context: Context) {
        val channel = NotificationChannel(
            CHANNEL_ID,
            context.getString(R.string.notification_channel_name),
            NotificationManager.IMPORTANCE_DEFAULT,
        ).apply {
            description = context.getString(R.string.notification_channel_desc)
        }
        val manager = context.getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(channel)
    }

    fun show(context: Context) {
        val appContext = context.applicationContext
        val collapsed = buildRemoteViews(appContext, R.layout.notification_custom)
        val expanded = buildRemoteViews(appContext, R.layout.notification_custom_big)

        val notification = NotificationCompat.Builder(appContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification_small)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setCustomContentView(collapsed)
            .setCustomBigContentView(expanded)
            .setContentIntent(openAppPendingIntent(appContext))
            .setAutoCancel(false)
            .setOngoing(!NotificationState.isCompleted)
            .build()

        NotificationManagerCompat.from(appContext)
            .notify(NotificationState.NOTIFICATION_ID, notification)
    }

    fun dismiss(context: Context) {
        NotificationState.reset()
        NotificationManagerCompat.from(context.applicationContext)
            .cancel(NotificationState.NOTIFICATION_ID)
    }

    private fun buildRemoteViews(context: Context, layoutId: Int): RemoteViews {
        val rv = RemoteViews(context.packageName, layoutId)

        val title: String
        val subtitle: String
        val badge: String

        if (NotificationState.isCompleted) {
            title = context.getString(R.string.notification_done_title)
            subtitle = context.getString(R.string.notification_done_subtitle)
            badge = context.getString(R.string.notification_done_badge)
            rv.setImageViewResource(R.id.iv_icon, R.drawable.ic_focus_spark)
            rv.setInt(R.id.tv_badge, "setTextColor", color(context, R.color.notification_done))
        } else {
            title = context.getString(R.string.notification_title_default)
            subtitle = context.getString(R.string.notification_subtitle_default)
            badge = context.getString(R.string.notification_badge_default)
            rv.setImageViewResource(R.id.iv_icon, R.drawable.ic_focus_spark)
            rv.setInt(R.id.tv_badge, "setTextColor", color(context, R.color.notification_badge))
        }

        rv.setTextViewText(R.id.tv_title, title)
        rv.setTextViewText(R.id.tv_subtitle, subtitle)
        rv.setTextViewText(R.id.tv_badge, badge)
        rv.setProgressBar(R.id.progress, 100, NotificationState.progress, false)

        if (layoutId == R.layout.notification_custom_big) {
            rv.setTextViewText(R.id.tv_body, context.getString(R.string.notification_body_default))
            rv.setTextViewText(
                R.id.tv_progress_label,
                context.getString(R.string.notification_progress_format, NotificationState.progress),
            )
        }

        rv.setOnClickPendingIntent(R.id.btn_done, actionPendingIntent(context, NotificationActionReceiver.ACTION_MARK_DONE))
        rv.setOnClickPendingIntent(R.id.btn_snooze, actionPendingIntent(context, NotificationActionReceiver.ACTION_SNOOZE))

        return rv
    }

    private fun actionPendingIntent(context: Context, action: String): PendingIntent {
        val intent = Intent(context, NotificationActionReceiver::class.java).apply {
            this.action = action
        }
        return PendingIntent.getBroadcast(
            context,
            action.hashCode(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )
    }

    private fun openAppPendingIntent(context: Context): PendingIntent {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        }
        return PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )
    }

    private fun color(context: Context, resId: Int): Int =
        ContextCompat.getColor(context, resId)
}
