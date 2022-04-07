import 'package:dio/browser_imp.dart';
import 'package:dio/dio.dart';


Dio getDioConditional(String networkUrl) {
  return DioForBrowser(BaseOptions(baseUrl: networkUrl));
}