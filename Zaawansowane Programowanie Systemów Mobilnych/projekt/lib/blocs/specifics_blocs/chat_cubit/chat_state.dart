part of 'chat_cubit.dart';

abstract class ChatState extends Equatable {
  const ChatState();

  @override
  List<Object> get props => <Object>[];
}

class ChatLoading extends ChatState {}