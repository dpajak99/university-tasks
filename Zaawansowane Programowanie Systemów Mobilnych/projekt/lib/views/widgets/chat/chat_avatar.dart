import 'package:flutter/material.dart';
import 'package:projekt/config/locator.dart';
import 'package:projekt/infra/dto/auth/users/public_user_data.dart';
import 'package:projekt/infra/dto/chat/chat_room.dart';
import 'package:projekt/providers/account_provider.dart';
import 'package:projekt/shared/api_manager.dart';
import 'package:projekt/shared/models/account.dart';

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
      child: ClipRRect(
        borderRadius: BorderRadius.circular(500),
        child: _AvatarBuilder(
          chatRoom: chatRoom,
          size: size,
        ),
      ),
    );
  }
}

class _AvatarBuilder extends StatelessWidget {
  final ChatRoom chatRoom;
  final double size;

  const _AvatarBuilder({
    required this.chatRoom,
    required this.size,
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    Account account = globalLocator<AccountProvider>().account!;

    if (chatRoom.image?.id != null) {
      return _ChatRoomAvatar(chatRoom: chatRoom, size: size);
    } else if (chatRoom.users.length > 2) {
      return _MultipleUsersAvatar(
        users: chatRoom.users.toList(),
        size: size,
      );
    } else if (chatRoom.users.length > 1) {
      return _SingleUserAvatar(
        user: chatRoom.users.where((PublicUserData e) => e.id != account.id).first,
        size: size,
      );
    } else {
      return const Center(
          child: Icon(
        Icons.error,
      ));
    }
  }
}

class _ChatRoomAvatar extends StatelessWidget {
  final ChatRoom chatRoom;
  final double size;

  const _ChatRoomAvatar({
    required this.chatRoom,
    required this.size,
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      height: size,
      width: size,
      child: Image.network(
        '${ApiManager.networkUrl}/drive/uploads/${chatRoom.image!.id}',
        fit: BoxFit.cover,
      ),
    );
  }
}

class _SingleUserAvatar extends StatelessWidget {
  final PublicUserData user;
  final double size;

  const _SingleUserAvatar({
    required this.user,
    required this.size,
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      height: size,
      width: size,
      child: Image.network(
        user.getAvatarUrl(size),
        fit: BoxFit.cover,
        errorBuilder: (context, error, stackTrace) {
          return const Center(
            child: Icon(
              Icons.error,
            ),
          );
        },
      ),
    );
  }
}

class _MultipleUsersAvatar extends StatelessWidget {
  final List<PublicUserData> users;
  final double size;

  const _MultipleUsersAvatar({
    required this.users,
    required this.size,
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      height: size,
      width: size,
      child: Row(
        children: [
          Flexible(
            child: Column(
              children: [
                Flexible(
                  child: SizedBox(
                    height: double.infinity,
                    width: double.infinity,
                    child: Image.network(
                      users[0].getAvatarUrl(size),
                      fit: BoxFit.cover,
                    ),
                  ),
                ),
                if (users.length > 3)
                  Flexible(
                    child: SizedBox(
                      height: double.infinity,
                      width: double.infinity,
                      child: Image.network(
                        users[3].getAvatarUrl(size),
                        fit: BoxFit.cover,
                      ),
                    ),
                  ),
              ],
            ),
          ),
          Flexible(
            child: Column(
              children: [
                Flexible(
                  child: SizedBox(
                    height: double.infinity,
                    width: double.infinity,
                    child: Image.network(
                      users[1].getAvatarUrl(size),
                      fit: BoxFit.cover,
                    ),
                  ),
                ),
                if (users.length > 2)
                  Flexible(
                    child: SizedBox(
                      height: double.infinity,
                      width: double.infinity,
                      child: Image.network(
                        users[2].getAvatarUrl(size),
                        fit: BoxFit.cover,
                      ),
                    ),
                  ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}
