import 'package:auto_route/auto_route.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:projekt/blocs/specifics_blocs/chat_rooms_cubit/chat_rooms_cubit.dart';
import 'package:projekt/blocs/specifics_blocs/search_cubit/search_cubit.dart';
import 'package:projekt/config/locator.dart';
import 'package:projekt/infra/dto/auth/users/public_user_data.dart';
import 'package:projekt/infra/dto/chat/chat_room.dart';
import 'package:projekt/infra/services/chat/chat_service.dart';
import 'package:projekt/providers/account_provider.dart';
import 'package:projekt/shared/api_manager.dart';
import 'package:projekt/shared/models/account.dart';
import 'package:projekt/shared/router/router.gr.dart';

class SearchPage extends StatefulWidget {
  const SearchPage({Key? key}) : super(key: key);

  @override
  _SearchPageState createState() => _SearchPageState();
}

class _SearchPageState extends State<SearchPage> {
  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        elevation: 0,
        title: TextField(
          onChanged: (String value) {
            BlocProvider.of<SearchCubit>(context).search(value);
          },
          cursorWidth: 2,
          autofocus: true,
          decoration: const InputDecoration(
            border: InputBorder.none,
            hintText: 'Search',
            enabledBorder: InputBorder.none,
          ),
        ),
        leading: IconButton(
          icon: const Icon(Icons.arrow_back, size: 18),
          onPressed: () {
            Navigator.of(context).pop();
          },
        ),
      ),
      body: BlocBuilder<SearchCubit, SearchState>(
        builder: (BuildContext context, SearchState state) {
          if (state is SearchFound) {
            return _buildResultList(state.result);
          }
          return const Text('wpisz jakiś tekst, żeby wyszukać użytkownika');
        },
      ),
    );
  }

  Widget _buildResultList(List<dynamic> items) {
    return ListView.builder(
      itemCount: items.length,
      itemBuilder: (context, index) {
        PublicUserData user = items[index];
        return ListTile(
          onTap: () async {
            Account account = globalLocator<AccountProvider>().account!;
            ChatRoom chatRoom = await globalLocator<ChatService>().createRoom(usersId: <String>[user.id, account.id]);
            BlocProvider.of<ChatRoomsCubit>(context).initChatRooms();
            AutoRouter.of(context).navigate(ChatMessagesRoute(chatRoom: chatRoom));
          },
          title: Text('${user.firstName} ${user.lastName}'),
          subtitle: Text(user.email),
          leading: CircleAvatar(
            backgroundImage: user.avatar?.id != null
                ? NetworkImage('${ApiManager.networkUrl}/drive/uploads/${user.avatar!.id}')
                : null,
          ),
        );
      },
    );
  }
}
