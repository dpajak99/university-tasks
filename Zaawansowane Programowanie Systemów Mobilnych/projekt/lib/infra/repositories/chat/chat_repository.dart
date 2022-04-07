import 'package:dio/dio.dart';
import 'package:projekt/shared/api_manager.dart';

abstract class ChatRepository {
  Future<List<dynamic>> fetchChatMessages({required String roomId});

  Future<Map<String, dynamic>> fetchMessage({required String messageId});

  Future<List<dynamic>> fetchUserRooms();

  Future<Map<String, dynamic>> fetchRoomDetails({required String roomId});

  Future<Map<String, dynamic>> createRoom({required List<String> usersId});
}

class RemoteChatRepository extends ChatRepository {
  final ApiManager api = ApiManager();

  @override
  Future<List<dynamic>> fetchChatMessages({required String roomId}) async {
    final Response<List<dynamic>> response = await api.get(
      path: '/chat/room/$roomId/messages',
    );
    return response.data!;
  }

  @override
  Future<Map<String, dynamic>> fetchMessage({required String messageId}) async {
    final Response<Map<String, dynamic>> response = await api.get(
      path: '/chat/messages/$messageId',
    );
    return response.data!;
  }

  @override
  Future<List<dynamic>> fetchUserRooms() async {
    final Response<List<dynamic>> response = await api.get(
      path: '/user/chat/rooms',
    );
    return response.data!;
  }

  @override
  Future<Map<String, dynamic>> fetchRoomDetails({required String roomId}) async {
    final Response<Map<String, dynamic>> response = await api.get(
      path: '/chat/room',
      queryParameters: <String, dynamic>{
        'roomId': roomId,
      },
    );
    return response.data!;
  }

  @override
  Future<Map<String, dynamic>> createRoom({required List<String> usersId}) async {
    final Response<Map<String, dynamic>> response = await api.post(
      path: '/chat/room',
      body: <String, dynamic>{
        'users': usersId,
      },
    );
    return response.data!;
  }
}
