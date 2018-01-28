package com.example.sanat.charitycharge;

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

    private WebSocketListener(){
        client = new OkHttpClient();
        System.out.println("Reaching constructor");
    }

    public static void start(){
        Request request = new Request.Builder().url("ws://552e68b6.ngrok.io").build();
        WebSocketListener listener = WebSocketListener.getInstance();
        WebSocket ws = client.newWebSocket(request, listener);
    }

    public static synchronized WebSocketListener getInstance(){
        if(instance==null){
            instance=new WebSocketListener();
        }
        return instance;
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
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        webSocket.close(NORMAL_CLOSURE_STATUS, null);
        System.out.println("Closing : " + code + " / " + reason);
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        System.out.println("Error : " + t.getMessage());
    }
}