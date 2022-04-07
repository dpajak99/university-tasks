import 'package:auto_route/annotations.dart';
import 'package:projekt/shared/guards/auth_guard.dart';
import 'package:projekt/views/pages/auth/auth_wrapper.dart';
import 'package:projekt/views/pages/auth/login_page/login_page.dart';
import 'package:projekt/views/pages/auth/register_page/register_page.dart';
import 'package:projekt/views/pages/chat_messages_page/chat_messages_page.dart';
import 'package:projekt/views/pages/core_wrapper.dart';
import 'package:projekt/views/pages/main/chat_rooms_page/chat_rooms_page.dart';
import 'package:projekt/views/pages/main/friends_page/friends_page.dart';
import 'package:projekt/views/pages/main/main_wrapper.dart';
import 'package:projekt/views/pages/main/profile_page/profile_page.dart';
import 'package:projekt/views/pages/search_page/search_page.dart';

// part 'router.gr.dart';

// ignore_for_file: always_specify_types
@CustomAutoRouter(
  preferRelativeImports: true,
  replaceInRouteName: 'Page,Route',
  routes: [
    AutoRoute<void>(
      page: CoreWrapper,
      name: 'CoreRoute',
      path: '/',
      initial: true,
      children: [
        AutoRoute<void>(
          page: AuthWrapper,
          name: 'AuthWrapperRoute',
          path: 'auth',
          children: [
            AutoRoute<void>(
              page: LoginPage,
              name: 'LoginRoute',
              path: 'login',
            ),
            AutoRoute<void>(
              page: RegisterPage,
              name: 'RegisterRoute',
              path: 'register',
            ),
            RedirectRoute(path: '', redirectTo: 'login'),
          ],
        ),
        AutoRoute<void>(
          page: MainWrapper,
          name: 'MainWrapperRoute',
          path: 'main',
          guards: [AuthGuard],
          children: [
            AutoRoute<void>(
              page: ChatRoomsPage,
              name: 'ChatRoomsRoute',
              path: 'rooms',
            ),
            AutoRoute<void>(
              page: FriendsPage,
              name: 'FriendsRoute',
              path: 'friends',
            ),
            AutoRoute<void>(
              page: ProfilePage,
              name: 'ProfileRoute',
              path: 'profile',
            ),
            RedirectRoute(path: '', redirectTo: 'rooms'),
          ],
        ),
        AutoRoute<void>(
          page: ChatMessagesPage,
          name: 'ChatMessagesRoute',
          path: 'messages',
        ),
        AutoRoute<void>(
          page: SearchPage,
          name: 'SearchRoute',
          path: 'search',
        ),
        RedirectRoute(path: '', redirectTo: 'main'),
      ],
    ),
  ],
)
class $AppRouter {}
