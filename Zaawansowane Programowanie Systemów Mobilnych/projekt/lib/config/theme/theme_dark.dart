import 'package:flutter/material.dart';

ColorScheme _kColorSchemeDark = ColorScheme.dark(
  primary: const Color(0xffFCB433),
  primaryVariant: Colors.grey[300]!,
  secondary: const Color(0xffbbbbbb),
  onBackground: Colors.black,
  background: const Color(0xff2E2E2E),
  onPrimary: Colors.white,
);

TextTheme _buildDarkTextTheme(TextTheme? base, String? lang) {
  const TextTheme textTheme = TextTheme();
  return textTheme.copyWith();
}

ThemeData buildDarkTheme(String? lang) {
  final ThemeData baseDark = ThemeData.dark();
  return baseDark.copyWith(
    textTheme: _buildDarkTextTheme(baseDark.textTheme, lang),
    colorScheme: _kColorSchemeDark,
  );
}
