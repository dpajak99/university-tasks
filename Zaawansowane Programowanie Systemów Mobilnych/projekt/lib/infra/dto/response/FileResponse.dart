class FileResponse {
  final String id;
  final String name;
  final String extension;
  final int timestamp;
  final int size;

  FileResponse({
    required this.id,
    required this.name,
    required this.extension,
    required this.timestamp,
    this.size = 100,
  });

  factory FileResponse.fromJson(Map<String, dynamic> json) => FileResponse(
    id: json["id"],
    name: json["name"],
    extension: json["extension"],
    timestamp: json["timestamp"],
  );
}
