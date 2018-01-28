from flask import Flask, jsonify, request
import keys, nessie, nlp
import logging
from websocket_server import WebsocketServer

app = Flask(__name__)


def new_client(client, server):
	server.send_message_to_all("Hey all, a new client has joined us")

def message(client, server, message):
    print("Message received: " + message)

server = WebsocketServer(3000)
server.set_fn_new_client(new_client)
server.set_fn_message_received(message)

server.run_forever()

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

@app.route("/get_keywords", methods=["POST"])
def get_keywords():
    body = request.get_json()
    phrase = body["phrase"]
    return jsonify(nlp.get_keywords(phrase).json())
