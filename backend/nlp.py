import requests
import json

accessKey = 'f44b9fd66dab4c9db09bd9cd5c6675ed'

uri = 'https://southcentralus.api.cognitive.microsoft.com/text/analytics/v2.0/keyPhrases'

# input a JSON and this will create a keyword analysis of that JSON
# format:

# documents = {
#   "documents": [
#        {
#        "language": "string",
#        "id": "string",
#        "text": "string"
#        }
#    ]
#}


def get_keywords (string):
    "Detects the languages for a set of documents and returns the information."
    documents = {
        "documents": [
            {
                "language": "en",
                "id": "1",
                "text": string
            }
        ]
    }
    headers = {'Ocp-Apim-Subscription-Key': accessKey}
    body = json.dumps (documents)
    response = requests.post(uri, data=body, headers=headers)
    return response.json()
