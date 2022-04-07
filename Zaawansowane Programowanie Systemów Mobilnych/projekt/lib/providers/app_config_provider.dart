import 'package:flutter/material.dart';
import 'package:hive/hive.dart';
import 'package:projekt/config/theme/theme_light.dart';
import 'package:projekt/shared/app_logger.dart';

abstract class AppConfigProvider extends ChangeNotifier {
  String get locale;

  ThemeData get themeData;

  void initConfig();

  Future<void> updateLang(String? lang);

}

class AppConfigProviderImpl extends AppConfigProvider {
  AppConfigProviderImpl() {
    initConfig();
  }

  late Box<String> _prefs;
  late String _locale;

  @override
  String get locale => _locale;

  @override
  ThemeData get themeData => buildLightTheme(locale);

  @override
  void initConfig() {
    try {
      _prefs = Hive.box<String>('configuration');
      _locale = _prefs.get('language', defaultValue: 'en')!;
      notifyListeners();
    } on Exception catch (error) {
      AppLogger().log(message: error.toString(), logLevel: LogLevel.terribleFailure);
    }
  }

  @override
  Future<void> updateLang(String? lang) async {
    try {
      await _prefs.put('language', lang!);
      notifyListeners();
    } on Exception catch (error) {
      AppLogger().log(message: error.toString(), logLevel: LogLevel.error);
    }
  }
}
