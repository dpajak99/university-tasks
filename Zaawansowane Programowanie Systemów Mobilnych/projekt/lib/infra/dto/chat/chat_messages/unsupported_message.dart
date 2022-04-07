import 'package:json_annotation/json_annotation.dart';
import 'package:meta/meta.dart';
import 'package:projekt/infra/dto/auth/users/public_user_data.dart';
import 'package:projekt/infra/dto/chat/chat_message.dart';
import 'package:projekt/infra/dto/chat/preview_data.dart';

part 'unsupported_message.g.dart';

/// A class that represents unsupported message. Used for backwards
/// compatibility. If chat's end user doesn't update to a new version
/// where new message types are being sent, some of them will result
/// to unsupported.
@JsonSerializable(explicitToJson: true)
@immutable
class UnsupportedMessage extends ChatMessage {
  /// Creates an unsupported message.
  const UnsupportedMessage({
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
          type ?? MessageType.unsupported,
          updatedAt,
        );

  /// Creates an unsupported message from a map (decoded JSON).
  factory UnsupportedMessage.fromJson(Map<String, dynamic> json) =>
      _$UnsupportedMessageFromJson(json);

  /// Converts an unsupported message to the map representation,
  /// encodable to JSON.
  @override
  Map<String, dynamic> toJson() {
    return _$UnsupportedMessageToJson(this);
  }

  /// Creates a copy of the unsupported message with an updated data.
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
    return UnsupportedMessage(
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
