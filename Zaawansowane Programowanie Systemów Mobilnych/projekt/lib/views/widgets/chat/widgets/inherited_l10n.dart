import 'package:flutter/widgets.dart';
import 'package:projekt/views/widgets/chat/model/chat_l10n.dart';

/// Used to make provided [ChatL10n] class available through the whole package
class InheritedL10n extends InheritedWidget {
  /// Creates [InheritedWidget] from a provided [ChatL10n] class
  const InheritedL10n({
    required this.l10n,
    required Widget child,
    Key? key,
  }) : super(key: key, child: child);

  /// Represents localized copy
  final ChatL10n l10n;

  static InheritedL10n of(BuildContext context) {
    return context.dependOnInheritedWidgetOfExactType<InheritedL10n>()!;
  }

  @override
  bool updateShouldNotify(InheritedL10n oldWidget) => l10n.hashCode != oldWidget.l10n.hashCode;
}
