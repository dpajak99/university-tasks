import 'package:get_it/get_it.dart';
import 'package:projekt/infra/repositories/auth/auth_repository.dart';
import 'package:projekt/infra/repositories/auth/users_repository.dart';
import 'package:projekt/infra/repositories/chat/chat_repository.dart';
import 'package:projekt/infra/repositories/drive/files_repository.dart';
import 'package:projekt/infra/services/auth/auth_service.dart';
import 'package:projekt/infra/services/auth/users_service.dart';
import 'package:projekt/infra/services/chat/chat_service.dart';
import 'package:projekt/infra/services/drive/files_service.dart';
import 'package:projekt/infra/sockets/chat_socket.dart';
import 'package:projekt/providers/account_provider.dart';
import 'package:projekt/providers/app_config_provider.dart';

final GetIt globalLocator = GetIt.I;

Future<void> initLocator() async {
  globalLocator
    ..registerLazySingleton<AppConfigProvider>(() => AppConfigProviderImpl())
    ..registerLazySingleton<AccountProvider>(() => AccountProvider())
    //
    ..registerFactory<AuthRepository>(() => RemoteAuthRepository())
    ..registerFactory<AuthService>(() => AuthService())
    //
    ..registerFactory<FilesRepository>(() => RemoteFilesRepository())
    ..registerFactory<FilesService>(() => FilesService())
    //
    ..registerFactory<UsersRepository>(() => RemoteUsersRepository())
    ..registerFactory<UsersService>(() => UsersService())
    //
    ..registerFactory<ChatRepository>(() => RemoteChatRepository())
    ..registerFactory<ChatSocket>(() => ChatSocket())
    ..registerFactory<ChatService>(() => ChatService());
}
