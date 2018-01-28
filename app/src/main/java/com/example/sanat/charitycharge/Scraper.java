package com.example.sanat.charitycharge;

import android.content.Context;
import android.util.Log;

import java.util.*;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.Paging;
import twitter4j.auth.AccessToken;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;


import java.util.logging.*;
import java.io.FileReader;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject   ;

import java.io.*;


public class Scraper {
	// final static AccessToken accessToken = new AccessToken("3151868940-VNKeYFFoLoOgq9TtHgomR2jDMiRAjze1DTOgMgJ"
	// 	, "WvLJfsd124aJVW3PQGPO4G3siEyRwyV8sKKNyRaeGKsa2");
	// final static Twitter twitter = new TwitterFactory().getInstance(accessToken);
	static ConfigurationBuilder builder = new ConfigurationBuilder();
	static ArrayList<String> setList = new ArrayList<String>();
	static Twitter twitter;
	static boolean addedToList = false;
	Scraper(){
		builder =  new ConfigurationBuilder();
	}
	public static void main(String[] args)throws IOException{

		builder.setOAuthConsumerKey("HaBjJXYJ9pL2dUKB7Ep3pHlya");
		builder.setOAuthConsumerSecret("HVP9kDG3LjMLs4nqp1tE7BL1RUJk8n2Tf1NeXYu4rQnmUqPH5k");
		builder.setOAuthAccessToken("3151868940-VNKeYFFoLoOgq9TtHgomR2jDMiRAjze1DTOgMgJ");
		builder.setOAuthAccessTokenSecret("WvLJfsd124aJVW3PQGPO4G3siEyRwyV8sKKNyRaeGKsa2");
		Configuration configuration = builder.build();
		TwitterFactory factory = new TwitterFactory(configuration);
		twitter = factory.getInstance();

		try {
			//create a query to search twitter with the specific hashtag
			Paging paging = new Paging(1, 100);
			List<Status> tweets = twitter.getUserTimeline("yonajune",paging);
			
			String tweetText = tweets.get(0).getText();
			System.out.println(tweetText);
			sendMessageToServer(tweetText);
		}
		catch (TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to search tweets: " + te.getMessage());
			System.exit(-1);
		}
	}

	private static void sendMessageToServer(String message) {
        MediaType binary = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient httpclient = new OkHttpClient();
        String json = "{\"phrase\":\""+message+"\"}";
        RequestBody body = RequestBody.create(binary, json);

        Request request = new Request.Builder()
                .url("http://127.0.0.1:5000/get_keywords")
                .post(body)
                .build();
        Response response = null;
        httpclient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.v("FAIL", e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonStr = response.body().string();
                System.out.println("API resp" +  jsonStr);

                //result = parseJson(jsonStr);

                //if(result.size()!= 0) {
                //    Log.v("JSON STR", result.toString());
                //}
                //else
                 //   Log.v("JSON STR", "Empty result");
            }
        });
	}
}
