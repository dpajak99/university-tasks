import 'dart:convert';

import 'package:equatable/equatable.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:projekt/infra/dto/chat/chat_message.dart';
import 'package:projekt/infra/sockets/chat_socket.dart';
import 'package:stomp_dart_client/stomp_frame.dart';

part 'websocket_listener_state.dart';

class WebsocketListenerCubit extends Cubit<WebsocketListenerState> {
  final ChatSocket chatSocket;

  WebsocketListenerCubit({required this.chatSocket}) : super(WebsocketListenerWaiting());

  Future<void> init() async {
    chatSocket.initConnection(onMessageReceived: _onMessageReceived);
  }

  Future<void> _onMessageReceived(StompFrame frame) async {
    if (frame.body != null) {
      ChatMessage chatMessage = ChatMessage.fromJson(json.decode(frame.body!) as Map<String, dynamic>);
      emit(ReceivedChatMessage(message: chatMessage));
    }
  }
}
