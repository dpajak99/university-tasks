import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:projekt/blocs/specifics_blocs/friends_cubit/friends_cubit.dart';
import 'package:projekt/infra/dto/chat/count_messages_dto.dart';
import 'package:projekt/views/widgets/generic/pretty_scroll_view.dart';
import 'package:projekt/views/widgets/profile_avatar.dart';

class FriendsPage extends StatefulWidget {
  const FriendsPage({Key? key}) : super(key: key);

  @override
  State<StatefulWidget> createState() => _FriendsPage();
}

class _FriendsPage extends State<FriendsPage> {
  @override
  void initState() {
    BlocProvider.of<FriendsCubit>(context).init();
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return PrettyScrollView(
      title: 'Friends',
      actions: const [],
      body: Padding(
        padding: const EdgeInsets.symmetric(horizontal: 15),
        child: BlocBuilder<FriendsCubit, FriendsState>(
          builder: (BuildContext context, FriendsState state) {
            if (state is FriendsLoading) {
              return const Center(
                child: CircularProgressIndicator(),
              );
            }
            if (state is FriendsLoaded) {
              return Column(
                children: [
                  const SizedBox(height: 10),
                  SizedBox(
                    width: double.infinity,
                    child: TextFormField(
                      decoration: InputDecoration(
                        prefixIcon: const Icon(Icons.search),
                        hintText: 'Search',
                        fillColor: Colors.grey[200],
                        filled: true,
                        border: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(30),
                          borderSide: BorderSide.none,
                        ),
                        enabledBorder: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(30),
                          borderSide: BorderSide.none,
                        ),
                        focusedBorder: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(30),
                          borderSide: BorderSide.none,
                        ),
                      ),
                    ),
                  ),
                  const SizedBox(height: 10),
                  _BestFriendTile(countMessagesDto: state.result.first),
                  const SizedBox(height: 10),
                  GridView(
                    shrinkWrap: true,
                    physics: const NeverScrollableScrollPhysics(),
                    gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
                      crossAxisCount: 2,
                      mainAxisSpacing: 10,
                      crossAxisSpacing: 10,
                      childAspectRatio: 1.3,
                    ),
                    children: state.result.map((e) => _FriendTile(countMessagesDto: e)).toList(),
                  )
                ],
              );
            }
            return const Center(
              child: Text('sorry bro, u have no friends 🤡'),
            );
          },
        ),
      ),
    );
  }
}

class _BestFriendTile extends StatelessWidget {
  final CountMessagesDto countMessagesDto;

  const _BestFriendTile({
    required this.countMessagesDto,
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      width: double.infinity,
      decoration: BoxDecoration(
        color: const Color(0xFF7B4A00).withOpacity(0.2),
        borderRadius: BorderRadius.circular(15),
      ),
      padding: const EdgeInsets.all(20),
      child: Row(
        children: [
          Column(
            children: [
              ProfileAvatar(
                size: 80,
                avatarId: countMessagesDto.user.avatar?.id,
              ),
              const SizedBox(
                height: 10,
              ),
              Text(
                countMessagesDto.user.firstName,
                style: const TextStyle(
                  color: Color(0xFF7B4A00),
                  fontSize: 20,
                  fontWeight: FontWeight.bold,
                ),
              ),
            ],
          ),
          Expanded(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              crossAxisAlignment: CrossAxisAlignment.center,
              children: [
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  crossAxisAlignment: CrossAxisAlignment.center,
                  children: const [
                    Icon(
                      Icons.star,
                      color: Color(0xFF7B4A00),
                      size: 30,
                    ),
                    SizedBox(width: 5),
                    Text(
                      'Best friend',
                      style: TextStyle(
                        color: Color(0xFF7B4A00),
                        fontSize: 20,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                  ],
                ),
                const SizedBox(height: 10),
                Text(
                  '${countMessagesDto.messages} messages',
                  style: const TextStyle(
                    color: Colors.black,
                    fontSize: 20,
                    fontWeight: FontWeight.bold,
                  ),
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}

class _FriendTile extends StatelessWidget {
  final CountMessagesDto countMessagesDto;

  const _FriendTile({
    required this.countMessagesDto,
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      width: double.infinity,
      decoration: BoxDecoration(
        color: Theme.of(context).primaryColor.withOpacity(0.2),
        borderRadius: BorderRadius.circular(15),
      ),
      padding: const EdgeInsets.symmetric(horizontal: 20, vertical: 5),
      child: Column(
        children: [
          ProfileAvatar(
            size: 55,
            avatarId: countMessagesDto.user.avatar?.id,
          ),
          const SizedBox(
            height: 10,
          ),
          Text(
            countMessagesDto.user.firstName,
            style: TextStyle(
              color: Theme.of(context).primaryColor,
              fontSize: 20,
              fontWeight: FontWeight.bold,
            ),
          ),
          Row(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              const Icon(Icons.message, color: Colors.black, size: 20,),
              const SizedBox(width: 5),
              Text(
                '${countMessagesDto.messages}',
                style: const TextStyle(
                  color: Colors.black,
                  fontSize: 20,
                  fontWeight: FontWeight.bold,
                ),
              ),

            ],
          ),
        ],
      ),
    );
  }
}
