import 'package:dio/dio.dart';
import 'package:projekt/infra/dto/auth/users/public_user_data.dart';
import 'package:projekt/shared/api_manager.dart';

abstract class UsersRepository {
  Future<dynamic> updateUser(PublicUserData publicUserData);
  Future<dynamic> getAll(String searchPattern);
}

class RemoteUsersRepository extends UsersRepository {
  final ApiManager api = ApiManager();

  @override
  Future<dynamic> updateUser(PublicUserData publicUserData) async {
    final Response<dynamic> response = await api.post<dynamic>(
      path: '/admin/users/${publicUserData.id}',
      body: publicUserData.toJson(),
    );
    return response.data!;
  }

  @override
  Future<dynamic> getAll(String searchPattern) async {
    final Response<dynamic> response = await api.get<dynamic>(
      path: '/admin/users',
      queryParameters: <String, dynamic>{
        'searchPattern': searchPattern,
      },
    );
    return response.data!;
  }
}
