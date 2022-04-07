import 'package:dio/dio.dart';
import 'package:projekt/config/locator.dart';
import 'package:projekt/providers/account_provider.dart';
import 'package:projekt/shared/dio_conditional.dart';
import 'package:projekt/shared/models/account.dart';

class ApiManager {
  static const String networkUrl = 'http://192.168.43.45:8082';

  static Map<String, String> getDefaultHeaders() {
    Map<String, String> headers = <String, String>{};
    if (globalLocator<AccountProvider>().isLoggedIn) {
      Account account = globalLocator<AccountProvider>().account!;
      headers.putIfAbsent('Authorization', () => '${account.token.token} ${account.token.type}');
    }
    return headers;
  }

  Future<Response<T>> get<T>({
    required String path,
    Map<String, dynamic>? queryParameters,
    CancelToken? cancelToken,
    ProgressCallback? onReceiveProgress,
  }) async {
    print('${networkUrl}$path $queryParameters - with headers ${getDefaultHeaders()}');
    try {
      final Dio server = DioConditional().getDio(networkUrl);
      return await server.get<T>(
        path,
        queryParameters: queryParameters,
        options: Options(
          headers: getDefaultHeaders(),
        ),
        cancelToken: cancelToken,
        onReceiveProgress: onReceiveProgress,
      );
    } on DioError {
      rethrow;
    }
  }

  Future<Response<T>> put<T>({
    required String path,
    required dynamic data,
    Map<String, dynamic>? queryParameters,
    Options? options,
    CancelToken? cancelToken,
    ProgressCallback? onSendProgress,
    ProgressCallback? onReceiveProgress,
  }) async {
    print('${networkUrl}$path $queryParameters');
    try {
      final Dio server = DioConditional().getDio(networkUrl);
      return await server.put<T>(
        path,
        data: data,
        queryParameters: queryParameters,
        options: Options(
          headers: getDefaultHeaders(),
        ),
        cancelToken: cancelToken,
        onReceiveProgress: onReceiveProgress,
      );
    } on DioError {
      rethrow;
    }
  }

  Future<Response<T>> post<T>({
    required String path,
    required dynamic body,
    Map<String, dynamic>? queryParameters,
    CancelToken? cancelToken,
    ProgressCallback? onReceiveProgress,
  }) async {
    try {
      final Dio server = DioConditional().getDio(networkUrl);
      print('${networkUrl}$path | $queryParameters | $body');
      return await server.post<T>(
        path,
        data: body,
        queryParameters: queryParameters,
        options: Options(
          headers: getDefaultHeaders(),
        ),
        cancelToken: cancelToken,
        onReceiveProgress: onReceiveProgress,
      );
    } on DioError {
      rethrow;
    }
  }

  Future<List<int>> download({required String path}) async {
    final Dio server = DioConditional().getDio(networkUrl);
    Response<List<int>> response = await server.get<List<int>>(
      path,
      options: Options(responseType: ResponseType.bytes), // set responseType to `bytes`
    );
    return response.data!;
  }
}
