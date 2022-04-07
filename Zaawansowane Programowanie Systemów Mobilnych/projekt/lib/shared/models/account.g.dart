// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'account.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

Account _$AccountFromJson(Map<String, dynamic> json) => Account(
      id: json['id'] as String,
      email: json['email'] as String,
      firstName: json['firstName'] as String,
      lastName: json['lastName'] as String,
      token: AuthToken.fromJson(json['token'] as Map<String, dynamic>),
      roles: (json['roles'] as List<dynamic>)
          .map((e) => Role.fromJson(e as Map<String, dynamic>))
          .toList(),
      avatarId: json['avatarId'] as String?,
    );

Map<String, dynamic> _$AccountToJson(Account instance) => <String, dynamic>{
      'id': instance.id,
      'email': instance.email,
      'firstName': instance.firstName,
      'lastName': instance.lastName,
      'avatarId': instance.avatarId,
      'token': instance.token.toJson(),
      'roles': instance.roles.map((e) => e.toJson()).toList(),
    };
