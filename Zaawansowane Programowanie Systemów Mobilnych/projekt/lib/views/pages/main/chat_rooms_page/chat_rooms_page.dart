import 'package:auto_route/auto_route.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:intl/intl.dart';
import 'package:projekt/blocs/specifics_blocs/chat_cubit/chat_cubit.dart';
import 'package:projekt/blocs/specifics_blocs/chat_rooms_cubit/chat_rooms_cubit.dart';
import 'package:projekt/blocs/specifics_blocs/websocket_listener_cubit/websocket_listener_cubit.dart';
import 'package:projekt/infra/dto/chat/chat_room.dart';
import 'package:projekt/shared/router/router.gr.dart';
import 'package:projekt/views/widgets/center_load_spinner.dart';
import 'package:projekt/views/widgets/chat/chat_avatar.dart';
import 'package:projekt/views/widgets/generic/pretty_scroll_view.dart';

class ChatRoomsPage extends StatefulWidget {
  const ChatRoomsPage({Key? key}) : super(key: key);

  @override
  State<StatefulWidget> createState() => _ChatRoomsPage();
}

class _ChatRoomsPage extends State<ChatRoomsPage> {
  @override
  Widget build(BuildContext context) {
    return PrettyScrollView(
      title: 'Messages',
      actions: <Widget>[
        IconButton(
          onPressed: () {
            AutoRouter.of(context).navigate(const SearchRoute());
          },
          icon: Icon(
            Icons.add,
            color: Theme.of(context).primaryColor,
          ),
        ),
      ],
      body: Column(
        children: <Widget>[
          const SizedBox(height: 15),
          Container(
            width: double.infinity,
            padding: const EdgeInsets.symmetric(horizontal: 10),
            child: TextFormField(
              decoration: InputDecoration(
                prefixIcon: const Icon(Icons.search),
                hintText: 'Search',
                fillColor: Colors.grey[200],
                filled: true,
                border: OutlineInputBorder(
                  borderRadius: BorderRadius.circular(30),
                  borderSide: BorderSide.none,
                ),
                enabledBorder: OutlineInputBorder(
                  borderRadius: BorderRadius.circular(30),
                  borderSide: BorderSide.none,
                ),
                focusedBorder: OutlineInputBorder(
                  borderRadius: BorderRadius.circular(30),
                  borderSide: BorderSide.none,
                ),
              ),
            ),
          ),
          const SizedBox(height: 15),
          BlocBuilder<ChatRoomsCubit, ChatRoomsState>(
            builder: (BuildContext context, ChatRoomsState state) {
              if (state is RoomsLoaded) {
                print(state.rooms.length);
                return ListView.builder(
                  shrinkWrap: true,
                  itemCount: state.rooms.length,
                  itemBuilder: (BuildContext context, int index) {
                    ChatRoom room = state.rooms[index];
                    return _ChatRoomListItem(room: room);
                  },
                );
              }
              return const CenterLoadSpinner();
            },
          ),
        ],
      ),
    );
  }
}

class _ChatRoomListItem extends StatelessWidget {
  final ChatRoom room;

  const _ChatRoomListItem({
    required this.room,
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return InkWell(
      onTap: () {
        AutoRouter.of(context).navigate(
          ChatMessagesRoute(
            chatRoom: room,
          ),
        );
      },
      child: Padding(
        padding: const EdgeInsets.symmetric(horizontal: 10),
        child: Row(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: <Widget>[
            ChatAvatar(
              chatRoom: room,
              size: 50,
            ),
            Expanded(
              child: ListTile(
                title: Text(
                  room.getName(),
                  overflow: TextOverflow.ellipsis,
                ),
                subtitle: Text(room.lastChatMessage?.getLastMessageText() ?? 'Brak wiadomości',
                    overflow: TextOverflow.ellipsis),
              ),
            ),
            SizedBox(
              width: 50,
              child: Text(DateFormat("dd.MM").format(DateTime.fromMillisecondsSinceEpoch(room.updatedAt))),
            )
          ],
        ),
      ),
    );
  }
}
