import 'package:equatable/equatable.dart';
import 'package:meta/meta.dart';

/// A class that represents a date header between messages.
@immutable
class DateHeader extends Equatable {
  /// Creates a date header.
  const DateHeader({
    required this.text,
  });

  /// Equatable props
  @override
  List<Object> get props => <Object>[text];

  /// Text to show in a header
  final String text;
}
