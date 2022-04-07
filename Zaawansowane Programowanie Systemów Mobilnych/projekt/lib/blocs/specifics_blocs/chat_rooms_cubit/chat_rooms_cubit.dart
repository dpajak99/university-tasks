import 'package:equatable/equatable.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:projekt/infra/dto/chat/chat_message.dart';
import 'package:projekt/infra/dto/chat/chat_room.dart';
import 'package:projekt/infra/services/chat/chat_service.dart';

part 'chat_rooms_state.dart';

class ChatRoomsCubit extends Cubit<ChatRoomsState> {
  final ChatService chatService;

  ChatRoomsCubit({
    required this.chatService,
  }) : super(RoomsLoading());

  List<ChatRoom> actualChatRooms = List<ChatRoom>.empty(growable: true);

  Future<List<ChatRoom>> initChatRooms() async {
    actualChatRooms.clear();
    actualChatRooms = await chatService.getUserRooms();
    emit(RoomsLoaded(rooms: actualChatRooms));
    return actualChatRooms;
  }

  Future<void> receiveMessage(ChatMessage chatMessage) async {
    for(ChatRoom chatRoom in actualChatRooms ) {
      if( chatRoom.id == chatMessage.roomId) {
        chatRoom.lastChatMessage = chatMessage;
      }
      emit(RoomsLoading());
      emit(RoomsLoaded(rooms: actualChatRooms));
    }
  }
}
