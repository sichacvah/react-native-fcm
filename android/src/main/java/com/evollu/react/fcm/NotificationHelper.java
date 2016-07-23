package com.evollu.react.fcm;


import android.app.*;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;


public class NotificationHelper {
  private Context mContext;

  public NotificationHelper(Application context) {
    mContext = context;
  }

  public Class getMainActivityClass() {
    String packageName = mContext.getPackageName();
    Intent launchIntent = mContext.getPackageManager().getLaunchIntentForPackage(packageName);
    String className = launchIntent.getComponent().getClassName();
    try {
        return Class.forName(className);
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
        return null;
    }
  }
}
