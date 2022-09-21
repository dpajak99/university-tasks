// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'public_user_data.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

PublicUserData _$PublicUserDataFromJson(Map<String, dynamic> json) =>
    PublicUserData(
      firstName: json['firstName'] as String,
      lastName: json['lastName'] as String,
      email: json['email'] as String,
      roles: (json['roles'] as List<dynamic>)
          .map((e) => Role.fromJson(e as Map<String, dynamic>))
          .toList(),
      id: json['id'] as String,
      avatar: json['avatar'] == null
          ? null
          : FileResponse.fromJson(json['avatar'] as Map<String, dynamic>),
    );

Map<String, dynamic> _$PublicUserDataToJson(PublicUserData instance) =>
    <String, dynamic>{
      'id': instance.id,
      'firstName': instance.firstName,
      'lastName': instance.lastName,
      'email': instance.email,
      'avatar': instance.avatar?.toJson(),
      'roles': instance.roles.map((e) => e.toJson()).toList(),
    };
