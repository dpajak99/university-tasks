import 'package:flutter/material.dart';
import 'package:hive_flutter/hive_flutter.dart';
import 'package:projekt/config/hive.dart';
import 'package:projekt/config/locator.dart';
import 'package:projekt/providers/app_config_provider.dart';
import 'package:projekt/providers/app_list_providers.dart';
import 'package:projekt/shared/guards/auth_guard.dart';
import 'package:projekt/shared/router/router.gr.dart';
import 'package:provider/provider.dart';
import 'package:flutter_localizations/flutter_localizations.dart';

Future<void> main() async {
  await Hive.initFlutter();
  await initLocator();
  await initHive();

  runApp(
    // ignore: always_specify_types
    ChangeNotifierProvider.value(
      value: globalLocator<AppConfigProvider>(),
      child: MultiProvider(
        providers: appListProviders,
        child: const CoreApp(),
      ),
    ),
  );
}

class CoreApp extends StatefulWidget {
  const CoreApp({Key? key}) : super(key: key);

  @override
  State<StatefulWidget> createState() => _CoreApp();
}

class _CoreApp extends State<CoreApp> {
  final AppRouter appRouter = AppRouter(authGuard: AuthGuard());

  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Consumer<AppConfigProvider>(
      builder: (_, AppConfigProvider value, Widget? child) {
        return MaterialApp.router(
          routeInformationParser: appRouter.defaultRouteParser(),
          routerDelegate: appRouter.delegate(),
          // showPerformanceOverlay: true,
          debugShowCheckedModeBanner: false,
          locale: Locale(
            globalLocator<AppConfigProvider>().locale,
            globalLocator<AppConfigProvider>().locale.toUpperCase(),
          ),
          builder: (_, Widget? routerWidget) {
            return Scaffold( body: SafeArea(child: routerWidget as Widget));
          },
          localizationsDelegates: const <LocalizationsDelegate<dynamic>>[
            // S.delegate,
            GlobalMaterialLocalizations.delegate,
            GlobalWidgetsLocalizations.delegate,
          ],
        );
      },
    );
  }
}