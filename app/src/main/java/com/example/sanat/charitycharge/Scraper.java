package sxsw2017;

import java.util.*;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
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
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class Scraper {
	/*	final static AccessToken accessToken = new AccessToken("789951371428495360-7aljagVXHXXVySZ94BQNHzG0ZVr2JDI"
			, "dxq40x9zV4gqUHxZflfqT975pEFpUxardfyRinuxi96S7");*/
	//final static Twitter twitter = new TwitterFactory().getInstance(accessToken);
	//final static Twitter twitter = new TwitterFactory().getInstance(accessToken);
	private static String city = "";
	private static String state = "";
	private static String hashtag = "";
	private static String artist = "";
	private static double latitude = 0;
	private static double longitude = 0;
	static ConfigurationBuilder builder = new ConfigurationBuilder();
	static ArrayList<String> setList = new ArrayList<String>();
	static Twitter twitter;
	static boolean addedToList = false;
	Scraper(){
		builder =  new ConfigurationBuilder();
		//city = null;
		//state = null;
		latitude = 0;
		longitude = 0;
		//hashtag = null;
		setList = new ArrayList<String>();
	}
	public static void main(String[] args)throws IOException{

		builder.setOAuthConsumerKey("BNvErshlqPIYQUDOPFmvQofPH");
		builder.setOAuthConsumerSecret("3oBEm1MerGR0kJPDOYqhLnoaQlpYFX4pTbjybRo4uIJEjQ0ahJ");
		builder.setOAuthAccessToken("789951371428495360-7aljagVXHXXVySZ94BQNHzG0ZVr2JDI");
		builder.setOAuthAccessTokenSecret("dxq40x9zV4gqUHxZflfqT975pEFpUxardfyRinuxi96S7");
		Configuration configuration = builder.build();
		TwitterFactory factory = new TwitterFactory(configuration);
		twitter = factory.getInstance();


		//	URL url = new URL("http://www.oracle.com/");

		//createJsonString();

		System.out.println(setList.toString());

		//		System.out.println("HEREEEE");

		//		System.out.println(hashtag);



		/*		
        }*/
	}

	static void fetchTwitter(String hashtag, String city, String state) throws JSONException{

		if(hashtag == null)
			throw new IllegalArgumentException("0");

		try {
			//create a query to search twitter with the specific hashtag
			Query query = new Query(hashtag);
			QueryResult result;
			boolean check = false;

			//get results from twitter
			result = twitter.search(query);

			//store the returned tweets in a list
			List<Status> tweets = result.getTweets();

			//TODO send in the state and state to mapquest api
			//TODO set latitude and longitude to the return from the getLat and getLong methods
			addedToList = false;
			for(Status tweet: tweets){
				
				check = false;

				String[] location = tweet.getUser().getLocation().split(", ");
				for(String s : location)
					if(s.equalsIgnoreCase(city) || s.equalsIgnoreCase(state)){
						check = true;
						break;
					}
				String[] text = tweet.getText().split(" ");
				if(!(text[0].equals(hashtag)))
					check = false;

				if(check){
					//System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
					//splitting the text into tokens based on spaces

					MapQuestApi map = new MapQuestApi(city, state);

					latitude = map.getLat();
					longitude = map.getLon();
					artist = text[0].substring(1, text[0].length());

					System.out.println(tweet.getUser().getScreenName());
					System.out.println(tweet.getText());
					System.out.println(tweet.getUser().getLocation());

					System.out.println(latitude);
					System.out.println(longitude + "\n");

					String[] tokens = tweet.getText().split(" ");
					String song = "";
					for(String s: tokens){
						if(s.length() > 0) {
							if(s.charAt(0)!='#')
								song += s + " ";
						}
					}
					song = song.substring(0, song.length()-1);
					if(!setList.contains(song))
					{
						addedToList = true;
						setList.add(song);
					}
					
					if(!setList.isEmpty()){
						if(addedToList){
							sendMessageToServer();
						}
					}
				}
			}
		}
		catch (TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to search tweets: " + te.getMessage());
			System.exit(-1);
		}

		System.out.println(setList.toString());
	}
}
