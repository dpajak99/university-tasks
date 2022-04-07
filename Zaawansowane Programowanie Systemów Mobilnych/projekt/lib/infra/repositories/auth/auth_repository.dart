import 'package:dio/dio.dart';
import 'package:projekt/infra/dto/auth/login/request/login_request.dart';
import 'package:projekt/infra/dto/auth/register/request/register_req.dart';
import 'package:projekt/infra/dto/auth/register/response/register_resp.dart';
import 'package:projekt/shared/api_manager.dart';

abstract class AuthRepository {
  Future<Map<String, dynamic>> login(LoginRequest loginRequest);

  Future<RegisterResponse> register(RegisterRequest registerRequest);
}

class RemoteAuthRepository extends AuthRepository {
  final ApiManager api = ApiManager();

  @override
  Future<Map<String, dynamic>> login(LoginRequest loginRequest) async {
    final Response<Map<String, dynamic>> response = await api.post(
      path: '/auth/login',
      body: loginRequest.toJson(),
    );
    return response.data!;
  }

  @override
  Future<RegisterResponse> register(RegisterRequest registerRequest) async {
    final Response<String> response = await api.post(
      path: '/auth/register',
      body: registerRequest.toJson(),
    );
    return RegisterResponse(userId: response.data!);
  }

}
