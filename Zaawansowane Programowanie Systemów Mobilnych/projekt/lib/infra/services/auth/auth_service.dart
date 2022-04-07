import 'package:projekt/config/locator.dart';
import 'package:projekt/infra/dto/auth/login/request/login_request.dart';
import 'package:projekt/infra/dto/auth/register/request/register_req.dart';
import 'package:projekt/infra/dto/auth/register/response/register_resp.dart';
import 'package:projekt/infra/dto/auth/users/public_user_data.dart';
import 'package:projekt/infra/repositories/auth/auth_repository.dart';
import 'package:projekt/infra/services/auth/users_service.dart';
import 'package:projekt/providers/account_provider.dart';
import 'package:projekt/shared/models/account.dart';

class AuthService {
  final AuthRepository authRepository = globalLocator<AuthRepository>();

  Future<Account> login(String email, String password) async {
    Account account = Account.fromJson(await authRepository.login(
      LoginRequest(
        email: email,
        password: password,
      ),
    ));

    globalLocator<AccountProvider>().updateAccount(account);
    return account;
  }

  Future<void> register(RegisterRequest registerRequest) async {
    await authRepository.register(registerRequest);
  }
}
