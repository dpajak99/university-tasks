import 'package:meta/meta.dart';
import 'package:projekt/infra/dto/auth/users/public_user_data.dart';
import 'package:projekt/infra/dto/chat/chat_message.dart';
import 'package:projekt/infra/dto/chat/chat_messages/partial_file.dart';
import 'package:projekt/infra/dto/chat/preview_data.dart';
import 'package:projekt/infra/dto/response/FileResponse.dart';
import 'package:projekt/shared/utils/enums.dart';

/// A class that represents file message.
@immutable
class FileMessage extends ChatMessage {
  /// Creates a file message.
  const FileMessage({
    required String id,
    required PublicUserData author,
    required Map<String, dynamic> metadata,
    int? createdAt,
    String? roomId,
    MessageStatus status = MessageStatus.sending,
    MessageType? type,
    int? updatedAt,
    required this.fileResponse,
  }) : super(
    author,
    createdAt,
    id,
    metadata,
    roomId,
    status,
    type ?? MessageType.file,
    updatedAt,
  );

  /// Creates a file message from a map (decoded JSON).
  factory FileMessage.fromJson(Map<String, dynamic> json) {
    FileResponse fileResponse = FileResponse.fromJson(json['file'] as Map<String, dynamic>);
    return FileMessage(
      id: json['id'] as String,
      author: PublicUserData.fromJson(json['author'] as Map<String, dynamic>),
      fileResponse: fileResponse,
      metadata: json['metadata'] as Map<String, dynamic>,
      updatedAt: json['updatedAt'] as int,
      createdAt: json['createdAt'] as int,
      roomId: json['roomId'] as String,
      type: enumFromString(MessageType.values, json['type'] as String),
      status: enumFromString(MessageStatus.values, json['status'] as String),
    );
  }

  /// Converts a file message to the map representation, encodable to JSON.
  @override
  Map<String, dynamic> toJson() {
    return <String, dynamic>{
      'id': id,
      'fileId': fileResponse.id,
      'authorId': author.id,
      'createdAt': createdAt,
      'updatedAt': updatedAt,
      'metadata': metadata,
      'roomId': roomId,
      'type': enumToString(type),
      'status': enumToString(status),
    };
  }

  /// Creates a copy of the file message with an updated data.
  /// [metadata] with null value will nullify existing metadata, otherwise
  /// both metadatas will be merged into one Map, where keys from a passed
  /// metadata will overwite keys from the previous one.
  /// [previewData] is ignored for this message type.
  /// [remoteId] and [updatedAt] with null values will nullify existing value.
  /// [status] and [imageId] with null values will be overwritten by previous values.
  /// [text] is ignored for this message type.
  @override
  ChatMessage copyWith({
    Map<String, dynamic>? metadata,
    MessageStatus? status,
    int? updatedAt,
    FileResponse? fileResponse,
  }) {
    return FileMessage(
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
  List<Object?> get props =>
      <Object?>[
        author,
        createdAt,
        id,
        metadata,
        roomId,
        status,
        updatedAt,
      ];

  /// Media type
  final FileResponse fileResponse;
}
