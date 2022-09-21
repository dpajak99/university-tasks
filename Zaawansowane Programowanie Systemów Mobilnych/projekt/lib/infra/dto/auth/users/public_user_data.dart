import 'package:equatable/equatable.dart';
import 'package:flutter/material.dart';
import 'package:json_annotation/json_annotation.dart';
import 'package:projekt/infra/dto/auth/login/response/role.dart';
import 'package:projekt/infra/dto/response/FileResponse.dart';
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
  final FileResponse? avatar;
  final List<Role> roles;

  const PublicUserData({
    required this.firstName,
    required this.lastName,
    required this.email,
    required this.roles,
    required this.id,
    this.avatar,
  });

  factory PublicUserData.fromAccount(Account account) {
    return PublicUserData(
      id: account.id,
      firstName: account.firstName,
      lastName: account.lastName,
      email: account.email,
      roles: account.roles,
      avatar: account.avatar,
    );
  }

  String getAvatarUrl([double? size]) {
    if( avatar == null ) return 'https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460__340.png';
    return '${ApiManager.networkUrl}/drive/uploads/${avatar!.id}${size != null ? '?height=${size.toInt()}' : ''}';
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
    FileResponse? avatar,
    List<Role>? roles,
  }) {
    return PublicUserData(
      id: id ?? this.id,
      firstName: firstName ?? this.firstName,
      lastName: lastName ?? this.lastName,
      email: email ?? this.email,
      avatar: avatar ?? this.avatar,
      roles: roles ?? this.roles,
    );
  }


  @override
  String toString() {
    return 'PublicUserData{id: $id, firstName: $firstName, lastName: $lastName, email: $email, avatar: $avatar, roles: $roles}';
  }

  @override
  List<Object?> get props => <Object?>[id];
}
