import 'package:flutter/cupertino.dart';
import 'package:json_annotation/json_annotation.dart';

part 'FileResponse.g.dart';

@JsonSerializable(explicitToJson: true)
@immutable
class FileResponse {
  final String id;
  final String name;
  final String extension;
  final int timestamp;
  final int size;

  const FileResponse({
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

  Map<String, dynamic> toJson() => {
    "id": id,
    "name": name,
    "extension": extension,
    "timestamp": timestamp,
  };

  @override
  String toString() {
    return 'FileResponse{id: $id, name: $name, extension: $extension, timestamp: $timestamp, size: $size}';
  }
}
