// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'FileResponse.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

FileResponse _$FileResponseFromJson(Map<String, dynamic> json) => FileResponse(
      id: json['id'] as String,
      name: json['name'] as String,
      extension: json['extension'] as String,
      timestamp: json['timestamp'] as int,
      size: json['size'] as int? ?? 100,
    );

Map<String, dynamic> _$FileResponseToJson(FileResponse instance) =>
    <String, dynamic>{
      'id': instance.id,
      'name': instance.name,
      'extension': instance.extension,
      'timestamp': instance.timestamp,
      'size': instance.size,
    };
