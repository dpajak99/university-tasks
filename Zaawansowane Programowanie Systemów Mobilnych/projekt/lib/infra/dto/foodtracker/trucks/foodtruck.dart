class FoodTruck {
  final String? id;
  final String name;
  final String description;
  final String? tags;

  FoodTruck({
    required this.id,
    required this.name,
    required this.description,
    required this.tags,
  });

  factory FoodTruck.fromJson(Map<String, dynamic> json) {
    return FoodTruck(
      id: json['id'] as String,
      name: json['name'] as String,
      description: json['description'] as String,
      tags: json['tags'] as String?,
    );
  }

  Map<String, dynamic> toJson() {
    return <String, dynamic>{
      'id': id,
      'name': name,
      'description': description,
      'tags': tags,
    };
  }

  @override
  String toString() {
    return 'FoodTruck{id: $id, name: $name, description: $description, tags: $tags}';
  }
}
