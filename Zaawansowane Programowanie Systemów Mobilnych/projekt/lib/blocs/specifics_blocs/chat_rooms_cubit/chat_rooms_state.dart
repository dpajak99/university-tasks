part of 'chat_rooms_cubit.dart';

abstract class ChatRoomsState extends Equatable {
  const ChatRoomsState();

  @override
  List<Object> get props => <Object>[];
}

class RoomsLoading extends ChatRoomsState {}

class RoomsLoaded extends ChatRoomsState {
  final List<ChatRoom> rooms;

  const RoomsLoaded({required this.rooms});
}