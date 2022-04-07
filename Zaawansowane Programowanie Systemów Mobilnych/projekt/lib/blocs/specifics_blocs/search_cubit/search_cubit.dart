import 'package:equatable/equatable.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:projekt/infra/dto/auth/users/public_user_data.dart';
import 'package:projekt/infra/services/auth/users_service.dart';
import 'package:projekt/providers/account_provider.dart';

part 'search_state.dart';

class SearchCubit extends Cubit<SearchState> {
  final UsersService usersService;
  final AccountProvider accountProvider;

  SearchCubit({
    required this.usersService,
    required this.accountProvider,
  }) : super(SearchInitial());

  Future<void> refresh() async {
    await search('');
  }

  Future<void> search(String searchPattern) async {
    if (searchPattern == '') {
      emit(SearchInitial());
      return;
    }
    List<PublicUserData> result = await usersService.getAll(searchPattern);
    emit(SearchLoading());
    emit(SearchFound(result: result.where((user) => user.id != accountProvider.account?.id).toList()));
  }
}
