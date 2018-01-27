from flask import Flask, jsonify, request
import keys, nessie, nlp

app = Flask(__name__)

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
