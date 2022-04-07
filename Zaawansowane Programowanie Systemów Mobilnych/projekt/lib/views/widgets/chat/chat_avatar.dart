import 'package:flutter/material.dart';
import 'package:projekt/infra/dto/chat/chat_room.dart';
import 'package:projekt/shared/api_manager.dart';

class ChatAvatar extends StatelessWidget {
  final ChatRoom chatRoom;
  final double size;

  const ChatAvatar({
    required this.chatRoom,
    required this.size,
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      height: size,
      width: size,
      decoration: BoxDecoration(
        border: Border.all(
          color: const Color(0xffeeeeee),
          width: size < 50 ? 1 : 2,
        ),
        borderRadius: BorderRadius.circular(500),
      ),
      child: CircleAvatar(
        backgroundImage:
        chatRoom.image != null ? NetworkImage('${ApiManager.networkUrl}/drive/files/${chatRoom.image!.id}') : null,
      ),
    );
  }
}