package com.example.advprog2_4;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


public class MessageService extends FirebaseMessagingService {
    NotificationManager notificationManager;
    int importance = NotificationManager.IMPORTANCE_DEFAULT;
    String id;
    String SenderName;
    public MessageService() {
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        if (message.getNotification() != null) {
            id = message.getNotification().getBody();
            createNotificationChannel();
            SenderName = message.getNotification().getTitle();
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, id)
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle("ChatApp")
                    .setContentText("Message received from" + SenderName)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            notificationManager.notify(Integer.parseInt(id), builder.build());
        }
    }
    public void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                CharSequence name = getString(R.string.channel_name);
                String desc = getString(R.string.channel_name);
                NotificationChannel channel = new NotificationChannel(id, name, importance);
                channel.setDescription(desc);

                notificationManager = getSystemService(NotificationManager.class);
                notificationManager.createNotificationChannel(channel);
            }
        }
}