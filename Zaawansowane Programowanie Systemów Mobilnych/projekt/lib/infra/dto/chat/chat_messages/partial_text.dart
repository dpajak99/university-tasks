import 'package:json_annotation/json_annotation.dart';
import 'package:meta/meta.dart';
import 'package:projekt/infra/dto/chat/preview_data.dart';

part 'partial_text.g.dart';

/// A class that represents partial text message.
@JsonSerializable(explicitToJson: true)
@immutable
class PartialText {
  /// Additional custom metadata or attributes related to the message
  final Map<String, dynamic> metadata;

  /// See [PreviewData]
  final PreviewData? previewData;

  /// User's message
  final String text;

  /// Creates a partial text message with all variables text can have.
  /// Use [TextMessage] to create a full message.
  /// You can use [TextMessage.fromPartial] constructor to create a full
  /// message from a partial one.
  const PartialText({
    required this.text,
    required this.metadata,
    this.previewData,
  });

  /// Creates a partial text message from a map (decoded JSON).
  factory PartialText.fromJson(Map<String, dynamic> json) =>
      _$PartialTextFromJson(json);

  /// Converts a partial text message to the map representation, encodable to JSON.
  Map<String, dynamic> toJson() => _$PartialTextToJson(this);
}
