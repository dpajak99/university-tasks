import 'package:json_annotation/json_annotation.dart';
import 'package:meta/meta.dart';
import 'package:projekt/infra/dto/auth/users/public_user_data.dart';
import 'package:projekt/infra/dto/chat/chat_message.dart';
import 'package:projekt/infra/dto/chat/chat_messages/partial_text.dart';
import 'package:projekt/infra/dto/chat/preview_data.dart';

part 'text_message.g.dart';

/// A class that represents text message.
@JsonSerializable(explicitToJson: true)
@immutable
class TextMessage extends ChatMessage {

  /// See [PreviewData]
  @JsonKey(ignore: true)
  final PreviewData? previewData;

  /// User's message
  final String text;

  /// Creates a text message.
  const TextMessage({
    required String id,
    required PublicUserData author,
    required this.text,
    required Map<String, dynamic> metadata,
    int? createdAt,
    String? roomId,
    MessageStatus status = MessageStatus.sending,
    MessageType? type,
    int? updatedAt,
    this.previewData,
  }) : super(
          author,
          createdAt,
          id,
          metadata,
          roomId,
          status,
          type ?? MessageType.text,
          updatedAt,
        );

  /// Creates a full text message from a partial one.
  TextMessage.fromPartial({
    required String id,
    required PublicUserData author,
    required PartialText partialText,
    int? createdAt,
    String? roomId,
    MessageStatus status = MessageStatus.sending,
    int? updatedAt,
  })  : previewData = partialText.previewData,
        text = partialText.text,
        super(
          author,
          createdAt,
          id,
          partialText.metadata,
          roomId,
          status,
          MessageType.text,
          updatedAt,
        );

  /// Creates a text message from a map (decoded JSON).
  factory TextMessage.fromJson(Map<String, dynamic> json) {
    return _$TextMessageFromJson(json);
  }

  /// Converts a text message to the map representation, encodable to JSON.
  @override
  Map<String, dynamic> toJson() {
    return _$TextMessageToJson(this);
  }

  /// Creates a copy of the text message with an updated data
  /// [metadata] with null value will nullify existing metadata, otherwise
  /// both metadatas will be merged into one Map, where keys from a passed
  /// metadata will overwite keys from the previous one.
  /// [remoteId] and [updatedAt] with null values will nullify existing value.
  /// [status] and [text] with null values will be overwritten by previous values.
  /// [uri] is ignored for this message type.
  @override
  ChatMessage copyWith({
    Map<String, dynamic>? metadata,
    PreviewData? previewData,
    String? remoteId,
    MessageStatus? status,
    String? text,
    int? updatedAt,
    String? imageId,
    String? fileId,
  }) {
    return TextMessage(
      author: author,
      createdAt: createdAt,
      id: id,
      metadata: metadata ?? this.metadata,
      previewData: previewData,
      roomId: roomId,
      status: status ?? this.status,
      text: text ?? this.text,
      updatedAt: updatedAt ?? DateTime.now().millisecondsSinceEpoch,
    );
  }

  /// Equatable props
  @override
  List<Object?> get props => <Object?>[
        author,
        createdAt,
        id,
        metadata,
        previewData,
        roomId,
        status,
        text,
        updatedAt,
      ];

}
