import 'package:equatable/equatable.dart';
import 'package:flutter/material.dart';
import 'package:json_annotation/json_annotation.dart';

part 'role.g.dart';

@JsonSerializable(explicitToJson: true)
@immutable
class Role extends Equatable {
  final int id;
  final String name;

  const Role({
    required this.id,
    required this.name,
  });

  factory Role.fromJson(Map<String, dynamic> json) => _$RoleFromJson(json);
  Map<String, dynamic> toJson() => _$RoleToJson(this);

  @override
  List<Object?> get props => <Object?>[id];

  @override
  String toString() {
    return 'Role{id: $id, name: $name}';
  }
}
