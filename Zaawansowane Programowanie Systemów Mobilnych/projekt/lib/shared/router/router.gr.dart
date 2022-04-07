// **************************************************************************
// AutoRouteGenerator
// **************************************************************************

// GENERATED CODE - DO NOT MODIFY BY HAND

// **************************************************************************
// AutoRouteGenerator
// **************************************************************************
//
// ignore_for_file: type=lint

import 'package:auto_route/auto_route.dart' as _i11;
import 'package:flutter/material.dart' as _i12;

import '../../infra/dto/chat/chat_room.dart' as _i14;
import '../../views/pages/auth/auth_wrapper.dart' as _i2;
import '../../views/pages/auth/login_page/login_page.dart' as _i6;
import '../../views/pages/auth/register_page/register_page.dart' as _i7;
import '../../views/pages/chat_messages_page/chat_messages_page.dart' as _i4;
import '../../views/pages/core_wrapper.dart' as _i1;
import '../../views/pages/main/chat_rooms_page/chat_rooms_page.dart' as _i8;
import '../../views/pages/main/friends_page/friends_page.dart' as _i9;
import '../../views/pages/main/main_wrapper.dart' as _i3;
import '../../views/pages/main/profile_page/profile_page.dart' as _i10;
import '../../views/pages/search_page/search_page.dart' as _i5;
import '../guards/auth_guard.dart' as _i13;

class AppRouter extends _i11.RootStackRouter {
  AppRouter(
      {_i12.GlobalKey<_i12.NavigatorState>? navigatorKey,
      required this.authGuard})
      : super(navigatorKey);

  final _i13.AuthGuard authGuard;

  @override
  final Map<String, _i11.PageFactory> pagesMap = {
    CoreRoute.name: (routeData) {
      return _i11.CustomPage<void>(
          routeData: routeData,
          child: const _i1.CoreWrapper(),
          opaque: true,
          barrierDismissible: false);
    },
    AuthWrapperRoute.name: (routeData) {
      return _i11.CustomPage<void>(
          routeData: routeData,
          child: const _i2.AuthWrapper(),
          opaque: true,
          barrierDismissible: false);
    },
    MainWrapperRoute.name: (routeData) {
      return _i11.CustomPage<void>(
          routeData: routeData,
          child: const _i3.MainWrapper(),
          opaque: true,
          barrierDismissible: false);
    },
    ChatMessagesRoute.name: (routeData) {
      final args = routeData.argsAs<ChatMessagesRouteArgs>();
      return _i11.CustomPage<void>(
          routeData: routeData,
          child: _i4.ChatMessagesPage(chatRoom: args.chatRoom, key: args.key),
          opaque: true,
          barrierDismissible: false);
    },
    SearchRoute.name: (routeData) {
      return _i11.CustomPage<void>(
          routeData: routeData,
          child: const _i5.SearchPage(),
          opaque: true,
          barrierDismissible: false);
    },
    LoginRoute.name: (routeData) {
      return _i11.CustomPage<void>(
          routeData: routeData,
          child: const _i6.LoginPage(),
          opaque: true,
          barrierDismissible: false);
    },
    RegisterRoute.name: (routeData) {
      return _i11.CustomPage<void>(
          routeData: routeData,
          child: const _i7.RegisterPage(),
          opaque: true,
          barrierDismissible: false);
    },
    ChatRoomsRoute.name: (routeData) {
      return _i11.CustomPage<void>(
          routeData: routeData,
          child: const _i8.ChatRoomsPage(),
          opaque: true,
          barrierDismissible: false);
    },
    FriendsRoute.name: (routeData) {
      return _i11.CustomPage<void>(
          routeData: routeData,
          child: const _i9.FriendsPage(),
          opaque: true,
          barrierDismissible: false);
    },
    ProfileRoute.name: (routeData) {
      return _i11.CustomPage<void>(
          routeData: routeData,
          child: const _i10.ProfilePage(),
          opaque: true,
          barrierDismissible: false);
    }
  };

  @override
  List<_i11.RouteConfig> get routes => [
        _i11.RouteConfig(CoreRoute.name, path: '/', children: [
          _i11.RouteConfig(AuthWrapperRoute.name,
              path: 'auth',
              parent: CoreRoute.name,
              children: [
                _i11.RouteConfig(LoginRoute.name,
                    path: 'login', parent: AuthWrapperRoute.name),
                _i11.RouteConfig(RegisterRoute.name,
                    path: 'register', parent: AuthWrapperRoute.name),
                _i11.RouteConfig('#redirect',
                    path: '',
                    parent: AuthWrapperRoute.name,
                    redirectTo: 'login',
                    fullMatch: true)
              ]),
          _i11.RouteConfig(MainWrapperRoute.name,
              path: 'main',
              parent: CoreRoute.name,
              guards: [
                authGuard
              ],
              children: [
                _i11.RouteConfig(ChatRoomsRoute.name,
                    path: 'rooms', parent: MainWrapperRoute.name),
                _i11.RouteConfig(FriendsRoute.name,
                    path: 'friends', parent: MainWrapperRoute.name),
                _i11.RouteConfig(ProfileRoute.name,
                    path: 'profile', parent: MainWrapperRoute.name),
                _i11.RouteConfig('#redirect',
                    path: '',
                    parent: MainWrapperRoute.name,
                    redirectTo: 'rooms',
                    fullMatch: true)
              ]),
          _i11.RouteConfig(ChatMessagesRoute.name,
              path: 'messages', parent: CoreRoute.name),
          _i11.RouteConfig(SearchRoute.name,
              path: 'search', parent: CoreRoute.name),
          _i11.RouteConfig('#redirect',
              path: '',
              parent: CoreRoute.name,
              redirectTo: 'main',
              fullMatch: true)
        ])
      ];
}

/// generated route for
/// [_i1.CoreWrapper]
class CoreRoute extends _i11.PageRouteInfo<void> {
  const CoreRoute({List<_i11.PageRouteInfo>? children})
      : super(CoreRoute.name, path: '/', initialChildren: children);

  static const String name = 'CoreRoute';
}

/// generated route for
/// [_i2.AuthWrapper]
class AuthWrapperRoute extends _i11.PageRouteInfo<void> {
  const AuthWrapperRoute({List<_i11.PageRouteInfo>? children})
      : super(AuthWrapperRoute.name, path: 'auth', initialChildren: children);

  static const String name = 'AuthWrapperRoute';
}

/// generated route for
/// [_i3.MainWrapper]
class MainWrapperRoute extends _i11.PageRouteInfo<void> {
  const MainWrapperRoute({List<_i11.PageRouteInfo>? children})
      : super(MainWrapperRoute.name, path: 'main', initialChildren: children);

  static const String name = 'MainWrapperRoute';
}

/// generated route for
/// [_i4.ChatMessagesPage]
class ChatMessagesRoute extends _i11.PageRouteInfo<ChatMessagesRouteArgs> {
  ChatMessagesRoute({required _i14.ChatRoom chatRoom, _i12.Key? key})
      : super(ChatMessagesRoute.name,
            path: 'messages',
            args: ChatMessagesRouteArgs(chatRoom: chatRoom, key: key));

  static const String name = 'ChatMessagesRoute';
}

class ChatMessagesRouteArgs {
  const ChatMessagesRouteArgs({required this.chatRoom, this.key});

  final _i14.ChatRoom chatRoom;

  final _i12.Key? key;

  @override
  String toString() {
    return 'ChatMessagesRouteArgs{chatRoom: $chatRoom, key: $key}';
  }
}

/// generated route for
/// [_i5.SearchPage]
class SearchRoute extends _i11.PageRouteInfo<void> {
  const SearchRoute() : super(SearchRoute.name, path: 'search');

  static const String name = 'SearchRoute';
}

/// generated route for
/// [_i6.LoginPage]
class LoginRoute extends _i11.PageRouteInfo<void> {
  const LoginRoute() : super(LoginRoute.name, path: 'login');

  static const String name = 'LoginRoute';
}

/// generated route for
/// [_i7.RegisterPage]
class RegisterRoute extends _i11.PageRouteInfo<void> {
  const RegisterRoute() : super(RegisterRoute.name, path: 'register');

  static const String name = 'RegisterRoute';
}

/// generated route for
/// [_i8.ChatRoomsPage]
class ChatRoomsRoute extends _i11.PageRouteInfo<void> {
  const ChatRoomsRoute() : super(ChatRoomsRoute.name, path: 'rooms');

  static const String name = 'ChatRoomsRoute';
}

/// generated route for
/// [_i9.FriendsPage]
class FriendsRoute extends _i11.PageRouteInfo<void> {
  const FriendsRoute() : super(FriendsRoute.name, path: 'friends');

  static const String name = 'FriendsRoute';
}

/// generated route for
/// [_i10.ProfilePage]
class ProfileRoute extends _i11.PageRouteInfo<void> {
  const ProfileRoute() : super(ProfileRoute.name, path: 'profile');

  static const String name = 'ProfileRoute';
}
