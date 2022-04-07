import 'package:dio/dio.dart';
import 'package:file_picker/file_picker.dart';
import 'package:projekt/config/locator.dart';
import 'package:projekt/infra/dto/response/FileResponse.dart';
import 'package:projekt/infra/repositories/drive/files_repository.dart';

class FilesService {
  final FilesRepository filesRepository = globalLocator<FilesRepository>();

  Future<FileResponse> upload(PlatformFile platformFile) async {
    print('BEFORE PREPARE ${platformFile}');
      MultipartFile multipartFile = MultipartFile.fromBytes(
        platformFile.bytes!,
        filename: platformFile.name,
      );
    print('BEFORE SEND');
      Map<String, dynamic> response = await filesRepository.upload(file: multipartFile);
      print('FILE RESPONSE $response');
      return FileResponse.fromJson(response);
  }
}
