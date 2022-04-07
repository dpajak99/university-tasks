import 'package:equatable/equatable.dart';
import 'package:flutter/material.dart';
import 'package:json_annotation/json_annotation.dart';
import 'package:projekt/config/locator.dart';
import 'package:projekt/infra/dto/auth/users/public_user_data.dart';
import 'package:projekt/infra/dto/chat/chat_messages/custom_message.dart';
import 'package:projekt/infra/dto/chat/chat_messages/file_message.dart';
import 'package:projekt/infra/dto/chat/chat_messages/image_message.dart';
import 'package:projekt/infra/dto/chat/chat_messages/text_message.dart';
import 'package:projekt/infra/dto/chat/chat_messages/unsupported_message.dart';
import 'package:projekt/infra/dto/chat/preview_data.dart';
import 'package:projekt/providers/account_provider.dart';


/// All possible message types.
enum MessageType { custom, file, image, text, unsupported }

/// All possible statuses message can have.
enum MessageStatus { delivered, error, seen, sending, sent }

/// An abstract class that contains all variables and methods
/// every message will have.
@immutable
abstract class ChatMessage extends Equatable {
  /// Converts a particular message to the map representation, encodable to JSON.
  Map<String, dynamic> toJson();

  /// Unique ID of the message
  final String id;

  /// User who sent this message
  final PublicUserData author;

  /// Created message timestamp, in ms
  final int? createdAt;

  /// Additional custom metadata or attributes related to the message
  @JsonKey(defaultValue: <String, dynamic>{})
  final Map<String, dynamic> metadata;

  /// ID of the room where this message is sent
  final String? roomId;

  /// Message [MessageStatus]
  final MessageStatus status;

  /// [MessageType]
  final MessageType type;

  /// Updated message timestamp, in ms
  final int? updatedAt;

  const ChatMessage(
      this.author,
      this.createdAt,
      this.id,
      this.metadata,
      this.roomId,
      this.status,
      this.type,
      this.updatedAt,
      );

  /// Creates a particular message from a map (decoded JSON).
  /// Type is determined by the `type` field.
  factory ChatMessage.fromJson(Map<String, dynamic> json) {
    final String type = json['type'] as String;
    switch (type) {
      case 'custom':
        return CustomMessage.fromJson(json);
      case 'file':
        return FileMessage.fromJson(json);
      case 'image':
        return ImageMessage.fromJson(json);
      case 'text':
        return TextMessage.fromJson(json);
      default:
        return UnsupportedMessage.fromJson(json);
    }
  }

  /// Creates a copy of the message with an updated data
  /// [metadata] with null value will nullify existing metadata, otherwise
  /// both metadatas will be merged into one Map, where keys from a passed
  /// metadata will overwite keys from the previous one.
  /// [previewData] will be only set for the text message type.
  /// [status] with null value will be overwritten by the previous status.
  /// [text] will be only set for the text message type. Null value will be
  /// overwritten by the previous text (can't be empty).
  /// [updatedAt] with null values will nullify existing value.
  /// [uri] will be only set for file and image message types. Null value
  /// will be overwritten by the previous value (uri can't be empty).
  ChatMessage copyWith({
    Map<String, dynamic>? metadata,
    MessageStatus? status,
    int? updatedAt,
  });

  bool get isMe {
    return author.id == globalLocator<AccountProvider>().account!.id;
  }

  String? getLastMessageText() {
    switch (type) {
      case MessageType.custom:
        return (this as CustomMessage).id;
      case MessageType.file:
        return 'Wysłano plik';
      case MessageType.image:
        return 'Wysłano zdjęcie';
      case MessageType.text:
        return (this as TextMessage).text;
      default:
        return 'Unsupported message';
    }
  }

}