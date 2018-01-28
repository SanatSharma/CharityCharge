import requests
import keys, json

def transfer_funds(payer, payee, payee_name, amount):
    url = "http://api.reimaginebanking.com/accounts/5a6cd1866514d52c7774a891/transfers"

    querystring = {"key": keys.get_nessie_key()}

    headers = {
            "Content-Type": "application/json"
            }

    payload = {
            "medium": "balance",
            "payee_id": payee,
            "transaction_date": "2018-01-27",
            "amount": float(amount),
            "status": "pending",
            "description": "My contribution to {0}".format(payee_name)
    }
    
    response = requests.post(url, data=json.dumps(payload), headers=headers, params=querystring)
    return response
