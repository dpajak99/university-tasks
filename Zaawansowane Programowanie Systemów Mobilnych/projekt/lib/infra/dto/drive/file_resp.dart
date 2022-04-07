import 'package:projekt/shared/utils/file_type_utils.dart';

class FileResp {
  final String path;
  final bool isFile;
  final String name;
  final int lastModified;
  final bool isDirectory;

  FileResp({
    required this.path,
    required this.isFile,
    required this.name,
    required this.lastModified,
    required this.isDirectory,
  });

  factory FileResp.fromJson(Map<String, dynamic> json) {
    return FileResp(
      path: json['path'] as String,
      isFile: json['isFile'] as bool,
      name: json['name'] as String,
      lastModified: json['lastModified'] as int,
      isDirectory: json['isDirectory'] as bool,
    );
  }

  String get extension {
    return name.split('.').last;
  }

  bool get isImage {
    return FileTypeUtils.isImage(extension);
  }

  bool get isPdf {
    return extension == 'pdf';
  }

  bool get isSvg {
    return extension == 'svg';
  }

  bool get isRawText {
    return FileTypeUtils.isRawText(extension);
  }

  @override
  String toString() {
    return 'FileResp{path: $path, isFile: $isFile, name: $name, lastModified: $lastModified, isDirectory: $isDirectory}';
  }
}
