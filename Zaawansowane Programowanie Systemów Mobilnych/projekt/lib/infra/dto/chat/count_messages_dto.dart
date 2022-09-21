import 'package:projekt/infra/dto/auth/users/public_user_data.dart';

class CountMessagesDto {
  final PublicUserData user;
  final int messages;

  CountMessagesDto({
    required this.user,
    required this.messages,
  });

  factory CountMessagesDto.fromJson(Map<String, dynamic> json) => CountMessagesDto(
    user: PublicUserData.fromJson(json['user']),
    messages: json['messages'],
  );
}
