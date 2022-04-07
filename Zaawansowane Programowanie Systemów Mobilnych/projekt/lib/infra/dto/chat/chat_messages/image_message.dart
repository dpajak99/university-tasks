import 'package:json_annotation/json_annotation.dart';
import 'package:meta/meta.dart';
import 'package:projekt/infra/dto/auth/users/public_user_data.dart';
import 'package:projekt/infra/dto/chat/chat_message.dart';
import 'package:projekt/infra/dto/chat/chat_messages/partial_image.dart';
import 'package:projekt/infra/dto/chat/preview_data.dart';
import 'package:projekt/infra/dto/response/FileResponse.dart';
import 'package:projekt/shared/api_manager.dart';
import 'package:projekt/shared/utils/enums.dart';

/// A class that represents image message.
class ImageMessage extends ChatMessage {
  /// Creates an image message.
  const ImageMessage({
    required String id,
    required PublicUserData author,
    required Map<String, dynamic> metadata,
    required this.fileResponse,
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
          type ?? MessageType.image,
          updatedAt,
        );

  /// Creates an image message from a map (decoded JSON).
  factory ImageMessage.fromJson(Map<String, dynamic> json) {
    String id = json['id'] as String;
    PublicUserData publicUserData = PublicUserData.fromJson(json['author'] as Map<String, dynamic>);
    FileResponse fileResponse = FileResponse.fromJson(json['image'] as Map<String, dynamic>);
    Map<String, dynamic> metadata = json['metadata'] as Map<String, dynamic>;
    int updatedAt = json['updatedAt'] as int;
    int createdAt = json['createdAt'] as int;
    String roomId = json['roomId'] as String;

    return ImageMessage(
      id: id,
      author: publicUserData,
      fileResponse: fileResponse,
      metadata: metadata,
      updatedAt: updatedAt,
      createdAt: createdAt,
      roomId: roomId,
      type: enumFromString(MessageType.values, json['type'] as String),
      status: enumFromString(MessageStatus.values, json['status'] as String),
    );
  }

  /// Converts an image message to the map representation, encodable to JSON.
  @override
  Map<String, dynamic> toJson() {
    return <String, dynamic>{
      'id': id,
      'imageId': fileResponse.id,
      'authorId': author.id,
      'createdAt': createdAt,
      'updatedAt': updatedAt,
      'metadata': metadata,
      'roomId': roomId,
      'type': enumToString(type),
      'status': enumToString(status),
    };
  }

  /// Creates a copy of the image message with an updated data
  /// [metadata] with null value will nullify existing metadata, otherwise
  /// both metadatas will be merged into one Map, where keys from a passed
  /// metadata will overwite keys from the previous one.
  /// [updatedAt] with null values will nullify existing value.
  /// [status] and [uri] with null values will be overwritten by previous values.
  /// [text] is ignored for this message type.
  @override
  ChatMessage copyWith({
    Map<String, dynamic>? metadata,
    MessageStatus? status,
    FileResponse? fileResponse,
    int? updatedAt,
  }) {
    return ImageMessage(
      author: author,
      createdAt: createdAt,
      id: id,
      metadata: metadata ?? this.metadata,
      roomId: roomId,
      status: status ?? this.status,
      updatedAt: updatedAt,
      fileResponse: fileResponse ?? this.fileResponse,
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

  final FileResponse fileResponse;

  String get url => '${ApiManager.networkUrl}/drive/files/${fileResponse.id}';
}
