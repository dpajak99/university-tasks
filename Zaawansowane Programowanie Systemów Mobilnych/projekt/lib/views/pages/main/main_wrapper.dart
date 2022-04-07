import 'package:auto_route/auto_route.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:projekt/blocs/specifics_blocs/chat_cubit/chat_cubit.dart';
import 'package:projekt/blocs/specifics_blocs/websocket_listener_cubit/websocket_listener_cubit.dart';
import 'package:projekt/config/locator.dart';
import 'package:projekt/providers/account_provider.dart';
import 'package:projekt/shared/api_manager.dart';
import 'package:projekt/shared/models/account.dart';
import 'package:projekt/shared/router/router.gr.dart';
import 'package:projekt/views/widgets/profile_avatar.dart';
import 'package:salomon_bottom_bar/salomon_bottom_bar.dart';

class MainWrapper extends StatefulWidget {
  const MainWrapper({Key? key}) : super(key: key);

  @override
  State<StatefulWidget> createState() => _MainWrapper();
}

class _MainWrapper extends State<MainWrapper> {
  @override
  void initState() {
    BlocProvider.of<WebsocketListenerCubit>(context).init();
    BlocProvider.of<ChatCubit>(context).init();
    super.initState();
  }

  @override
  void dispose() {
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    // BlocProvider.of<ChatCubit>(context).refresh();
    return AutoTabsScaffold(
      routes: const <PageRouteInfo>[
        ChatRoomsRoute(),
        FriendsRoute(),
        ProfileRoute(),
      ],
      bottomNavigationBuilder: (_, TabsRouter tabsRouter) => _BottomBarNavigation(
        tabsRouter: tabsRouter,
      ),
    );
  }
}

class _BottomBarNavigation extends StatelessWidget {
  final TabsRouter tabsRouter;

  const _BottomBarNavigation({
    required this.tabsRouter,
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {

    return Container(
      color: const Color(0xFFF1F5FB),
      child: SalomonBottomBar(
        currentIndex: tabsRouter.activeIndex,
        onTap: tabsRouter.setActiveIndex,
        items: [
          SalomonBottomBarItem(
            selectedColor: const Color(0xFF27AAFF),
            icon: const Icon(
              Icons.chat_bubble_outline,
              color: Color(0xFF141B2C),
              size: 30,
            ),
            title: const Text(
              'Posts',
              style: TextStyle(
                color: Color(0xFF141B2C),
              ),
            ),
          ),
          SalomonBottomBarItem(
            selectedColor: Colors.blue[200],
            icon: const Icon(
              Icons.person,
              size: 30,
            ),
            title: const Text('Friends'),
          ),
          SalomonBottomBarItem(
            selectedColor: Colors.pinkAccent[100],
            icon: const ProfileAvatar(),
            title: const Text('Profile'),
          ),
        ],
      ),
    );
  }
}
