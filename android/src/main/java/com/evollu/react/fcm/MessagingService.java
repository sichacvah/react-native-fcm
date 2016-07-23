package com.evollu.react.fcm;



import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import org.json.JSONObject;
import com.facebook.react.ReactActivity;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.app.*;
import java.lang.ClassNotFoundException;



public class MessagingService extends FirebaseMessagingService {
    private Context mContext;

    public MessagingService(Application context) {
      mContext = context;
    }

    private static final String TAG = "MessagingService";

    final static String GROUP_FCM_NOTIFICATIONS = "GROUP_FCM_NOTIFICATIONS";

    public int mNotificationId = 1;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "Remote message received");
        Intent i = new Intent("com.evollu.react.fcm.ReceiveNotification");
        i.putExtra("data", remoteMessage);
        sendOrderedBroadcast(i, null);
        sendNotification(remoteMessage);
    }


    private void sendNotification(RemoteMessage remoteMessage) {
        Class intentClass = new NotificationHelper(getApplication()).getMainActivityClass();
        Intent intent = new Intent(mContext, intentClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, mNotificationId, intent, PendingIntent.FLAG_ONE_SHOT);

        Intent cancelIntent = new Intent("com.evollu.react.fcm.CancelMessage");
        cancelIntent.putExtra("notificationId", mNotificationId);
        PendingIntent btPendingIntent = PendingIntent.getBroadcast(this, 0, cancelIntent,0);


        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
          .setSmallIcon(R.drawable.ic_launcher)
          .setLargeIcon(largeIcon)
          .setPriority(NotificationCompat.PRIORITY_MAX)
          .setContentTitle("FCM Message")
          .setContentText("SOME TEXT")
          .setAutoCancel(true)
          .setSound(defaultSoundUri)
          .addAction(R.drawable.ic_done_black_48dp, "Принять", btPendingIntent)
          .setContentIntent(pendingIntent);

          
          NotificationManager notificationManager =
                  (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

          notificationManager.notify(mNotificationId, notificationBuilder.build());
    }
}
