import 'package:projekt/config/locator.dart';
import 'package:projekt/infra/dto/auth/users/public_user_data.dart';
import 'package:projekt/infra/repositories/auth/admin_repository.dart';

class AdminService {
  final AdminRepository adminRepository = globalLocator<AdminRepository>();

  Future<List<PublicUserData>> getAllUsers() async {
    List<dynamic> response = await adminRepository.fetchUsers(0);
    List<PublicUserData> usersList = response.map((dynamic e) => PublicUserData.fromJson(e as Map<String, dynamic>)).toList();
    return usersList;
  }
}
