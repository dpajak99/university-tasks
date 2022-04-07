import 'package:projekt/infra/dto/auth/users/public_user_data.dart';
import 'package:projekt/infra/dto/chat/chat_message.dart';
import 'package:projekt/infra/dto/response/FileResponse.dart';
import 'package:projekt/shared/utils/enums.dart';

enum RoomType { channel, direct, group }

class ChatRoom {
  final String id;
  final String? name;
  final RoomType roomType;
  final List<PublicUserData> users;
  final int updatedAt;
  final int createdAt;
  final Map<String, dynamic> metadata;
  final FileResponse? image;
  ChatMessage? lastChatMessage;

  ChatRoom({
    required this.id,
    required this.roomType,
    required this.users,
    required this.updatedAt,
    required this.createdAt,
    required this.metadata,
    required this.name,
    required this.image,
  });

  factory ChatRoom.fromJson(Map<String, dynamic> json) {
    return ChatRoom(
      id: json['id'] as String,
      roomType: enumFromString(RoomType.values, json['roomType'] as String),
      users: (json['users'] as List).map((e) => PublicUserData.fromJson(e as Map<String, dynamic>)).toList(),
      updatedAt: json['updatedAt'] as int,
      createdAt: json['createdAt'] as int,
      metadata: json['metadata'] as Map<String, dynamic>,
      name: json['name'] as String?,
      image: json['image'] == null ? null : FileResponse.fromJson(json['image'] as Map<String, dynamic>),
    );
  }
}
