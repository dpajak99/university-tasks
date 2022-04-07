// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'partial_image.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

PartialImage _$PartialImageFromJson(Map<String, dynamic> json) => PartialImage(
      name: json['name'] as String,
      size: json['size'] as num,
      uri: json['uri'] as String,
      metadata: json['metadata'] as Map<String, dynamic>,
      height: (json['height'] as num?)?.toDouble(),
      width: (json['width'] as num?)?.toDouble(),
    );

Map<String, dynamic> _$PartialImageToJson(PartialImage instance) =>
    <String, dynamic>{
      'height': instance.height,
      'metadata': instance.metadata,
      'name': instance.name,
      'size': instance.size,
      'uri': instance.uri,
      'width': instance.width,
    };
