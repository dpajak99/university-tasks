import 'package:projekt/config/locator.dart';
import 'package:projekt/infra/repositories/drive/drive_repository.dart';
import 'package:projekt/shared/app_logger.dart';

class DriveService {
  final DriveRepository driveRepository = globalLocator<DriveRepository>();

  Future<List<int>> download({String path = ''}) async {
    try {
      List<int> response = await driveRepository.download(path: path);
      return response;
    } catch (e) {
      AppLogger().log(message: e.toString(), logLevel: LogLevel.error);
      rethrow;
    }
  }
}
