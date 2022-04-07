import 'package:dio/dio.dart';
import 'package:projekt/shared/dio_base.dart'
if (dart.library.html) 'package:projekt/shared/dio_web.dart';

class DioConditional {

  Dio getDio(String uri) {
    return getDioConditional(uri);
  }
}