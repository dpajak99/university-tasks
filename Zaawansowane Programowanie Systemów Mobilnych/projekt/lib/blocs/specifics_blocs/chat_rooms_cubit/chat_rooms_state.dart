part of 'chat_rooms_cubit.dart';

abstract class ChatRoomsState extends Equatable {
  const ChatRoomsState();

  @override
  List<Object> get props => <Object>[];
}

class RoomsLoading extends ChatRoomsState {}

class RoomsLoaded extends ChatRoomsState {
  final List<ChatRoom> _rooms;

  List<ChatRoom> get rooms {
    _rooms.sort((ChatRoom a, ChatRoom b) => b.updatedAt.compareTo(a.updatedAt));
    return _rooms;
  }

  @override
  List<Object> get props => <Object>[_rooms.hashCode];

  const RoomsLoaded({required List<ChatRoom> rooms}) : _rooms = rooms;

}