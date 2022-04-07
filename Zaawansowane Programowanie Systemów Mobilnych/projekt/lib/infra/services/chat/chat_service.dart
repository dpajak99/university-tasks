
import 'package:projekt/config/locator.dart';
import 'package:projekt/infra/dto/chat/chat_message.dart';
import 'package:projekt/infra/dto/chat/chat_room.dart';
import 'package:projekt/infra/repositories/chat/chat_repository.dart';

class ChatService {
  final ChatRepository chatRepository = globalLocator<ChatRepository>();

  Future<List<ChatMessage>> getChatMessages({required String roomId}) async {
    List<dynamic> response = await chatRepository.fetchChatMessages(roomId: roomId);
    List<ChatMessage> messages = response.map((dynamic e) => ChatMessage.fromJson(e as Map<String, dynamic>)).toList();
    return messages;
  }

  Future<ChatMessage> getMessage({required String messageId}) async {
   Map<String, dynamic> response = await chatRepository.fetchMessage(messageId: messageId);
   ChatMessage chatMessage = ChatMessage.fromJson(response);
   return chatMessage;
  }

  Future<List<ChatRoom>> getUserRooms() async {
    List<dynamic> response = await chatRepository.fetchUserRooms();
    List<ChatRoom> rooms = response.map((dynamic e) => ChatRoom.fromJson(e as Map<String, dynamic>)).toList();
    return rooms;
  }

  Future<ChatRoom> getRoomDetails({required String roomId}) async {
    Map<String, dynamic> response = await chatRepository.fetchRoomDetails(roomId: roomId);
    ChatRoom chatRoom = ChatRoom.fromJson(response);
    return chatRoom;
  }

  Future<ChatRoom> createRoom({required List<String> usersId}) async {
    Map<String, dynamic> response = await chatRepository.createRoom(usersId: usersId);
    ChatRoom chatRoom = ChatRoom.fromJson(response);
    return chatRoom;
  }
}
