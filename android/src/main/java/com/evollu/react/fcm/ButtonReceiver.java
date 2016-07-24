package com.evollu.react.fcm;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.Context;
import android.app.NotificationManager;

public class ButtonReceiver extends BroadcastReceiver {
  @Override
  public void onReceive(Context context, Intent intent) {
    int notificationId = intent.getIntExtra("notificationId", 0);

    NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    manager.cancel(notificationId);
    
  }
}


