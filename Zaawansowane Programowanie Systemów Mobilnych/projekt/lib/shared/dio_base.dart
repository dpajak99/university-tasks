import 'package:dio/dio.dart';

Dio getDioConditional(String networkUrl) {
  return Dio(BaseOptions(baseUrl: networkUrl));
}