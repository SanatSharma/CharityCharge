from flask import Flask, jsonify, request
from multiprocessing import Process, Value, Array, Manager
from ctypes import c_char_p
import requests, json, time

import keys, nessie, nlp, tweets

app = Flask(__name__)

manager = Manager()

display_name = "Shin_Cow"

@app.route("/")
def hello():
    return keys.get_nessie_key()

@app.route("/transfer", methods=["POST"])
def transfer():
    body = request.get_json()
    payee = body["payee"]
    payer = body["payer"]
    payee_name = body["payee_name"]
    amount = body["amount"]
    return jsonify(nessie.transfer_funds(payer, payee, payee_name, amount).json())

def get_keywords(phrase):
    return json.dumps(nlp.get_keywords(phrase).json())

def get_tweets(display_name):
    return json.dumps(tweets.get_recent_tweet(display_name)["text"])

def update():
    previous_string = ""
    while True:
        response_json = json.dumps(tweets.get_recent_tweet("Shin_Cow")["text"])
        print(response_json)
        if (previous_string == ""):
            previous_string = response_json
        elif (previous_string != response_json):
            #send request to sanat
            previous_string = response_json
            keywords = get_keywords(response_json)
            print(keywords)
        else:
            time.sleep(5)

if __name__ == "__main__":
    p = Process(target=update)
    p.start()
    app.run(debug=True, use_reloader=False)
    p.join()
