import 'package:flutter/material.dart';
import 'package:json_annotation/json_annotation.dart';

part 'auth_token.g.dart';

@JsonSerializable(explicitToJson: true)
@immutable
class AuthToken {
  final String token;
  final String type;

  const AuthToken({
    required this.token,
    required this.type,
  });

  factory AuthToken.fromJson(Map<String, dynamic> json) {
    return _$AuthTokenFromJson(json);
  }


  Map<String, dynamic> toJson() {
    Map<String, dynamic> publicUserDataJson = _$AuthTokenToJson(this);
    return publicUserDataJson;
  }

}
