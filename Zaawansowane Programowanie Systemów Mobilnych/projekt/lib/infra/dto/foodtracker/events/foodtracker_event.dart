class FoodtrackerEvent {
  final String? id;
  final String name;
  final String description;
  final DateTime dateStart;
  final DateTime dateEnd;
  final String? profileImageId;
  final String? backgroundImageId;

  FoodtrackerEvent({
    required this.name,
    required this.description,
    required this.dateStart,
    required this.dateEnd,
    this.id,
    this.profileImageId,
    this.backgroundImageId,
  });

  factory FoodtrackerEvent.fromJson(Map<String, dynamic> json) {
    return FoodtrackerEvent(
      id: json['id'] as String?,
      name: json['name'] as String,
      description: json['description'] as String,
      dateStart: DateTime.parse(json['date_start'] as String),
      dateEnd: DateTime.parse(json['date_end'] as String),
      profileImageId: json['profile_image'] as String,
      backgroundImageId: json['background_image'] as String,
    );
  }

  Map<String, dynamic> toJson() {
    return <String, dynamic>{
      'id': id,
      'name': name,
      'description': description,
      'dateStart': dateStart.toString(),
      'dateEnd': dateEnd.toString(),
      'profileImage': profileImageId,
      'backgroundImage': backgroundImageId,
    };
  }
}
