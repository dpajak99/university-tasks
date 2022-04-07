import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:projekt/config/locator.dart';
import 'package:projekt/providers/account_provider.dart';
import 'package:projekt/shared/api_manager.dart';
import 'package:projekt/shared/models/account.dart';

class ProfileAvatar extends StatelessWidget {
  final double size;

  const ProfileAvatar({
    this.size = 30,
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    Account? account = globalLocator<AccountProvider>().account;

    return SizedBox(
      width: size,
      height: size,
      child: CircleAvatar(
        radius: size / 2,
        backgroundImage:
            account?.avatarId != null ? NetworkImage('${ApiManager.networkUrl}/drive/files/${account!.avatarId}') : null,
      ),
    );
  }
}
