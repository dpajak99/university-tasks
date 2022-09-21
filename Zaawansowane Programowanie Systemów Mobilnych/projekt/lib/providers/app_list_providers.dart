import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:projekt/blocs/specifics_blocs/chat_cubit/chat_cubit.dart';
import 'package:projekt/blocs/specifics_blocs/chat_messages_cubit/chat_messages_cubit.dart';
import 'package:projekt/blocs/specifics_blocs/chat_rooms_cubit/chat_rooms_cubit.dart';
import 'package:projekt/blocs/specifics_blocs/friends_cubit/friends_cubit.dart';
import 'package:projekt/blocs/specifics_blocs/search_cubit/search_cubit.dart';
import 'package:projekt/blocs/specifics_blocs/websocket_listener_cubit/websocket_listener_cubit.dart';
import 'package:projekt/config/locator.dart';
import 'package:projekt/infra/services/auth/users_service.dart';
import 'package:projekt/infra/services/chat/chat_service.dart';
import 'package:projekt/infra/sockets/chat_socket.dart';
import 'package:projekt/providers/account_provider.dart';
import 'package:provider/provider.dart';
import 'package:provider/single_child_widget.dart';

List<SingleChildWidget> appListProviders = <SingleChildWidget>[
  ChangeNotifierProvider<AccountProvider>.value(
    value: globalLocator<AccountProvider>(),
  ),
  BlocProvider<WebsocketListenerCubit>(
    lazy: false,
    create: (BuildContext context) => WebsocketListenerCubit(
      chatSocket: globalLocator<ChatSocket>(),
    ),
  ),
  BlocProvider<ChatMessagesCubit>(
    lazy: false,
    create: (BuildContext context) => ChatMessagesCubit(
      chatService: globalLocator<ChatService>(),
    ),
  ),
  BlocProvider<ChatRoomsCubit>(
    lazy: false,
    create: (BuildContext context) => ChatRoomsCubit(
      chatService: globalLocator<ChatService>(),
    ),
  ),
  BlocProvider<SearchCubit>(
    lazy: false,
    create: (BuildContext context) => SearchCubit(
      usersService: globalLocator<UsersService>(),
      accountProvider: globalLocator<AccountProvider>(),
    ),
  ),
  BlocProvider<FriendsCubit>(
    lazy: false,
    create: (BuildContext context) => FriendsCubit(
      chatService: globalLocator<ChatService>(),
      accountProvider: globalLocator<AccountProvider>(),
    ),
  ),
  BlocProvider<ChatCubit>(
    lazy: false,
    create: (BuildContext context) => ChatCubit(
      chatMessagesCubit: BlocProvider.of<ChatMessagesCubit>(context),
      chatRoomsCubit: BlocProvider.of<ChatRoomsCubit>(context),
      websocketListenerCubit: BlocProvider.of<WebsocketListenerCubit>(context),
      chatService: globalLocator<ChatService>(),
    ),
  ),
];
