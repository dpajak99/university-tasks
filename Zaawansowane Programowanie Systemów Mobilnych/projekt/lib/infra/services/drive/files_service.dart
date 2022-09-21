import 'dart:io';

import 'package:dio/dio.dart';
import 'package:file_picker/file_picker.dart';
import 'package:projekt/config/locator.dart';
import 'package:projekt/infra/dto/response/FileResponse.dart';
import 'package:projekt/infra/repositories/drive/files_repository.dart';

class FilesService {
  final FilesRepository filesRepository = globalLocator<FilesRepository>();

  Future<FileResponse> upload(PlatformFile platformFile) async {
    if(platformFile.bytes == null) {
      return const FileResponse(id: '', name: 'none', extension: 'none', timestamp: 0);
    } else {
      MultipartFile multipartFile;
      multipartFile = MultipartFile.fromBytes(
        platformFile.bytes!,
        filename: platformFile.name,
      );

      Map<String, dynamic> response = await filesRepository.upload(file: multipartFile);
      return FileResponse.fromJson(response);
    }
  }
}
