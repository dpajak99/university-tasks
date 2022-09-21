import 'dart:convert';

import 'package:projekt/config/locator.dart';
import 'package:projekt/providers/account_provider.dart';
import 'package:projekt/shared/stomp_manager.dart';
import 'package:stomp_dart_client/stomp_frame.dart';

class ChatSocket {
  StompManager websocket = StompManager();

  void initConnection({required MessageReceivedCallback onMessageReceived}) {
    try {
      websocket.deactivate();
    } catch(_) {
      print('Error while disconnecting from websocket');
    }
    websocket.startConnection((StompFrame frame) => _onConnect(frame, onMessageReceived));
  }

  Future<void> _onConnect(StompFrame frame, MessageReceivedCallback onMessageReceived) async {
    print('Connected to websocket');
    if( globalLocator<AccountProvider>().isLoggedIn ) {
      String userId = globalLocator<AccountProvider>().account!.id;
      websocket.subscribe(
        destination: '/user/$userId/queue/messages',
        callback: (StompFrame frame) {
          onMessageReceived(frame);
        },
      );
    }
  }

  Future<void> sendMessage(Map<String, dynamic> chatMessage) async {
    print('Sending message: $chatMessage');
    websocket.send(
      destination: '/app/chat',
      body: json.encode(chatMessage),
    );
  }

  Future<void> stopConnection() async {
    websocket.deactivate();
  }
}
