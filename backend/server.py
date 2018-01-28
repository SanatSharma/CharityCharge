from flask import Flask, jsonify, request
from ctypes import c_char_p
import requests, json, time
import keys, nessie, nlp, tweets
import time, threading
from websocket import create_connection

app = Flask(__name__)

display_name = "Shin_Cow"

def new_client(client, server):
    socket_client = client
    server.send_message_to_all("Hey all, a new client has joined us")

def message(client, server, message):
    print("Message received: " + message)
    
ws = None
previous_string = ""

def hello():
    return keys.get_nessie_key()

def transfer():
    body = request.get_json()
    payee = body["payee"]
    payer = body["payer"]
    payee_name = body["payee_name"]
    amount = body["amount"]
    return jsonify(nessie.transfer_funds(payer, payee, payee_name, amount).json())

def get_keywords(phrase):
    return nlp.get_keywords(phrase)

def get_tweets(display_name):
    return json.dumps(tweets.get_recent_tweet(display_name)["text"])

def update():
    response_json = json.dumps(tweets.get_recent_tweet("Shin_Cow")["text"])
    print(response_json)
    global previous_string
    if (previous_string == ""):
        print("Going here")
        previous_string = response_json
    elif (previous_string != response_json):
        print("OR here")
        #send request to sanat
        previous_string = response_json
        print("$$$$$$$$$$$$$$$$$")
        keywords = get_keywords(response_json)
        print(type(keywords))
        print(keywords)
        try:
            word = keywords["documents"][0]["keyPhrases"][0]
            print(str(word))
            if (ws != None):
                print("Sending Data")
                ws.send(word)
            else:
                print("ws is none")
        except:
            print("Error happened")

    
    threading.Timer(5, update).start()

if __name__ == "__main__":
    print("here")
    print("!!!!!!!!!!!!!!!")
    ws = create_connection("ws://localhost:3000")
    ws.send("server")
    threading.Timer(5, update).start()
    #app.run(debug=True, use_reloader=False)
   
