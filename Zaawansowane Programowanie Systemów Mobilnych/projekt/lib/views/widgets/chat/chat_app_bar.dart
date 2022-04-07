import 'package:flutter/material.dart';
import 'package:projekt/infra/dto/chat/chat_room.dart';
import 'package:projekt/views/widgets/chat/chat_avatar.dart';

class ChatAppBar extends StatelessWidget implements PreferredSizeWidget {
  final ChatRoom chatRoom;

  const ChatAppBar({
    required this.chatRoom,
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      height: 60,
      decoration: const BoxDecoration(
        border: Border(
          bottom: BorderSide(
            width: 1,
            color: Colors.black12,
          ),
        ),
      ),
      child: Padding(
        padding: const EdgeInsets.symmetric(horizontal: 20, vertical: 7),
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: <Widget>[
            IconButton(
              onPressed: () {
                Navigator.of(context).pop();
              },
              icon: const Icon(Icons.arrow_back),
            ),
            ChatAvatar(
              chatRoom: chatRoom,
              size: 35,
            ),
            const SizedBox(width: 15),
            Text(
              chatRoom.name ?? chatRoom.users.map((e) => e.firstName).join(', '),
            ),
            const Spacer(),
            IconButton(
              onPressed: () {},
              icon: const Icon(Icons.info_outline),
            ),
          ],
        ),
      ),
    );
  }

  @override
  Size get preferredSize => const Size.fromHeight(60);
}
