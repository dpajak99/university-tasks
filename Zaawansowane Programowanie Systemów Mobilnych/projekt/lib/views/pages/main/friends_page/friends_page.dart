import 'package:flutter/cupertino.dart';
import 'package:projekt/views/widgets/generic/pretty_scroll_view.dart';

class FriendsPage extends StatefulWidget {
  const FriendsPage({Key? key}) : super(key: key);

  @override
  State<StatefulWidget> createState() => _FriendsPage();
}

class _FriendsPage extends State<FriendsPage> {
  @override
  Widget build(BuildContext context) {
    return PrettyScrollView(
      title: 'Friends',
      actions: const [],
      body: Container(),
    );
  }
}
