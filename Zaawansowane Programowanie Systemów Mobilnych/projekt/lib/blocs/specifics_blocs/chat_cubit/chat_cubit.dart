import 'package:equatable/equatable.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:projekt/blocs/specifics_blocs/chat_messages_cubit/chat_messages_cubit.dart';
import 'package:projekt/blocs/specifics_blocs/chat_rooms_cubit/chat_rooms_cubit.dart';
import 'package:projekt/blocs/specifics_blocs/websocket_listener_cubit/websocket_listener_cubit.dart';
import 'package:projekt/config/locator.dart';
import 'package:projekt/infra/dto/chat/chat_message.dart';
import 'package:projekt/infra/dto/chat/chat_room.dart';
import 'package:projekt/infra/services/chat/chat_service.dart';
import 'package:projekt/providers/account_provider.dart';

part 'chat_state.dart';

class ChatCubit extends Cubit<ChatState> {
  final WebsocketListenerCubit websocketListenerCubit;
  final ChatMessagesCubit chatMessagesCubit;
  final ChatRoomsCubit chatRoomsCubit;
  final ChatService chatService;


  ChatCubit({
    required this.websocketListenerCubit,
    required this.chatMessagesCubit,
    required this.chatRoomsCubit,
    required this.chatService,
  }) : super(ChatLoading());

  Future<void> init() async {
    refresh();
    websocketListenerCubit.stream.listen((WebsocketListenerState state) {
      if( state is ReceivedChatMessage) {
        receiveMessage(state.message);
      }
    });

    globalLocator<AccountProvider>().addListener(refresh);
  }

  Future<void> refresh() async {
    List<ChatRoom> chatRooms = await chatRoomsCubit.initChatRooms();

    if( chatRooms.isNotEmpty ) {
      await chatMessagesCubit.initChatMessages(chatRooms.first);
    }
  }

  void updateChatRoom(ChatRoom chatRoom) {
    chatMessagesCubit.initChatMessages(chatRoom);
  }

  void sendMessage(ChatMessage message) {
    websocketListenerCubit.chatSocket.sendMessage(message.toJson());
    receiveMessage(message, isLocal: true);
  }

  Future<void> receiveMessage(ChatMessage message, {bool isLocal = false}) async {
    if( !isLocal ) {
      await Future<void>.delayed(const Duration(milliseconds: 500));
    }
    await chatMessagesCubit.receiveMessage(message, isLocal: isLocal);
    await chatRoomsCubit.receiveMessage(message);
  }
}
