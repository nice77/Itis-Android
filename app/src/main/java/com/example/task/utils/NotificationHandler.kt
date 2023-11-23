package com.example.task.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.task.MainActivity
import com.example.task.R
import com.example.task.fragments.CoroutinesFragment
import com.example.task.fragments.MainFragment

object NotificationHandler {

    private const val NOTIFICATION_CHANNEL_ID = "1"
    const val ACTION_KEY = "action"
    const val ACTION_VALUE_ONE = "toast"
    const val ACTION_VALUE_TWO = "settings"
    const val NOTIFICATION_ID = 42
    lateinit var notificationManager : NotificationManager

    fun initNotificationManager(ctx: Context) {
        notificationManager = ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannel()
    }

    fun createNotification(ctx : Context, title : String, text : String) {
        val builder = NotificationCompat.Builder(
            ctx,
            NOTIFICATION_CHANNEL_ID
        )
            .setPriority(NotificationSettings.importantLevel)
            .setVisibility(NotificationSettings.privacyLevel)
            .setContentTitle(title)
            .setSmallIcon(R.mipmap.ic_launcher)

        val intent = Intent(ctx, MainActivity::class.java)
        val open = PendingIntent.getActivity(
            ctx,
            101,
            intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )
        builder.setContentIntent(open)

        if (NotificationSettings.expanding) {
            builder
                .setContentText(ctx.getString(R.string.more_text_below))
                .setStyle(NotificationCompat.BigTextStyle().bigText(text))
        }
        else {
            builder.setContentText(text)
        }
        if (NotificationSettings.buttonsShowUp) {
            val intentActionOne = Intent(ctx, MainActivity::class.java)
                .putExtra(ACTION_KEY, ACTION_VALUE_ONE)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            val actionOne = PendingIntent.getActivity(
                ctx,
                101,
                intentActionOne,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
            )
            builder
                .addAction(0, "Toast", actionOne)

            val intentActionTwo = Intent(ctx, MainActivity::class.java)
                .putExtra(ACTION_KEY, ACTION_VALUE_TWO)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            val actionTwo = PendingIntent.getActivity(
                ctx,
                102,
                intentActionTwo,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
            )
            builder
                .addAction(0, "Settings", actionTwo)
        }
        builder.setAutoCancel(true).setOngoing(true)
        notificationManager.notify(NOTIFICATION_ID, builder.build())
    }

    fun closeNotification() {
        notificationManager.cancel(NOTIFICATION_ID)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                "Notifications channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
    }
}