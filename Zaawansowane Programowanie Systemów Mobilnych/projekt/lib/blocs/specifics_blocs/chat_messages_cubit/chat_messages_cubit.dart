import 'package:equatable/equatable.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:projekt/infra/dto/chat/chat_message.dart';
import 'package:projekt/infra/dto/chat/chat_room.dart';
import 'package:projekt/infra/services/chat/chat_service.dart';

part 'chat_messages_state.dart';

class ChatMessagesCubit extends Cubit<ChatMessagesState> {
  final ChatService chatService;

  ChatMessagesCubit({
    required this.chatService,
  }) : super(MessagesLoading());

  List<ChatMessage> actualMessages = List<ChatMessage>.empty(growable: true);
  late ChatRoom actualRoom;

  Future<List<ChatMessage>> initChatMessages(ChatRoom room) async {
    emit(MessagesLoading());
    actualRoom = room;
    actualMessages.clear();
    actualMessages = await chatService.getChatMessages(roomId: room.id);
    emit(MessagesLoaded(
      messages: actualMessages,
      chatRoom: room,
    ));
    return actualMessages;
  }

  Future<void> receiveMessage(ChatMessage receivedMessage, {bool isLocal = false}) async {
    if (receivedMessage.roomId == actualRoom.id) {
      List<ChatMessage> latestMessage = actualMessages.where((ChatMessage e) => e.id == receivedMessage.id).toList();
      if( !isLocal && latestMessage.isNotEmpty ) {
        int messageIndex = actualMessages.indexOf(latestMessage.first);
        actualMessages.replaceRange(messageIndex, 1, <ChatMessage>[receivedMessage]);
      } else {
        actualMessages.insert(0, receivedMessage);
      }

      emit(MessagesLoading());
      emit(MessagesLoaded(
        messages: actualMessages,
        chatRoom: actualRoom,
      ));
    }
  }
}
