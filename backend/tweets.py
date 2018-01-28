import twitter, keys

api = twitter.Api(consumer_key=keys.get_twitter_key(),
        consumer_secret=keys.get_twitter_secret(),
        access_token_key=keys.get_twitter_access(),
        access_token_secret=keys.get_twitter_access_secret())

print(api.VerifyCredentials())

def get_timeline(user):
    list_of_tweets = api.GetUserTimeline(screen_name=user)
    return list_of_tweets

def get_recent_tweet(user):
    return get_timeline(user)[0].AsDict()
