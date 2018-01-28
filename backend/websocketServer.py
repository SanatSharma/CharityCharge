from websocket_server import WebsocketServer

socket_client = None
server_conn = None
server = None

def new_client(client, server):
    print("Connection")

def messageRec(client, server, message):
    print("Message received: " + message)
    global socket_client
    global server_conn

    if message=="server":
        #server connection
        server_conn = client
    
    elif socket_client is None and client!=server_conn:
        print("Initialized client")
        socket_client=client
    else:
        print("hereeee")
        # data message
        if socket_client is None:
            print("NO CLIENT")
        else:
            print("Trying to send message");
            server.send_message(socket_client, message)
    
    

def websocket():
    server = WebsocketServer(3000)
    server.set_fn_new_client(new_client)
    server.set_fn_message_received(messageRec)
    server.run_forever()

if __name__ == "__main__":
    websocket()
