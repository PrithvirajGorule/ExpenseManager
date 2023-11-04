package com.example.expensemanager

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class Alarm :BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        val i=Intent(context,LimitExceed::class.java)
        intent!!.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent=PendingIntent.getActivity(context,0,i,0)

        val builder=NotificationCompat.Builder(context!!,"Expenses Manager")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("Limit Exceeded!!!!")
            .setContentText("Expense exceeded set limit")
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)

        /*val notificationManager=NotificationManagerCompat.from(context)
        notificationManager.notify(123,builder.build())*/
    }


}