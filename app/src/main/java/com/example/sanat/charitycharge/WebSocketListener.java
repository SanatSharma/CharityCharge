package com.example.sanat.charitycharge;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okio.ByteString;

/**
 * Created by sanat on 1/28/2018.
 */

public class WebSocketListener extends okhttp3.WebSocketListener{
    private static final int NORMAL_CLOSURE_STATUS = 1000;
    private static WebSocketListener instance;
    static OkHttpClient client;
    String message;
    static Context context;
    static WebSocketListener listener_1;


    public WebSocketListener(Context ctx){
        client = new OkHttpClient();
        listener_1 = this;
        context = ctx;
        System.out.println("Reaching constructor");
        System.out.println(context.toString());

    }

    public static void start(){
        Request request = new Request.Builder().url("http://6c734971.ngrok.io").build();
        WebSocketListener listener = listener_1;
        WebSocket ws = client.newWebSocket(request, listener);
    }


    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        webSocket.send("Connected");
        System.out.println("Connection  established!");
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        System.out.println("Receiving : " + text);
        message = text;
        sendNotification(text);
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        webSocket.close(NORMAL_CLOSURE_STATUS, null);
        System.out.println("Closing : " + code + " / " + reason);
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        sendNotification("Hurricane");
        System.out.println("Error : " + t.getMessage());
    }

    public void sendNotification(String text){
        // The id of the channel.
        Integer mNotificationId = 23;
        String CHANNEL_ID = "my_channel_01";
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context, CHANNEL_ID)
                        .setContentTitle("Charity Charge")
                        .setSmallIcon(R.drawable.notification)
                        .setContentText(text + " has occurred! The following charities might be useful to donate to");
        // Creates an explicit intent for an Activity in your app
        System.out.println("CONTEXTT: " + context);

        try {
            Intent resultIntent = new Intent(context, ResultActivity.class);

            // The stack builder object will contain an artificial back stack for the
            // started Activity.
            // This ensures that navigating backward from the Activity leads out of
            // your app to the Home screen.
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
            System.out.println(context);
            // Adds the back stack for the Intent (but not the Intent itself)
            stackBuilder.addParentStack(ResultActivity.class);
            // Adds the Intent that starts the Activity to the top of the stack
            stackBuilder.addNextIntent(resultIntent);
            PendingIntent resultPendingIntent =
                    stackBuilder.getPendingIntent(
                            0,
                            PendingIntent.FLAG_UPDATE_CURRENT
                    );
            mBuilder.setContentIntent(resultPendingIntent);
            NotificationManager mNotificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            // mNotificationId is a unique integer your app uses to identify the
// notification. For example, to cancel the notification, you can pass its ID
// number to NotificationManager.cancel().
            mNotificationManager.notify(mNotificationId, mBuilder.build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}