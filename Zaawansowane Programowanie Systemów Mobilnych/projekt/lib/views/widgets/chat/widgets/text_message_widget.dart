import 'package:flutter/material.dart';
import 'package:projekt/infra/dto/auth/users/public_user_data.dart';
import 'package:projekt/infra/dto/chat/chat_messages/text_message.dart';
import 'package:projekt/infra/dto/chat/preview_data.dart';
import 'package:projekt/views/widgets/chat/chat_theme.dart';
import 'package:projekt/views/widgets/chat/chat_utils.dart';
import 'package:projekt/views/widgets/chat/model/emoji_enlargement_behavior.dart';
import 'package:projekt/views/widgets/chat/widgets/inherited_chat_theme.dart';
import 'package:projekt/views/widgets/chat/widgets/inherited_user.dart';

/// A class that represents text message widget with optional link preview
class TextMessageWidget extends StatelessWidget {
  /// Creates a text message widget from a [TextMessage] class
  const TextMessageWidget({
    required this.emojiEnlargementBehavior,
    required this.hideBackgroundOnEmojiMessages,
    required this.usePreviewData,
    required this.showName,
    required this.message,
    this.onPreviewDataFetched,
    Key? key,
  }) : super(key: key);

  /// See [Message.emojiEnlargementBehavior]
  final EmojiEnlargementBehavior emojiEnlargementBehavior;

  /// See [Message.hideBackgroundOnEmojiMessages]
  final bool hideBackgroundOnEmojiMessages;

  /// [TextMessage]
  final TextMessage message;

  /// See [LinkPreview.onPreviewDataFetched]
  final void Function(TextMessage, PreviewData)?
  onPreviewDataFetched;

  /// Show user name for the received message. Useful for a group chat.
  final bool showName;

  /// Enables link (URL) preview
  final bool usePreviewData;

  void _onPreviewDataFetched(PreviewData previewData) {
    if (message.previewData == null) {
      onPreviewDataFetched?.call(message, previewData);
    }
  }

  Widget _linkPreview(
      PublicUserData user,
      double width,
      BuildContext context,
      ) {
    final TextStyle? bodyLinkTextStyle = user.id == message.author.id
        ? InheritedChatTheme.of(context).theme.sentMessageBodyLinkTextStyle
        : InheritedChatTheme.of(context).theme.receivedMessageBodyLinkTextStyle;
    final TextStyle bodyTextStyle = user.id == message.author.id
        ? InheritedChatTheme.of(context).theme.sentMessageBodyTextStyle
        : InheritedChatTheme.of(context).theme.receivedMessageBodyTextStyle;
    final TextStyle linkDescriptionTextStyle = user.id == message.author.id
        ? InheritedChatTheme.of(context)
        .theme
        .sentMessageLinkDescriptionTextStyle
        : InheritedChatTheme.of(context)
        .theme
        .receivedMessageLinkDescriptionTextStyle;
    final TextStyle linkTitleTextStyle = user.id == message.author.id
        ? InheritedChatTheme.of(context).theme.sentMessageLinkTitleTextStyle
        : InheritedChatTheme.of(context)
        .theme
        .receivedMessageLinkTitleTextStyle;

    final Color color = getUserAvatarNameColor(message.author,
        InheritedChatTheme.of(context).theme.userAvatarNameColors);
    final String name = getUserName(message.author);

    return const Text('TODO');
    // return LinkPreview(
    //   enableAnimation: true,
    //   header: showName ? name : null,
    //   headerStyle: InheritedChatTheme.of(context)
    //       .theme
    //       .userNameTextStyle
    //       .copyWith(color: color),
    //   linkStyle: bodyLinkTextStyle ?? bodyTextStyle,
    //   metadataTextStyle: linkDescriptionTextStyle,
    //   metadataTitleStyle: linkTitleTextStyle,
    //   onPreviewDataFetched: (types.PreviewData previewData) {
    //     return _onPreviewDataFetched(PreviewData.fromJson(previewData.toJson()));
    //   },
    //   padding: EdgeInsets.symmetric(
    //     horizontal:
    //     InheritedChatTheme.of(context).theme.messageInsetsHorizontal,
    //     vertical: InheritedChatTheme.of(context).theme.messageInsetsVertical,
    //   ),
    //   previewData: message.previewData != null ? types.PreviewData.fromJson(message.previewData!.toJson()) : null,
    //   text: message.text,
    //   textStyle: bodyTextStyle,
    //   width: width,
    // );
  }

  Widget _textWidgetBuilder(
      PublicUserData user,
      BuildContext context,
      bool enlargeEmojis,
      ) {
    final ChatTheme theme = InheritedChatTheme.of(context).theme;
    final Color color =
    getUserAvatarNameColor(message.author, theme.userAvatarNameColors);
    final String name = getUserName(message.author);

    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: <Widget>[
        if (showName)
          Padding(
            padding: const EdgeInsets.only(bottom: 6),
            child: Text(
              name,
              maxLines: 1,
              overflow: TextOverflow.ellipsis,
              style: theme.userNameTextStyle.copyWith(color: color),
            ),
          ),
        SelectableText(
          message.text,
          style: user.id == message.author.id
              ? enlargeEmojis
              ? theme.sentEmojiMessageTextStyle
              : theme.sentMessageBodyTextStyle
              : enlargeEmojis
              ? theme.receivedEmojiMessageTextStyle
              : theme.receivedMessageBodyTextStyle,
          textWidthBasis: TextWidthBasis.longestLine,
        ),
      ],
    );
  }

  @override
  Widget build(BuildContext context) {
    final bool _enlargeEmojis =
        emojiEnlargementBehavior != EmojiEnlargementBehavior.never &&
            isConsistsOfEmojis(emojiEnlargementBehavior, message);
    final ChatTheme _theme = InheritedChatTheme.of(context).theme;
    final PublicUserData _user = InheritedUser.of(context).user;
    final double _width = MediaQuery.of(context).size.width;

    // if (usePreviewData && onPreviewDataFetched != null) {
      // final RegExp urlRegexp = RegExp(regexLink, caseSensitive: false);
      // final Iterable<RegExpMatch> matches = urlRegexp.allMatches(message.text);
      //
      // if (matches.isNotEmpty) {
      //   return _linkPreview(_user, _width, context);
      // }
    // }

    return Container(
      margin: EdgeInsets.symmetric(
        horizontal: _enlargeEmojis && hideBackgroundOnEmojiMessages
            ? 0.0
            : _theme.messageInsetsHorizontal,
        vertical: _theme.messageInsetsVertical,
      ),
      child: _textWidgetBuilder(_user, context, _enlargeEmojis),
    );
  }
}