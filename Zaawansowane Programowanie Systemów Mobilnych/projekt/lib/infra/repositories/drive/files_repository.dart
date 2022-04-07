import 'package:dio/dio.dart';
import 'package:projekt/shared/api_manager.dart';

abstract class FilesRepository {
  Future<Map<String, dynamic>> upload({required MultipartFile file});
}

class RemoteFilesRepository extends FilesRepository {
  final ApiManager api = ApiManager();

  @override
  Future<Map<String, dynamic>> upload({required MultipartFile file}) async {
    final Response<Map<String, dynamic>> response = await api.post(
      path: '/drive/upload',
      body: FormData.fromMap(<String, dynamic>{
        'file': file,
      }),
    );
    return response.data!;
  }
}
