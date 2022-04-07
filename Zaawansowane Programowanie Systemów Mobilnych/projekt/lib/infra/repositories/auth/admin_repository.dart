import 'package:dio/dio.dart';
import 'package:projekt/shared/api_manager.dart';

abstract class AdminRepository {
  Future<List<dynamic>> fetchUsers(int page);
}

class RemoteAdminRepository extends AdminRepository {
  final ApiManager api = ApiManager();

  @override
  Future<List<dynamic>> fetchUsers(int page) async {
    final Response<List<dynamic>> response = await api.get(
      path: '/admin/users',
      queryParameters: <String, dynamic>{
        'page': page,
      }
    );
    return response.data!;
  }
}
