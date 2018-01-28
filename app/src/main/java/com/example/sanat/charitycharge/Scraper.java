package com.mycompany.app;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.net.*;
import java.io.*;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

import java.net.HttpURLConnection;

import org.apache.http.client.fluent.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.entity.ContentType;

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
			String response = Request.Post("http://127.0.0.1:5000/get_keywords")
			.bodyString("{\"phrase\":\""+message+"\"}", ContentType.APPLICATION_JSON)
			.execute().returnContent().asString();
			System.out.println(response);
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
