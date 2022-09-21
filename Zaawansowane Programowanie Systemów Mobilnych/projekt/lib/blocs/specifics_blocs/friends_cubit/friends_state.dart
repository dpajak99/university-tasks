part of 'friends_cubit.dart';

abstract class FriendsState extends Equatable {
  const FriendsState();

  @override
  List<Object> get props => [];
}

class FriendsInitial extends FriendsState {}

class FriendsRefresh extends FriendsState {}

class FriendsLoading extends FriendsState {}

class FriendsLoaded extends FriendsState {
  final List<CountMessagesDto> _result;

  List<CountMessagesDto> get result {
    _result.sort((a, b) => b.messages.compareTo(a.messages));
    return _result;
  }

  const FriendsLoaded({required List<CountMessagesDto> result}) : _result = result;
}

class FriendsFailure extends FriendsState {
  final String error;

  const FriendsFailure({required this.error});

  @override
  List<Object> get props => [error];
}
