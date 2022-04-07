import 'package:dio/dio.dart';
import 'package:projekt/shared/api_manager.dart';

abstract class CompanyRepository {
  Future<List<dynamic>> getAllCompanies();
}

class RemoteCompanyRepository extends CompanyRepository {
  final ApiManager api = ApiManager();

  @override
  Future<List<dynamic>> getAllCompanies() async {
    final Response<List<dynamic>> response = await api.get(
      path: '/auth/company',
    );
    return response.data!;
  }
}
