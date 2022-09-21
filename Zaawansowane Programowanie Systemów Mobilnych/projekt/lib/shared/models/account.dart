import 'package:equatable/equatable.dart';
import 'package:flutter/material.dart';
import 'package:json_annotation/json_annotation.dart';
import 'package:projekt/infra/dto/auth/login/response/role.dart';
import 'package:projekt/infra/dto/response/FileResponse.dart';
import 'package:projekt/shared/models/auth_token.dart';

part 'account.g.dart';

@JsonSerializable(explicitToJson: true)
@immutable
class Account extends Equatable {
  final String id;
  final String email;
  final String firstName;
  final String lastName;
  final FileResponse? avatar;
  final AuthToken token;
  final List<Role> roles;

  const Account({
    required this.id,
    required this.email,
    required this.firstName,
    required this.lastName,
    required this.token,
    required this.roles,
    this.avatar,
  });

  @override
  List<Object?> get props => <Object?>[id];

  factory Account.fromJson(Map<String, dynamic> json) {
    return _$AccountFromJson(json);
  }


  Map<String, dynamic> toJson() {
    Map<String, dynamic> publicUserDataJson = _$AccountToJson(this);
    return publicUserDataJson;
  }


  @override
  String toString() {
    return 'Account{email: $email, firstName: $firstName, lastName: $lastName, token: $token, roles: $roles}';
  }
}
