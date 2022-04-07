import 'package:equatable/equatable.dart';

class PublicCompanyData extends Equatable {
  final int id;
  final String name;
  final String? website;
  final String? phone;
  final String? email;
  final String? timezone;
  final String? lang;
  final String? avatar;
  final String? businessCard;

  const PublicCompanyData({
    required this.id,
    required this.name,
    this.website,
    this.phone,
    this.email,
    this.timezone,
    this.lang,
    this.avatar,
    this.businessCard,
  });

  factory PublicCompanyData.fromJson(Map<String, dynamic> json) {
    return PublicCompanyData(
      id: json['id'] as int,
      name: json['name'] as String,
      website: json['website'] as String?,
      phone: json['phone'] as String?,
      email: json['email'] as String?,
      timezone: json['timezone'] as String?,
      lang: json['lang'] as String?,
      avatar: json['avatar'] as String?,
      businessCard: json['businessCard'] as String?,
    );
  }

  Map<String, dynamic> toJson() {
    return <String, dynamic>{
      'id': id,
      'name': name,
      'website': website,
      'phone': phone,
      'email': email,
      'timezone': timezone,
      'lang': lang,
      'avatar': avatar,
      'businessCard': businessCard,
    };
  }

  @override
  String toString() {
    return 'PublicCompanyData{id: $id, name: $name, website: $website, phone: $phone, email: $email, timezone: $timezone, lang: $lang, avatar: $avatar, businessCard: $businessCard}';
  }

  @override
  List<Object?> get props => <Object?>[id];
}
