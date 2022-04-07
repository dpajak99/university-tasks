import 'package:projekt/config/locator.dart';
import 'package:projekt/infra/dto/auth/company/public_company_data.dart';
import 'package:projekt/infra/repositories/auth/company_repository.dart';
import 'package:projekt/shared/app_logger.dart';

class CompanyService {
  final CompanyRepository companyRepository = globalLocator<CompanyRepository>();

  Future<List<PublicCompanyData>> getAll() async {
    try {
      List<dynamic> response = await companyRepository.getAllCompanies();
      List<PublicCompanyData> companiesList = response.map((dynamic e) => PublicCompanyData.fromJson(e as Map<String, dynamic>)).toList();
      return companiesList;
    } catch(e) {
      AppLogger().log(message: e.toString(), logLevel: LogLevel.error);
      return List<PublicCompanyData>.empty();
    }
  }
}
