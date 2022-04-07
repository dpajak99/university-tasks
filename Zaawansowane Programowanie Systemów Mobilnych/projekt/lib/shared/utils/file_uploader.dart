import 'package:file_picker/file_picker.dart';
import 'package:projekt/config/locator.dart';
import 'package:projekt/infra/dto/response/FileResponse.dart';
import 'package:projekt/infra/services/drive/files_service.dart';

class FileUploader {
  static Future<FileResponse> upload(PlatformFile file) async {
    return await globalLocator<FilesService>().upload(file);
  }
}