import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:projekt/config/locator.dart';
import 'package:projekt/providers/account_provider.dart';
import 'package:projekt/shared/models/account.dart';
import 'package:projekt/views/widgets/generic/pretty_scroll_view.dart';
import 'package:projekt/views/widgets/profile_avatar.dart';

class ProfilePage extends StatefulWidget {
  const ProfilePage({Key? key}) : super(key: key);

  @override
  State<StatefulWidget> createState() => _ProfilePage();
}

class _ProfilePage extends State<ProfilePage> {

  @override
  void initState() {
    globalLocator<AccountProvider>().addListener(() {
      if(mounted) {
        setState(() {});
      }
    });
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    Account account = globalLocator<AccountProvider>().account!;
    return PrettyScrollView(
      title: 'Profile',
      actions: const [],
      body: Column(
        crossAxisAlignment: CrossAxisAlignment.center,
        children: <Widget>[
          const SizedBox(height: 16),
          ProfileAvatar(size: 100, avatarId: account.avatar?.id,),
          const SizedBox(height: 16),
          Text(
            '${account.firstName} ${account.lastName}',
            style: const TextStyle(
              fontSize: 24,
              fontWeight: FontWeight.bold,
            ),
          ),
          ListTile(
            onTap: () {
              globalLocator<AccountProvider>().logout(context);
            },
            title: const Text('wyloguj'),
            leading: const Icon(Icons.exit_to_app),
          ),
        ],
      ),
    );
  }
}
