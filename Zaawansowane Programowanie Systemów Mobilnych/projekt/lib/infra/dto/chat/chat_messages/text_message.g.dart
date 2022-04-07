// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'text_message.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

TextMessage _$TextMessageFromJson(Map<String, dynamic> json) => TextMessage(
      id: json['id'] as String,
      author: PublicUserData.fromJson(json['author'] as Map<String, dynamic>),
      text: json['text'] as String,
      metadata: json['metadata'] as Map<String, dynamic>? ?? {},
      createdAt: json['createdAt'] as int?,
      roomId: json['roomId'] as String?,
      status: $enumDecodeNullable(_$MessageStatusEnumMap, json['status']) ??
          MessageStatus.sending,
      type: $enumDecodeNullable(_$MessageTypeEnumMap, json['type']),
      updatedAt: json['updatedAt'] as int?,
    );

Map<String, dynamic> _$TextMessageToJson(TextMessage instance) =>
    <String, dynamic>{
      'id': instance.id,
      'author': instance.author.toJson(),
      'createdAt': instance.createdAt,
      'metadata': instance.metadata,
      'roomId': instance.roomId,
      'status': _$MessageStatusEnumMap[instance.status],
      'type': _$MessageTypeEnumMap[instance.type],
      'updatedAt': instance.updatedAt,
      'text': instance.text,
    };

const _$MessageStatusEnumMap = {
  MessageStatus.delivered: 'delivered',
  MessageStatus.error: 'error',
  MessageStatus.seen: 'seen',
  MessageStatus.sending: 'sending',
  MessageStatus.sent: 'sent',
};

const _$MessageTypeEnumMap = {
  MessageType.custom: 'custom',
  MessageType.file: 'file',
  MessageType.image: 'image',
  MessageType.text: 'text',
  MessageType.unsupported: 'unsupported',
};
