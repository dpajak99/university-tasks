import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:projekt/config/locator.dart';
import 'package:projekt/providers/account_provider.dart';
import 'package:projekt/shared/api_manager.dart';
import 'package:projekt/shared/models/account.dart';

class ProfileAvatar extends StatelessWidget {
  final String? avatarId;
  final double size;

  const ProfileAvatar({
    required this.avatarId,
    this.size = 30,
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      width: size,
      height: size,
      child: CircleAvatar(
        radius: size / 2,
        backgroundImage:
        avatarId != null ? NetworkImage('${ApiManager.networkUrl}/drive/uploads/$avatarId') : null,
      ),
    );
  }
}
