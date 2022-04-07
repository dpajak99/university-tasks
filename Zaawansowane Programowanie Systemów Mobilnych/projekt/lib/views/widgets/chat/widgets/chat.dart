import 'dart:math';

import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:photo_view/photo_view_gallery.dart';
import 'package:projekt/infra/dto/auth/users/public_user_data.dart';
import 'package:projekt/infra/dto/chat/chat_message.dart';
import 'package:projekt/infra/dto/chat/chat_messages/custom_message.dart';
import 'package:projekt/infra/dto/chat/chat_messages/file_message.dart';
import 'package:projekt/infra/dto/chat/chat_messages/image_message.dart';
import 'package:projekt/infra/dto/chat/chat_messages/partial_text.dart';
import 'package:projekt/infra/dto/chat/chat_messages/text_message.dart';
import 'package:projekt/infra/dto/chat/chat_room.dart';
import 'package:projekt/infra/dto/chat/preview_data.dart';
import 'package:projekt/views/widgets/chat/chat_theme.dart';
import 'package:projekt/views/widgets/chat/chat_utils.dart';
import 'package:projekt/views/widgets/chat/conditional/conditional.dart';
import 'package:projekt/views/widgets/chat/model/chat_l10n.dart';
import 'package:projekt/views/widgets/chat/model/date_header.dart';
import 'package:projekt/views/widgets/chat/model/emoji_enlargement_behavior.dart';
import 'package:projekt/views/widgets/chat/model/message_spacer.dart';
import 'package:projekt/views/widgets/chat/model/preview_image.dart';
import 'package:projekt/views/widgets/chat/model/send_button_visibility_mode.dart';
import 'package:projekt/views/widgets/chat/widgets/chat_list.dart';
import 'package:projekt/views/widgets/chat/widgets/inherited_chat_theme.dart';
import 'package:projekt/views/widgets/chat/widgets/inherited_l10n.dart';
import 'package:projekt/views/widgets/chat/widgets/inherited_user.dart';
import 'package:projekt/views/widgets/chat/widgets/input.dart';
import 'package:projekt/views/widgets/chat/widgets/message_widget.dart';

class Chat extends StatefulWidget {
  /// Creates a chat widget
  const Chat({
    required this.messages,
    required this.user,
    required this.onSendPressed,
    required this.chatRoom,
    this.bubbleBuilder,
    this.customBottomWidget,
    this.customDateHeaderText,
    this.customMessageBuilder,
    this.dateFormat,
    this.dateHeaderThreshold = 900000,
    this.dateLocale,
    this.disableImageGallery,
    this.emojiEnlargementBehavior = EmojiEnlargementBehavior.multi,
    this.emptyState,
    this.fileMessageBuilder,
    this.groupMessagesThreshold = 60000,
    this.hideBackgroundOnEmojiMessages = true,
    this.imageMessageBuilder,
    this.isAttachmentUploading,
    this.isLastPage,
    this.l10n = const ChatL10nPl(),
    this.onAttachmentPressed,
    this.onAvatarTap,
    this.onBackgroundTap,
    this.onEndReached,
    this.onEndReachedThreshold,
    this.onMessageDoubleTap,
    this.onMessageLongPress,
    this.onMessageStatusLongPress,
    this.onMessageStatusTap,
    this.onMessageTap,
    this.onPreviewDataFetched,
    this.onTextChanged,
    this.onTextFieldTap,
    this.scrollPhysics,
    this.sendButtonVisibilityMode = SendButtonVisibilityMode.editing,
    this.showUserAvatars = false,
    this.showUserNames = false,
    this.textMessageBuilder,
    this.theme = const DefaultChatTheme(),
    this.timeFormat,
    this.usePreviewData = true,
    Key? key,
  }) : super(key: key);

  /// See [Message.bubbleBuilder]
  final Widget Function(
    Widget child, {
    required ChatMessage message,
    required bool nextMessageInGroup,
  })? bubbleBuilder;

  /// Allows you to replace the default Input widget e.g. if you want to create
  /// a channel view.
  final Widget? customBottomWidget;

  /// If [dateFormat], [dateLocale] and/or [timeFormat] is not enough to
  /// customize date headers in your case, use this to return an arbitrary
  /// string based on a [DateTime] of a particular message. Can be helpful to
  /// return "Today" if [DateTime] is today. IMPORTANT: this will replace
  /// all default date headers, so you must handle all cases yourself, like
  /// for example today, yesterday and before. Or you can just return the same
  /// date header for any message.
  final String Function(DateTime)? customDateHeaderText;

  /// See [Message.customMessageBuilder]
  final Widget Function(CustomMessage message, {required int messageWidth})? customMessageBuilder;

  /// Allows you to customize the date format. IMPORTANT: only for the date,
  /// do not return time here. See [timeFormat] to customize the time format.
  /// [dateLocale] will be ignored if you use this, so if you want a localized date
  /// make sure you initialize your [DateFormat] with a locale. See [customDateHeaderText]
  /// for more customization.
  final DateFormat? dateFormat;

  /// Time (in ms) between two messages when we will render a date header.
  /// Default value is 15 minutes, 900000 ms. When time between two messages
  /// is higher than this threshold, date header will be rendered. Also,
  /// not related to this value, date header will be rendered on every new day.
  final int dateHeaderThreshold;

  /// Locale will be passed to the `Intl` package. Make sure you initialized
  /// date formatting in your app before passing any locale here, otherwise
  /// an error will be thrown. Also see [customDateHeaderText], [dateFormat], [timeFormat].
  final String? dateLocale;

  /// Disable automatic image preview on tap.
  final bool? disableImageGallery;

  /// See [Message.emojiEnlargementBehavior]
  final EmojiEnlargementBehavior emojiEnlargementBehavior;

  /// Allows you to change what the user sees when there are no messages.
  /// `emptyChatPlaceholder` and `emptyChatPlaceholderTextStyle` are ignored
  /// in this case.
  final Widget? emptyState;

  /// See [Message.fileMessageBuilder]
  final Widget Function(FileMessage, {required int messageWidth})? fileMessageBuilder;

  /// Time (in ms) between two messages when we will visually group them.
  /// Default value is 1 minute, 60000 ms. When time between two messages
  /// is lower than this threshold, they will be visually grouped.
  final int groupMessagesThreshold;

  /// See [Message.hideBackgroundOnEmojiMessages]
  final bool hideBackgroundOnEmojiMessages;

  /// See [Message.imageMessageBuilder]
  final Widget Function(ImageMessage, {required int messageWidth})? imageMessageBuilder;

  /// See [Input.isAttachmentUploading]
  final bool? isAttachmentUploading;

  /// See [ChatList.isLastPage]
  final bool? isLastPage;

  /// Localized copy. Extend [ChatL10n] class to create your own copy or use
  /// existing one, like the default [ChatL10nEn]. You can customize only
  /// certain properties, see more here [ChatL10nEn].
  final ChatL10n l10n;

  /// List of [types.Message] to render in the chat widget
  final List<ChatMessage> messages;

  /// See [Input.onAttachmentPressed]
  final void Function()? onAttachmentPressed;

  /// See [Message.onAvatarTap]
  final void Function(PublicUserData)? onAvatarTap;

  /// Called when user taps on background
  final void Function()? onBackgroundTap;

  /// See [ChatList.onEndReached]
  final Future<void> Function()? onEndReached;

  /// See [ChatList.onEndReachedThreshold]
  final double? onEndReachedThreshold;

  /// See [Message.onMessageDoubleTap]
  final void Function(BuildContext context, ChatMessage)? onMessageDoubleTap;

  /// See [Message.onMessageLongPress]
  final void Function(BuildContext context, ChatMessage)? onMessageLongPress;

  /// See [Message.onMessageStatusLongPress]
  final void Function(BuildContext context, ChatMessage)? onMessageStatusLongPress;

  /// See [Message.onMessageStatusTap]
  final void Function(BuildContext context, ChatMessage)? onMessageStatusTap;

  /// See [Message.onMessageTap]
  final void Function(BuildContext context, ChatMessage)? onMessageTap;

  /// See [Message.onPreviewDataFetched]
  final void Function(TextMessage, PreviewData)? onPreviewDataFetched;

  /// See [Input.onSendPressed]
  final void Function(PartialText) onSendPressed;

  /// See [Input.onTextChanged]
  final void Function(String)? onTextChanged;

  /// See [Input.onTextFieldTap]
  final void Function()? onTextFieldTap;

  /// See [ChatList.scrollPhysics]
  final ScrollPhysics? scrollPhysics;

  /// See [Input.sendButtonVisibilityMode]
  final SendButtonVisibilityMode sendButtonVisibilityMode;

  /// See [Message.showUserAvatars]
  final bool showUserAvatars;

  /// Show user names for received messages. Useful for a group chat. Will be
  /// shown only on text messages.
  final bool showUserNames;

  final ChatRoom chatRoom;

  /// See [Message.textMessageBuilder]
  final Widget Function(
    TextMessage, {
    required int messageWidth,
    required bool showName,
  })? textMessageBuilder;

  /// Chat theme. Extend [ChatTheme] class to create your own theme or use
  /// existing one, like the [DefaultChatTheme]. You can customize only certain
  /// properties, see more here [DefaultChatTheme].
  final ChatTheme theme;

  /// Allows you to customize the time format. IMPORTANT: only for the time,
  /// do not return date here. See [dateFormat] to customize the date format.
  /// [dateLocale] will be ignored if you use this, so if you want a localized time
  /// make sure you initialize your [DateFormat] with a locale. See [customDateHeaderText]
  /// for more customization.
  final DateFormat? timeFormat;

  /// See [Message.usePreviewData]
  final bool usePreviewData;

  /// See [InheritedUser.user]
  final PublicUserData user;

  @override
  _ChatState createState() => _ChatState();
}

/// [Chat] widget state
class _ChatState extends State<Chat> {
  List<Object> _chatMessages = <Object>[];
  List<PreviewImage> _gallery = <PreviewImage>[];
  int _imageViewIndex = 0;
  bool _isImageViewVisible = false;

  @override
  void initState() {
    super.initState();

    didUpdateWidget(widget);
  }

  @override
  void didUpdateWidget(covariant Chat oldWidget) {
    super.didUpdateWidget(oldWidget);

    if (widget.messages.isNotEmpty) {
      final List<Object> result = calculateChatMessages(
        widget.messages,
        widget.user,
        customDateHeaderText: widget.customDateHeaderText,
        dateFormat: widget.dateFormat,
        dateHeaderThreshold: widget.dateHeaderThreshold,
        dateLocale: widget.dateLocale,
        groupMessagesThreshold: widget.groupMessagesThreshold,
        showUserNames: widget.showUserNames,
        timeFormat: widget.timeFormat,
      );

      _chatMessages = result[0] as List<Object>;
      _gallery = result[1] as List<PreviewImage>;
    }
  }

  Widget _emptyStateBuilder() {
    return widget.emptyState ??
        Container(
          alignment: Alignment.center,
          margin: const EdgeInsets.symmetric(
            horizontal: 24,
          ),
          child: Text(
            widget.l10n.emptyChatPlaceholder,
            style: widget.theme.emptyChatPlaceholderTextStyle,
            textAlign: TextAlign.center,
          ),
        );
  }

  Widget _imageGalleryBuilder() {
    return Dismissible(
      key: const Key('photo_view_gallery'),
      direction: DismissDirection.down,
      onDismissed: (DismissDirection direction) => _onCloseGalleryPressed(),
      child: Stack(
        children: <Widget>[
          PhotoViewGallery.builder(
            builder: (BuildContext context, int index) => PhotoViewGalleryPageOptions(
              imageProvider: Conditional().getProvider(_gallery[index].uri),
            ),
            itemCount: _gallery.length,
            loadingBuilder: _imageGalleryLoadingBuilder,
            onPageChanged: _onPageChanged,
            pageController: PageController(initialPage: _imageViewIndex),
            scrollPhysics: const ClampingScrollPhysics(),
          ),
          Positioned(
            right: 16,
            top: 56,
            child: CloseButton(
              color: Colors.white,
              onPressed: _onCloseGalleryPressed,
            ),
          ),
        ],
      ),
    );
  }

  Widget _imageGalleryLoadingBuilder(
    BuildContext context,
    ImageChunkEvent? event,
  ) {
    return Center(
      child: SizedBox(
        width: 20,
        height: 20,
        child: CircularProgressIndicator(
          value: event == null || event.expectedTotalBytes == null
              ? 0
              : event.cumulativeBytesLoaded / event.expectedTotalBytes!,
        ),
      ),
    );
  }

  Widget _messageBuilder(Object object, BoxConstraints constraints) {
    if (object is DateHeader) {
      return Container(
        alignment: Alignment.center,
        margin: widget.theme.dateDividerMargin,
        child: Text(
          object.text,
          style: widget.theme.dateDividerTextStyle,
        ),
      );
    } else if (object is MessageSpacer) {
      return SizedBox(
        height: object.height,
      );
    } else {
      final Map<String, dynamic> map = object as Map<String, dynamic>;
      final ChatMessage message = map['message']! as ChatMessage;
      final int _messageWidth = widget.showUserAvatars && message.author.id != widget.user.id
          ? min(constraints.maxWidth * 0.72, 440).floor()
          : min(constraints.maxWidth * 0.78, 440).floor();
      return MessageWidget(
        key: ValueKey<String>(message.id),
        bubbleBuilder: widget.bubbleBuilder,
        customMessageBuilder: widget.customMessageBuilder,
        emojiEnlargementBehavior: widget.emojiEnlargementBehavior,
        fileMessageBuilder: widget.fileMessageBuilder,
        hideBackgroundOnEmojiMessages: widget.hideBackgroundOnEmojiMessages,
        imageMessageBuilder: widget.imageMessageBuilder,
        message: message,
        messageWidth: _messageWidth,
        onAvatarTap: widget.onAvatarTap,
        onMessageDoubleTap: widget.onMessageDoubleTap,
        onMessageLongPress: widget.onMessageLongPress,
        onMessageStatusLongPress: widget.onMessageStatusLongPress,
        onMessageStatusTap: widget.onMessageStatusTap,
        onMessageTap: (BuildContext context, ChatMessage tappedMessage) {
          if (tappedMessage is ImageMessage && widget.disableImageGallery != true) {
            _onImagePressed(tappedMessage);
          }

          widget.onMessageTap?.call(context, tappedMessage);
        },
        onPreviewDataFetched: _onPreviewDataFetched,
        roundBorder: map['nextMessageInGroup'] == true,
        showAvatar: map['nextMessageInGroup'] == false,
        showName: map['showName'] == true,
        showStatus: map['showStatus'] == true,
        showUserAvatars: widget.showUserAvatars,
        textMessageBuilder: widget.textMessageBuilder,
        usePreviewData: widget.usePreviewData,
      );
    }
  }

  void _onCloseGalleryPressed() {
    setState(() {
      _isImageViewVisible = false;
    });
  }

  void _onImagePressed(ImageMessage message) {
    setState(() {
      _imageViewIndex = _gallery.indexWhere(
        (PreviewImage element) => element.id == message.id && element.uri == message.fileResponse.id,
      );
      _isImageViewVisible = true;
    });
  }

  void _onPageChanged(int index) {
    setState(() {
      _imageViewIndex = index;
    });
  }

  void _onPreviewDataFetched(
    TextMessage message,
    PreviewData previewData,
  ) {
    widget.onPreviewDataFetched?.call(message, previewData);
  }

  @override
  Widget build(BuildContext context) {
    return InheritedUser(
      user: widget.user,
      child: InheritedChatTheme(
        theme: widget.theme,
        child: InheritedL10n(
          l10n: widget.l10n,
          child: Stack(
            children: <Widget>[
              Column(
                children: <Widget>[
                  // ChatAppBar(chatRoom: widget.chatRoom),
                  Flexible(
                    child: widget.messages.isEmpty
                        ? SizedBox.expand(
                            child: _emptyStateBuilder(),
                          )
                        : GestureDetector(
                            onTap: () {
                              FocusManager.instance.primaryFocus?.unfocus();
                              widget.onBackgroundTap?.call();
                            },
                            child: LayoutBuilder(
                              builder: (BuildContext context, BoxConstraints constraints) => ChatList(
                                isLastPage: widget.isLastPage,
                                itemBuilder: (Object item, int? index) => _messageBuilder(item, constraints),
                                items: _chatMessages,
                                onEndReached: widget.onEndReached,
                                onEndReachedThreshold: widget.onEndReachedThreshold,
                                scrollPhysics: widget.scrollPhysics,
                              ),
                            ),
                          ),
                  ),
                  widget.customBottomWidget ??
                      Input(
                        isAttachmentUploading: widget.isAttachmentUploading,
                        onAttachmentPressed: widget.onAttachmentPressed,
                        onSendPressed: widget.onSendPressed,
                        onTextChanged: widget.onTextChanged,
                        onTextFieldTap: widget.onTextFieldTap,
                        sendButtonVisibilityMode: widget.sendButtonVisibilityMode,
                      ),
                ],
              ),
              if (_isImageViewVisible) _imageGalleryBuilder(),
            ],
          ),
        ),
      ),
    );
  }
}
