import 'package:dio/dio.dart';
import 'package:projekt/shared/api_manager.dart';

abstract class DriveRepository {
  Future<List<int>> download({String path = ''});
}

class RemoteDriveRepository extends DriveRepository {
  final ApiManager api = ApiManager();

  @override
  Future<List<int>> download({String path = ''}) async {
    final List<int> response = await api.download(
      path: '/static$path',
    );
    return response;
  }
}
