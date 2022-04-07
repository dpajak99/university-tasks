import 'package:projekt/config/locator.dart';
import 'package:projekt/infra/dto/auth/users/public_user_data.dart';
import 'package:projekt/infra/repositories/auth/users_repository.dart';

class UsersService {
  final UsersRepository usersRepository = globalLocator<UsersRepository>();

  Future<PublicUserData> updateUser(PublicUserData user) async {
      dynamic response = await usersRepository.updateUser(user);
      PublicUserData newUserData = PublicUserData.fromJson(response as Map<String, dynamic>);
      return newUserData;
  }

  Future<List<PublicUserData>> getAll(String searchPattern) async {
    dynamic response = await usersRepository.getAll(searchPattern);
    List<PublicUserData> users = (response as List<dynamic>).map((e) => PublicUserData.fromJson(e as Map<String, dynamic>)).toList();
    return users;
  }
}
