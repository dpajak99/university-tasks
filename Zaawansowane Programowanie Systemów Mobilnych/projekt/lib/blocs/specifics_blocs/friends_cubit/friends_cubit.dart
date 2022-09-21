import 'package:equatable/equatable.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:projekt/infra/dto/chat/count_messages_dto.dart';
import 'package:projekt/infra/services/chat/chat_service.dart';
import 'package:projekt/providers/account_provider.dart';

part 'friends_state.dart';

class FriendsCubit extends Cubit<FriendsState> {
  final ChatService chatService;
  final AccountProvider accountProvider;

  FriendsCubit({
    required this.chatService,
    required this.accountProvider,
  }) : super(FriendsInitial());

  Future<void> init() async {
    List<CountMessagesDto> result = await chatService.countMessages();
    emit(FriendsLoading());
    emit(FriendsLoaded(result: result));
  }
}
