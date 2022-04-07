part of 'search_cubit.dart';

abstract class SearchState extends Equatable {
  const SearchState();

  @override
  List<Object> get props => [];
}

class SearchInitial extends SearchState {}

class SearchRefresh extends SearchState {}

class SearchLoading extends SearchState {}

class SearchFound extends SearchState {
  final List<PublicUserData> result;

  const SearchFound({required this.result});
}

class SearchFailure extends SearchState {
  final String error;

  const SearchFailure({required this.error});

  @override
  List<Object> get props => [error];
}
