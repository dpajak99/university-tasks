import 'package:flutter/material.dart';
import 'package:projekt/infra/dto/auth/users/public_user_data.dart';
import 'package:projekt/infra/dto/chat/chat_messages/file_message.dart';
import 'package:projekt/views/widgets/chat/chat_utils.dart';
import 'package:projekt/views/widgets/chat/widgets/inherited_chat_theme.dart';
import 'package:projekt/views/widgets/chat/widgets/inherited_l10n.dart';
import 'package:projekt/views/widgets/chat/widgets/inherited_user.dart';

/// A class that represents file message widget
class FileMessageWidget extends StatelessWidget {
  /// Creates a file message widget based on a [types.FileMessage]
  const FileMessageWidget({
    required this.message,
    Key? key,
  }) : super(key: key);

  /// [FileMessage]
  final FileMessage message;

  @override
  Widget build(BuildContext context) {
    final PublicUserData _user = InheritedUser.of(context).user;
    final Color _color = _user.id == message.author.id
        ? InheritedChatTheme.of(context).theme.sentMessageDocumentIconColor
        : InheritedChatTheme.of(context).theme.receivedMessageDocumentIconColor;

    return Semantics(
      label: InheritedL10n.of(context).l10n.fileButtonAccessibilityLabel,
      child: Container(
        padding: EdgeInsets.fromLTRB(
          InheritedChatTheme.of(context).theme.messageInsetsVertical,
          InheritedChatTheme.of(context).theme.messageInsetsVertical,
          InheritedChatTheme.of(context).theme.messageInsetsHorizontal,
          InheritedChatTheme.of(context).theme.messageInsetsVertical,
        ),
        child: Row(
          mainAxisSize: MainAxisSize.min,
          children: <Widget>[
            Container(
              decoration: BoxDecoration(
                color: _color.withOpacity(0.2),
                borderRadius: BorderRadius.circular(21),
              ),
              height: 42,
              width: 42,
              child: InheritedChatTheme.of(context).theme.documentIcon != null
                  ? InheritedChatTheme.of(context).theme.documentIcon!
                  : Image.asset(
                'assets/chat/icon-document.png',
                color: _color,
              ),
            ),
            Flexible(
              child: Container(
                margin: const EdgeInsets.only(
                  left: 16,
                ),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: <Widget>[
                    Text(
                      message.fileResponse.name,
                      style: _user.id == message.author.id
                          ? InheritedChatTheme.of(context)
                          .theme
                          .sentMessageBodyTextStyle
                          : InheritedChatTheme.of(context)
                          .theme
                          .receivedMessageBodyTextStyle,
                      textWidthBasis: TextWidthBasis.longestLine,
                    ),
                    Container(
                      margin: const EdgeInsets.only(
                        top: 4,
                      ),
                      child: Text(
                        formatBytes(message.fileResponse.size.truncate()),
                        style: _user.id == message.author.id
                            ? InheritedChatTheme.of(context)
                            .theme
                            .sentMessageCaptionTextStyle
                            : InheritedChatTheme.of(context)
                            .theme
                            .receivedMessageCaptionTextStyle,
                      ),
                    ),
                  ],
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}