# CharityCharge


# Inspiration 

We believe that giving back to society is vital. After seeing a lot of disasters occur this past year, we believe it is crucial to inform people about various charities doing relief work as well encourage people to donate to social good.

# What it does

CharityCharge is an Android app that informs people about various charities, their vision as well as allows them to donate to the charity of their choice with the click of a few buttons. We also constantly mine tweets to find about any disasters happening and immediately inform users about them, as well as list ways to aid in recovery.

# How we built it

We built the app on the Android platform. Our servers, for the app, the twitter scraping and natural language processing are built in python using the Python Flask framework. In order to convey real time data, we use WebSockets, in both Python and Java. We utilized the Microsoft Cognitive Services api for getting intents from user tweets.

# Challenges we ran into

We ran into a lot of challenges with multithreading, since we were running multiple asynchronous tasks on our server. We also strove for speed of data transfer and hence writing optimized code with Websockets was a challenge.

# Accomplishments that we're proud of

We are proud of creating a platform that we believe will not only make users more aware of various charities and the work they do, but also encourage them to take part in social good, donate to charity and help make better the lives of people.

# What we learned

We learned a lot about Android Development, concurrency, web sockets and honestly, a lot more about charities than we used to.

# What's next for CharityCharge

We plan to continue on our journey of creating an app that touches and impacts lives and plan to launch the app on the Android Play Store once it reaches a suitable level.
