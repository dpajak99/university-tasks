import 'package:flutter/material.dart';

ColorScheme _kColorSchemeLight = ColorScheme.light(
  primary: const Color(0xff512DA8),
  primaryVariant: Colors.grey[300]!,
  secondary: const Color(0xff666666),
  onBackground: Colors.black,
  onPrimary: Colors.white,
);

TextTheme _buildLightTextTheme(TextTheme? base, String? lang) {
  const TextTheme textTheme = TextTheme();
  return textTheme.copyWith();
}

ThemeData buildLightTheme(String? lang) {
  final ThemeData baseLight = ThemeData.light();
  return baseLight.copyWith(
    textTheme: _buildLightTextTheme(baseLight.textTheme, lang),
    scaffoldBackgroundColor: Colors.white,
    backgroundColor: Colors.white,
    colorScheme: _kColorSchemeLight,
  );
}
