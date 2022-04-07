import 'package:flutter/widgets.dart';
import 'package:projekt/infra/dto/auth/users/public_user_data.dart';

/// Used to make provided [types.User] class available through the whole package
class InheritedUser extends InheritedWidget {
  /// Creates [InheritedWidget] from a provided [types.User] class
  const InheritedUser({
    required this.user,
    required Widget child,
    Key? key,
  }) : super(key: key, child: child);

  /// Represents current logged in user. Used to determine message's author.
  final PublicUserData user;

  static InheritedUser of(BuildContext context) {
    return context.dependOnInheritedWidgetOfExactType<InheritedUser>()!;
  }

  @override
  bool updateShouldNotify(InheritedUser oldWidget) =>
      user.id != oldWidget.user.id;
}