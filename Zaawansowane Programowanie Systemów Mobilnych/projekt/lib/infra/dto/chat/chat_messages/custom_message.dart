import 'package:json_annotation/json_annotation.dart';
import 'package:meta/meta.dart';
import 'package:projekt/infra/dto/auth/users/public_user_data.dart';
import 'package:projekt/infra/dto/chat/chat_message.dart';
import 'package:projekt/infra/dto/chat/chat_messages/partial_custom.dart';
import 'package:projekt/infra/dto/chat/preview_data.dart';

part 'custom_message.g.dart';

/// A class that represents custom message. Use [metadata] to store anything
/// you want.
@JsonSerializable(explicitToJson: true)
@immutable
class CustomMessage extends ChatMessage {
  /// Creates a custom message.
  const CustomMessage({
    required String id,
    required PublicUserData author,
    required Map<String, dynamic> metadata,
    int? createdAt,
    String? roomId,
    MessageStatus status = MessageStatus.sending,
    MessageType? type,
    int? updatedAt,
  }) : super(
          author,
          createdAt,
          id,
          metadata,
          roomId,
          status,
          type ?? MessageType.custom,
          updatedAt,
        );

  /// Creates a full custom message from a partial one.
  CustomMessage.fromPartial({
    required PublicUserData author,
    required String id,
    required PartialCustom partialCustom,
    int? createdAt,
    int? updatedAt,
    String? roomId,
    MessageStatus status = MessageStatus.sending,
  }) : super(
          author,
          createdAt,
          id,
          partialCustom.metadata,
          roomId,
          status,
          MessageType.custom,
          updatedAt,
        );

  /// Creates a custom message from a map (decoded JSON).
  factory CustomMessage.fromJson(Map<String, dynamic> json) {
    json['metadata'] ??= <String, dynamic>{};
    CustomMessage message = _$CustomMessageFromJson(json);
    return message;
  }

  /// Converts a custom message to the map representation,
  /// encodable to JSON.
  @override
  Map<String, dynamic> toJson() {
   Map<String, dynamic> finalJson = _$CustomMessageToJson(this);
   return finalJson;
  }

  /// Creates a copy of the custom message with an updated data.
  /// [metadata] with null value will nullify existing metadata, otherwise
  /// both metadatas will be merged into one Map, where keys from a passed
  /// metadata will overwite keys from the previous one.
  /// [previewData] is ignored for this message type.
  /// [remoteId] and [updatedAt] with null values will nullify existing value.
  /// [status] with null value will be overwritten by the previous status.
  /// [text] is ignored for this message type.
  /// [uri] is ignored for this message type.
  @override
  ChatMessage copyWith({
    Map<String, dynamic>? metadata,
    PreviewData? previewData,
    MessageStatus? status,
    String? text,
    int? updatedAt,
    String? imageId,
    String? fileId,
  }) {
    return CustomMessage(
      author: author,
      createdAt: createdAt,
      id: id,
      metadata: metadata ?? this.metadata,
      roomId: roomId,
      status: status ?? this.status,
      updatedAt: updatedAt,
    );
  }

  /// Equatable props
  @override
  List<Object?> get props => <Object?>[
        author,
        createdAt,
        id,
        metadata,
        roomId,
        status,
        updatedAt,
      ];
}
