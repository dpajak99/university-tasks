/// The abstract class for a conditional import feature.
import 'package:projekt/views/widgets/chat/conditional/conditional_stub.dart'
if (dart.library.io) 'package:projekt/views/widgets/chat/conditional/io_conditional.dart'
if (dart.library.html) 'package:projekt/views/widgets/chat/conditional/browser_conditional.dart';

import 'package:flutter/material.dart';

abstract class Conditional {
  /// Creates a new platform appropriate conditional.
  ///
  /// Creates an `IOConditional` if `dart:io` is available and a `BrowserConditional` if
  /// `dart:html` is available, otherwise it will throw an unsupported error.
  factory Conditional() => createConditional();

  /// Returns an appropriate platform ImageProvider for specified URI
  ImageProvider getProvider(String uri);
}