import 'package:flutter/material.dart';
import 'package:projekt/infra/dto/auth/users/public_user_data.dart';
import 'package:projekt/infra/dto/chat/chat_message.dart';
import 'package:projekt/infra/dto/chat/chat_messages/custom_message.dart';
import 'package:projekt/infra/dto/chat/chat_messages/file_message.dart';
import 'package:projekt/infra/dto/chat/chat_messages/image_message.dart';
import 'package:projekt/infra/dto/chat/chat_messages/text_message.dart';
import 'package:projekt/infra/dto/chat/preview_data.dart';
import 'package:projekt/views/widgets/chat/chat_utils.dart';
import 'package:projekt/views/widgets/chat/model/emoji_enlargement_behavior.dart';
import 'package:projekt/views/widgets/chat/widgets/file_message_widget.dart';
import 'package:projekt/views/widgets/chat/widgets/image_message_widget.dart';
import 'package:projekt/views/widgets/chat/widgets/inherited_chat_theme.dart';
import 'package:projekt/views/widgets/chat/widgets/inherited_user.dart';
import 'package:projekt/views/widgets/chat/widgets/text_message_widget.dart';

/// Base widget for all message types in the chat. Renders bubbles around
/// messages and status. Sets maximum width for a message for
/// a nice look on larger screens.
class MessageWidget extends StatelessWidget {
  /// Creates a particular message from any message type
  const MessageWidget({
    required this.roundBorder,
    required this.showAvatar,
    required this.showName,
    required this.showStatus,
    required this.showUserAvatars,
    required this.emojiEnlargementBehavior,
    required this.hideBackgroundOnEmojiMessages,
    required this.message,
    required this.messageWidth,
    required this.usePreviewData,
    this.bubbleBuilder,
    this.customMessageBuilder,
    this.fileMessageBuilder,
    this.imageMessageBuilder,
    this.onAvatarTap,
    this.onMessageDoubleTap,
    this.onMessageLongPress,
    this.onMessageStatusLongPress,
    this.onMessageStatusTap,
    this.onMessageTap,
    this.onPreviewDataFetched,
    this.textMessageBuilder,
    Key? key,
  }) : super(key: key);

  /// Customize the default bubble using this function. `child` is a content
  /// you should render inside your bubble, `message` is a current message
  /// (contains `author` inside) and `nextMessageInGroup` allows you to see
  /// if the message is a part of a group (messages are grouped when written
  /// in quick succession by the same author)
  final Widget Function(
      Widget child, {
      required ChatMessage message,
      required bool nextMessageInGroup,
      })? bubbleBuilder;

  /// Build a custom message inside predefined bubble
  final Widget Function(CustomMessage, {required int messageWidth})?
  customMessageBuilder;

  /// Controls the enlargement behavior of the emojis in the
  /// [types.TextMessage].
  /// Defaults to [EmojiEnlargementBehavior.multi].
  final EmojiEnlargementBehavior emojiEnlargementBehavior;

  /// Build a file message inside predefined bubble
  final Widget Function(FileMessage, {required int messageWidth})?
  fileMessageBuilder;

  /// Hide background for messages containing only emojis.
  final bool hideBackgroundOnEmojiMessages;

  /// Build an image message inside predefined bubble
  final Widget Function(ImageMessage, {required int messageWidth})?
  imageMessageBuilder;

  /// Any message type
  final ChatMessage message;

  /// Maximum message width
  final int messageWidth;

  // Called when uses taps on an avatar
  final void Function(PublicUserData)? onAvatarTap;

  /// Called when user double taps on any message
  final void Function(BuildContext context, ChatMessage)? onMessageDoubleTap;

  /// Called when user makes a long press on any message
  final void Function(BuildContext context, ChatMessage)? onMessageLongPress;

  /// Called when user makes a long press on status icon in any message
  final void Function(BuildContext context, ChatMessage)?
  onMessageStatusLongPress;

  /// Called when user taps on status icon in any message
  final void Function(BuildContext context, ChatMessage)? onMessageStatusTap;

  /// Called when user taps on any message
  final void Function(BuildContext context, ChatMessage)? onMessageTap;

  /// See [TextMessage.onPreviewDataFetched]
  final void Function(TextMessage, PreviewData)? onPreviewDataFetched;

  /// Rounds border of the message to visually group messages together.
  final bool roundBorder;

  /// Show user avatar for the received message. Useful for a group chat.
  final bool showAvatar;

  /// See [TextMessage.showName]
  final bool showName;

  /// Show message's status
  final bool showStatus;

  /// Show user avatars for received messages. Useful for a group chat.
  final bool showUserAvatars;

  /// Build a text message inside predefined bubble.
  final Widget Function(
      TextMessage, {
      required int messageWidth,
      required bool showName,
      })? textMessageBuilder;

  /// See [TextMessage.usePreviewData]
  final bool usePreviewData;

  Widget _avatarBuilder(BuildContext context) {
    final Color color = getUserAvatarNameColor(
      message.author,
      InheritedChatTheme.of(context).theme.userAvatarNameColors,
    );
    final bool hasImage = message.author.getAvatarUrl() != null;
    final String initials = getUserInitials(message.author);

    return showAvatar
        ? Container(
      margin: const EdgeInsets.only(right: 8),
      child: GestureDetector(
        onTap: () => onAvatarTap?.call(message.author),
        child: CircleAvatar(
          backgroundColor: hasImage
              ? InheritedChatTheme.of(context)
              .theme
              .userAvatarImageBackgroundColor
              : color,
          backgroundImage:
          hasImage ? NetworkImage(message.author.getAvatarUrl()) : null,
          radius: 16,
          child: !hasImage
              ? Text(
            initials,
            style: InheritedChatTheme.of(context)
                .theme
                .userAvatarTextStyle,
          )
              : null,
        ),
      ),
    )
        : const SizedBox(width: 40);
  }

  Widget _bubbleBuilder({
    required BuildContext context,
    required BorderRadius borderRadius,
    required bool currentUserIsAuthor,
    bool enlargeEmojis = false,
  }) {
    return bubbleBuilder != null
        ? bubbleBuilder!(
      _messageBuilder(),
      message: message,
      nextMessageInGroup: roundBorder,
    )
        : enlargeEmojis && hideBackgroundOnEmojiMessages
        ? _messageBuilder()
        : Container(
      decoration: BoxDecoration(
        borderRadius: borderRadius,
        color: !currentUserIsAuthor ||
            message.type == MessageType.image
            ? InheritedChatTheme.of(context).theme.secondaryColor
            : InheritedChatTheme.of(context).theme.primaryColor,
      ),
      child: ClipRRect(
        borderRadius: borderRadius,
        child: _messageBuilder(),
      ),
    );
  }

  Widget _messageBuilder() {
    switch (message.type) {
      case MessageType.custom:
        final CustomMessage customMessage = message as CustomMessage;
        return customMessageBuilder != null
            ? customMessageBuilder!(customMessage, messageWidth: messageWidth)
            : const SizedBox();
      case MessageType.file:
        final FileMessage fileMessage = message as FileMessage;
        return fileMessageBuilder != null
            ? fileMessageBuilder!(fileMessage, messageWidth: messageWidth)
            : FileMessageWidget(message: fileMessage);
      case MessageType.image:
        final ImageMessage imageMessage = message as ImageMessage;
        return imageMessageBuilder != null
            ? imageMessageBuilder!(imageMessage, messageWidth: messageWidth)
            : ImageMessageWidget(message: imageMessage, messageWidth: messageWidth);
      case MessageType.text:
        final TextMessage textMessage = message as TextMessage;
        return textMessageBuilder != null
            ? textMessageBuilder!(
          textMessage,
          messageWidth: messageWidth,
          showName: showName,
        )
            : TextMessageWidget(
          emojiEnlargementBehavior: emojiEnlargementBehavior,
          hideBackgroundOnEmojiMessages: hideBackgroundOnEmojiMessages,
          message: textMessage,
          onPreviewDataFetched: onPreviewDataFetched,
          showName: showName,
          usePreviewData: usePreviewData,
        );
      default:
        return const SizedBox();
    }
  }

  Widget _statusBuilder(BuildContext context) {
    switch (message.status) {
      case MessageStatus.delivered:
      case MessageStatus.sent:
        return InheritedChatTheme.of(context).theme.deliveredIcon != null
            ? InheritedChatTheme.of(context).theme.deliveredIcon!
            : Image.asset(
          'assets/chat/icon-delivered.png',
          color: InheritedChatTheme.of(context).theme.primaryColor,
        );
      case MessageStatus.error:
        return InheritedChatTheme.of(context).theme.errorIcon != null
            ? InheritedChatTheme.of(context).theme.errorIcon!
            : Image.asset(
          'assets/chat/icon-error.png',
          color: InheritedChatTheme.of(context).theme.errorColor,
        );
      case MessageStatus.seen:
        return InheritedChatTheme.of(context).theme.seenIcon != null
            ? InheritedChatTheme.of(context).theme.seenIcon!
            : Image.asset(
          'assets/chat/icon-seen.png',
          color: InheritedChatTheme.of(context).theme.primaryColor,
        );
      case MessageStatus.sending:
        return InheritedChatTheme.of(context).theme.sendingIcon != null
            ? InheritedChatTheme.of(context).theme.sendingIcon!
            : Center(
          child: SizedBox(
            height: 10,
            width: 10,
            child: CircularProgressIndicator(
              backgroundColor: Colors.transparent,
              strokeWidth: 1.5,
              valueColor: AlwaysStoppedAnimation<Color>(
                InheritedChatTheme.of(context).theme.primaryColor,
              ),
            ),
          ),
        );
      default:
        return const SizedBox();
    }
  }

  @override
  Widget build(BuildContext context) {
    final PublicUserData _user = InheritedUser.of(context).user;
    final bool _currentUserIsAuthor = _user.id == message.author.id;
    final bool _enlargeEmojis =
        emojiEnlargementBehavior != EmojiEnlargementBehavior.never &&
            message is TextMessage &&
            isConsistsOfEmojis(
                emojiEnlargementBehavior, message as TextMessage);
    final double _messageBorderRadius =
        InheritedChatTheme.of(context).theme.messageBorderRadius;
    final BorderRadius _borderRadius = BorderRadius.only(
      bottomLeft: Radius.circular(
        _currentUserIsAuthor || roundBorder ? _messageBorderRadius : 0,
      ),
      bottomRight: Radius.circular(_currentUserIsAuthor
          ? roundBorder
          ? _messageBorderRadius
          : 0
          : _messageBorderRadius),
      topLeft: Radius.circular(_messageBorderRadius),
      topRight: Radius.circular(_messageBorderRadius),
    );

    return Container(
      alignment:
      _currentUserIsAuthor ? Alignment.centerRight : Alignment.centerLeft,
      margin: const EdgeInsets.only(
        bottom: 4,
        left: 20,
      ),
      child: Row(
        crossAxisAlignment: CrossAxisAlignment.end,
        mainAxisSize: MainAxisSize.min,
        children: <Widget>[
          if (!_currentUserIsAuthor && showUserAvatars) _avatarBuilder(context),
          ConstrainedBox(
            constraints: BoxConstraints(
              maxWidth: messageWidth.toDouble(),
            ),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.end,
              children: <Widget>[
                GestureDetector(
                  onDoubleTap: () => onMessageDoubleTap?.call(context, message),
                  onLongPress: () => onMessageLongPress?.call(context, message),
                  onTap: () => onMessageTap?.call(context, message),
                  child: _bubbleBuilder(
                    context: context,
                    borderRadius: _borderRadius,
                    currentUserIsAuthor: _currentUserIsAuthor,
                    enlargeEmojis: _enlargeEmojis,
                  ),
                ),
              ],
            ),
          ),
          if (_currentUserIsAuthor)
            Padding(
              padding: InheritedChatTheme.of(context).theme.statusIconPadding,
              child: showStatus
                  ? GestureDetector(
                onLongPress: () =>
                    onMessageStatusLongPress?.call(context, message),
                onTap: () => onMessageStatusTap?.call(context, message),
                child: _statusBuilder(context),
              )
                  : null,
            ),
        ],
      ),
    );
  }
}