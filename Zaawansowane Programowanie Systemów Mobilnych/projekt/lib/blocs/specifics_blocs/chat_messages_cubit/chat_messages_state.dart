part of 'chat_messages_cubit.dart';

abstract class ChatMessagesState extends Equatable {
  const ChatMessagesState();

  @override
  List<Object> get props => <Object>[];
}

class MessagesLoading extends ChatMessagesState {}

class MessagesLoaded extends ChatMessagesState {
  final List<ChatMessage> messages;
  final ChatRoom chatRoom;

  const MessagesLoaded({
    required this.messages,
    required this.chatRoom,
  });

  @override
  List<Object> get props => <Object>[messages, chatRoom];
}
