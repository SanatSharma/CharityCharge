package com.example.sanat.charitycharge;

import android.content.Context;
import android.util.Log;

import java.util.*;

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

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.*;


import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;

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
		try {
			System.out.println(message);

            CloseableHttpClient httpClient = HttpClients.createDefault();

            HttpPost httppost = new HttpPost("http://127.0.0.1:5000/get_keywords");

            // Request parameters and other properties.
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("phrase", message));
            httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

            //Execute and get the response.
            CloseableHttpResponse response = httpClient.execute(httppost);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                InputStream instream = entity.getContent();
                try {
                    // do something useful
                    System.out.println(instream);
                } finally {
                    instream.close();
                }
            }
		}
		catch(HttpResponseException hre) {
			hre.printStackTrace();
			System.out.println("Failed to communicate with server: " + hre.getStatusCode());
		}
		catch(ClientProtocolException cpe) {
			cpe.printStackTrace();
			System.out.println("CPE: Failed to communicate with server");
		}
		catch(IOException io) {
			io.printStackTrace();
			System.out.println("IO: Failed to communicate with server");
		}
	}
}
