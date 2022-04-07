import 'dart:typed_data';

import 'package:projekt/config/locator.dart';
import 'package:projekt/providers/account_provider.dart';
import 'package:projekt/shared/api_manager.dart';
import 'package:projekt/shared/models/account.dart';
import 'package:stomp_dart_client/stomp.dart';
import 'package:stomp_dart_client/stomp_config.dart';
import 'package:stomp_dart_client/stomp_frame.dart';

typedef MessageReceivedCallback = Function(StompFrame frame);
typedef WebsocketConnectCallback = void Function(StompFrame frame);

class StompManager {
  late StompClient stompClient;

  void startConnection(WebsocketConnectCallback onConnect) {
    stompClient = StompClient(
      config: StompConfig.SockJS(
        connectionTimeout: const Duration(minutes: 1),
        url: '${ApiManager.networkUrl}/ws',
        // onStompError: print,
        // onDebugMessage: print,
        // onDisconnect: print,
        // onUnhandledFrame: print,
        // onUnhandledMessage: print,
        // onUnhandledReceipt: print,
        // onWebSocketError: print,
        stompConnectHeaders: _getDefaultHeaders(),
        webSocketConnectHeaders: _getDefaultHeaders(),
        onConnect: (StompFrame frame) => onConnect(frame),
      ),
    )..activate();
  }

  void subscribe({
    required String destination,
    required MessageReceivedCallback callback,
  }) {
    stompClient.subscribe(
      destination: destination,
      callback: callback,
      headers: _getDefaultHeaders(),
    );
  }

  void send({
    required String destination,
    Map<String, String>? headers,
    String? body,
    Uint8List? binaryBody,
  }) {
    stompClient.send(
      destination: destination,
      body: body,
      binaryBody: binaryBody,
      headers: _getDefaultHeaders(),
    );
  }

  void deactivate() {
    stompClient.deactivate();
  }

  Map<String, String> _getDefaultHeaders() {
    Map<String, String> headers = <String, String>{};
    if (globalLocator<AccountProvider>().isLoggedIn) {
      Account account = globalLocator<AccountProvider>().account!;
      headers.putIfAbsent('Authorization', () => '${account.token.token} ${account.token.type}');
    }
    return headers;
  }
}
