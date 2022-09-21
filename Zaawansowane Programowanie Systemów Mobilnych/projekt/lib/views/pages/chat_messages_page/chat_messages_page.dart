import 'package:file_picker/file_picker.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:projekt/blocs/specifics_blocs/chat_cubit/chat_cubit.dart';
import 'package:projekt/blocs/specifics_blocs/chat_messages_cubit/chat_messages_cubit.dart';
import 'package:projekt/config/locator.dart';
import 'package:projekt/infra/dto/auth/users/public_user_data.dart';
import 'package:projekt/infra/dto/chat/chat_message.dart';
import 'package:projekt/infra/dto/chat/chat_messages/file_message.dart';
import 'package:projekt/infra/dto/chat/chat_messages/image_message.dart';
import 'package:projekt/infra/dto/chat/chat_messages/partial_text.dart';
import 'package:projekt/infra/dto/chat/chat_messages/text_message.dart';
import 'package:projekt/infra/dto/chat/chat_room.dart';
import 'package:projekt/infra/dto/chat/preview_data.dart';
import 'package:projekt/infra/dto/response/FileResponse.dart';
import 'package:projekt/providers/account_provider.dart';
import 'package:projekt/shared/utils/file_uploader.dart';
import 'package:projekt/views/widgets/center_load_spinner.dart';
import 'package:projekt/views/widgets/chat/chat_app_bar.dart';
import 'package:projekt/views/widgets/chat/chat_avatar.dart';
import 'package:projekt/views/widgets/chat/model/send_button_visibility_mode.dart';
import 'package:projekt/views/widgets/chat/widgets/chat.dart';
import 'package:uuid/uuid.dart';

class ChatMessagesPage extends StatefulWidget {
  final ChatRoom chatRoom;

  const ChatMessagesPage({
    required this.chatRoom,
    Key? key,
  }) : super(key: key);

  @override
  State<StatefulWidget> createState() => _ChatMessagesPage();
}

class _ChatMessagesPage extends State<ChatMessagesPage> {
  @override
  void didChangeDependencies() {
    BlocProvider.of<ChatCubit>(context).updateChatRoom(widget.chatRoom);
    super.didChangeDependencies();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: ChatAppBar(
        chatRoom: widget.chatRoom,
      ),
      endDrawer: _ChatDrawer(
        chatRoom: widget.chatRoom,
      ),
      body: BlocBuilder<ChatMessagesCubit, ChatMessagesState>(
        builder: (BuildContext context, ChatMessagesState state) {
          if (state is MessagesLoaded) {
            return Chat(
              messages: state.messages,
              user: PublicUserData.fromAccount(globalLocator<AccountProvider>().account!),
              showUserAvatars: true,
              sendButtonVisibilityMode: SendButtonVisibilityMode.always,
              onAttachmentPressed: () => _handleAtachmentPressed(state.chatRoom),
              chatRoom: state.chatRoom,
              onSendPressed: (PartialText partialText) => _handleSendPressed(partialText, state.chatRoom),
              onMessageTap: _handleMessageTap,
              onPreviewDataFetched: _handlePreviewDataFetched,
            );
          }
          return const CenterLoadSpinner();
        },
      ),
    );
  }

  Future<void> _handleMessageTap(BuildContext context, ChatMessage message) async {
    if (message is FileMessage) {}
  }

  void _handlePreviewDataFetched(TextMessage message, PreviewData previewData) {
    // BlocProvider.of<ChatMessagesCubit>(context).receiveMessage(message.copyWith(previewData: previewData));
  }

  void _handleAtachmentPressed(ChatRoom chatRoom) {
    showModalBottomSheet<void>(
      context: context,
      builder: (BuildContext context) {
        return SafeArea(
          child: SizedBox(
            height: 144,
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.stretch,
              children: <Widget>[
                TextButton(
                  onPressed: () {
                    Navigator.pop(context);
                    _handleImageSelection(chatRoom);
                  },
                  child: const Align(
                    alignment: Alignment.centerLeft,
                    child: Text('Photo'),
                  ),
                ),
                TextButton(
                  onPressed: () {
                    Navigator.pop(context);
                    _handleFileSelection(chatRoom);
                  },
                  child: const Align(
                    alignment: Alignment.centerLeft,
                    child: Text('File'),
                  ),
                ),
                TextButton(
                  onPressed: () => Navigator.pop(context),
                  child: const Align(
                    alignment: Alignment.centerLeft,
                    child: Text('Cancel'),
                  ),
                ),
              ],
            ),
          ),
        );
      },
    );
  }

  Future<void> _handleFileSelection(ChatRoom chatRoom) async {
    final FilePickerResult? result = await FilePicker.platform.pickFiles(
      type: FileType.any,
    );

    if (result != null && result.files.isNotEmpty) {
      final FileResponse fileResponse = await FileUploader.upload(result.files.first);

      final FileMessage message = FileMessage(
        id: const Uuid().v4(),
        author: PublicUserData.fromAccount(globalLocator<AccountProvider>().account!),
        createdAt: DateTime.now().millisecondsSinceEpoch,
        fileResponse: fileResponse,
        roomId: chatRoom.id,
        metadata: const <String, dynamic>{},
      );

      BlocProvider.of<ChatCubit>(context).sendMessage(message);
    }
  }

  Future<void> _handleImageSelection(ChatRoom chatRoom) async {
    FilePickerResult? result = await FilePicker.platform.pickFiles(
      type: FileType.image,
    );

    if (result != null) {
      print('BEFORE UPLOAD');
      final FileResponse fileResponse = await FileUploader.upload(result.files.first);
      print('BEFORE SENDING');
      final ImageMessage message = ImageMessage(
        id: const Uuid().v4(),
        author: PublicUserData.fromAccount(globalLocator<AccountProvider>().account!),
        createdAt: DateTime.now().millisecondsSinceEpoch,
        roomId: chatRoom.id,
        fileResponse: fileResponse,
        metadata: const <String, dynamic>{},
      );
      print('SENDING $message');
      BlocProvider.of<ChatCubit>(context).sendMessage(message);
    }
  }

  void _handleSendPressed(PartialText partialText, ChatRoom chatRoom) {
    TextMessage message = TextMessage(
      id: const Uuid().v4(),
      createdAt: DateTime.now().millisecondsSinceEpoch,
      roomId: chatRoom.id,
      type: MessageType.text,
      author: PublicUserData.fromAccount(globalLocator<AccountProvider>().account!),
      text: partialText.text,
      metadata: const <String, dynamic>{},
    );
    BlocProvider.of<ChatCubit>(context).sendMessage(message);
  }
}

class _ChatDrawer extends StatelessWidget {
  final ChatRoom chatRoom;

  const _ChatDrawer({
    required this.chatRoom,
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      width: MediaQuery.of(context).size.width * 0.90,
      child: Drawer(
        child: Column(
          children: [
            const SizedBox(height: 25),
            ChatAvatar(
              chatRoom: chatRoom,
              size: 100,
            ),
            const SizedBox(height: 15),
            Text(chatRoom.getName()),
            const SizedBox(height: 15),
            ListTile(
              onTap: () {
              },
              title: const Text('zmień nazwe chatu'),
              leading: const Icon(Icons.edit),
            ),
            ListTile(
              onTap: () {
              },
              title: const Text('zmień zdjęcie'),
              leading: const Icon(Icons.photo),
            ),
            ListTile(
              onTap: () {
              },
              title: const Text('wyszukaj w konwersacji'),
              leading: const Icon(Icons.search),
            ),
            ListTile(
              onTap: () {
              },
              title: const Text('edytuj nicki'),
              leading: const Icon(Icons.person),
            ),
            const Divider(),
            ListTile(
              onTap: () {
              },
              title: const Text('multimedia'),
              leading: const Icon(Icons.perm_media_outlined),
            ),
            ListTile(
              onTap: () {
              },
              title: const Text('pliki'),
              leading: const Icon(Icons.file_copy_outlined),
            ),
          ],
        ),
      ),
    );
  }
}
