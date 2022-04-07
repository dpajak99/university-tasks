part of 'websocket_listener_cubit.dart';

abstract class WebsocketListenerState extends Equatable {
  const WebsocketListenerState();

  @override
  List<Object> get props => <Object>[];
}

class WebsocketListenerWaiting extends WebsocketListenerState {}

class ReceivedChatMessage extends WebsocketListenerState {
  final ChatMessage message;

  const ReceivedChatMessage({required this.message});

  @override
  List<Object> get props => <Object>[message];
}