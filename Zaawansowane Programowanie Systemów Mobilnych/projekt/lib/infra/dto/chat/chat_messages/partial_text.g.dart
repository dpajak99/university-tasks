// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'partial_text.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

PartialText _$PartialTextFromJson(Map<String, dynamic> json) => PartialText(
      text: json['text'] as String,
      metadata: json['metadata'] as Map<String, dynamic>,
      previewData: json['previewData'] == null
          ? null
          : PreviewData.fromJson(json['previewData'] as Map<String, dynamic>),
    );

Map<String, dynamic> _$PartialTextToJson(PartialText instance) =>
    <String, dynamic>{
      'metadata': instance.metadata,
      'previewData': instance.previewData?.toJson(),
      'text': instance.text,
    };
