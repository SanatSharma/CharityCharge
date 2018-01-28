from websocket_server import WebsocketServer

socket_client = None
server_conn = None
server = None

def new_client(client, server):
    print("New client connected and was given id %d" % client['id'])

def messageRec(client, server, message):
    print("Message received: " + message)
    server.send_message_to_all(message)

def websocket():
    server = WebsocketServer(3000)
    server.set_fn_new_client(new_client)
    server.set_fn_message_received(messageRec)
    server.run_forever()

if __name__ == "__main__":
    websocket()
