import 'package:equatable/equatable.dart';
import 'package:flutter/material.dart';
import 'package:json_annotation/json_annotation.dart';
import 'package:projekt/infra/dto/auth/login/response/role.dart';
import 'package:projekt/shared/api_manager.dart';
import 'package:projekt/shared/models/account.dart';

part 'public_user_data.g.dart';

@JsonSerializable(explicitToJson: true)
@immutable
class PublicUserData extends Equatable {
  final String id;
  final String firstName;
  final String lastName;
  final String email;
  final String? avatarId;
  final List<Role> roles;

  const PublicUserData({
    required this.firstName,
    required this.lastName,
    required this.email,
    required this.roles,
    required this.id,
    this.avatarId,
  });

  factory PublicUserData.fromAccount(Account account) {
    return PublicUserData(
      id: account.id,
      firstName: account.firstName,
      lastName: account.lastName,
      email: account.email,
      roles: account.roles,
      avatarId: account.avatarId,
    );
  }

  String? getAvatarUrl() {
    if( avatarId == null ) return null;
    return '${ApiManager.networkUrl}/drive/files/$avatarId';
  }

  factory PublicUserData.fromJson(Map<String, dynamic> json) {
    json['roles'] = json['roles'] ?? List<Map<String, dynamic>>.empty();
    PublicUserData publicUserData = _$PublicUserDataFromJson(json);
    return publicUserData;
  }

  Map<String, dynamic> toJson() {
    Map<String, dynamic> publicUserDataJson = _$PublicUserDataToJson(this);
    return publicUserDataJson;
  }

  PublicUserData copyWith({
    String? id,
    String? firstName,
    String? lastName,
    String? email,
    String? avatarId,
    List<Role>? roles,
  }) {
    return PublicUserData(
      id: id ?? this.id,
      firstName: firstName ?? this.firstName,
      lastName: lastName ?? this.lastName,
      email: email ?? this.email,
      avatarId: avatarId ?? this.avatarId,
      roles: roles ?? this.roles,
    );
  }


  @override
  String toString() {
    return 'PublicUserData{id: $id, firstName: $firstName, lastName: $lastName, email: $email, avatarId: $avatarId, roles: $roles}';
  }

  @override
  List<Object?> get props => <Object?>[id];
}
