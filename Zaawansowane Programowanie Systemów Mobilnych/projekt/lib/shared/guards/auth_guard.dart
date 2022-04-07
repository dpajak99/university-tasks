import 'package:auto_route/auto_route.dart';
import 'package:projekt/config/locator.dart';
import 'package:projekt/providers/account_provider.dart';
import 'package:projekt/shared/router/router.gr.dart';

class AuthGuard extends AutoRouteGuard {
  @override
  void onNavigation(NavigationResolver resolver, StackRouter router) {
    AccountProvider accountProvider = globalLocator<AccountProvider>();

    if(accountProvider.isLoggedIn) {
      resolver.next(true);
    } else {
      router.navigate(const AuthWrapperRoute());
    }
  }

}