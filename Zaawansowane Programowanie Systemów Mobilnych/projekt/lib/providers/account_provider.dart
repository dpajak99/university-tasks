import 'dart:convert';

import 'package:auto_route/auto_route.dart';
import 'package:flutter/cupertino.dart';
import 'package:hive/hive.dart';
import 'package:projekt/shared/models/account.dart';
import 'package:projekt/shared/router/router.gr.dart';

class AccountProvider extends ChangeNotifier {
  static final Box<Map<dynamic, dynamic>> _accountBox = Hive.box<Map<dynamic, dynamic>>('account');
  static const String _accountStorageKey = 'data';

  Account? account;

  AccountProvider();

  static Account? _setUpCachedLogin() {
    if (_accountBox.get(_accountStorageKey) != null) {
      Map<String, dynamic> map = json.decode(json.encode(_accountBox.get(_accountStorageKey))) as Map<String, dynamic>;
      return Account.fromJson(map);
    }
  }

  bool get isLoggedIn {
    return account != null;
  }

  void updateAccount(Account newAccount) {
    print('Updating account ${newAccount}');
    account = newAccount;
    _accountBox.put(_accountStorageKey, newAccount.toJson());
    notifyListeners();
  }

  void logout(BuildContext context) {
    account = null;
    notifyListeners();
    AutoRouter.of(context).navigate(const AuthWrapperRoute(
      children: <PageRouteInfo>[
        LoginRoute(),
      ],
    ));
  }
}
