import 'package:flutter/material.dart';
import 'package:projekt/infra/dto/auth/users/public_user_data.dart';
import 'package:projekt/infra/dto/chat/chat_messages/image_message.dart';
import 'package:projekt/views/widgets/chat/chat_utils.dart';
import 'package:projekt/views/widgets/chat/conditional/conditional.dart';
import 'package:projekt/views/widgets/chat/widgets/inherited_chat_theme.dart';
import 'package:projekt/views/widgets/chat/widgets/inherited_user.dart';

/// A class that represents image message widget. Supports different
/// aspect ratios, renders blurred image as a background which is visible
/// if the image is narrow, renders image in form of a file if aspect
/// ratio is very small or very big.
class ImageMessageWidget extends StatefulWidget {
  /// Creates an image message widget based on [types.ImageMessage]
  const ImageMessageWidget({
    required this.message,
    required this.messageWidth,
    Key? key,
  }) : super(key: key);

  /// [ImageMessage]
  final ImageMessage message;

  /// Maximum message width
  final int messageWidth;

  @override
  _ImageMessageWidget createState() => _ImageMessageWidget();
}

/// [ImageMessage] widget state
class _ImageMessageWidget extends State<ImageMessageWidget> {
  ImageProvider? _image;
  ImageStream? _stream;
  Size _size = const Size(0, 0);

  @override
  void initState() {
    super.initState();
    _image = Conditional().getProvider(widget.message.url);
  }

  @override
  void didChangeDependencies() {
    super.didChangeDependencies();
    if (_size.isEmpty) {
      _getImage();
    }
  }

  void _getImage() {
    final ImageStream? oldImageStream = _stream;
    _stream = _image?.resolve(createLocalImageConfiguration(context));
    if (_stream?.key == oldImageStream?.key) {
      return;
    }
    final ImageStreamListener listener = ImageStreamListener(_updateImage);
    oldImageStream?.removeListener(listener);
    _stream?.addListener(listener);
  }

  void _updateImage(ImageInfo info, bool _) {
    setState(() {
      _size = Size(
        info.image.width.toDouble(),
        info.image.height.toDouble(),
      );
    });
  }

  @override
  void dispose() {
    _stream?.removeListener(ImageStreamListener(_updateImage));
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final PublicUserData _user = InheritedUser.of(context).user;

    if (_size.aspectRatio == 0) {
      return Container(
        color: InheritedChatTheme.of(context).theme.secondaryColor,
        height: _size.height,
        width: _size.width,
      );
    } else if (_size.aspectRatio < 0.1 || _size.aspectRatio > 10) {
      return Container(
        color: _user.id == widget.message.author.id
            ? InheritedChatTheme.of(context).theme.primaryColor
            : InheritedChatTheme.of(context).theme.secondaryColor,
        child: Row(
          mainAxisSize: MainAxisSize.min,
          children: <Widget>[
            Container(
              height: 64,
              margin: EdgeInsets.fromLTRB(
                InheritedChatTheme.of(context).theme.messageInsetsVertical,
                InheritedChatTheme.of(context).theme.messageInsetsVertical,
                16,
                InheritedChatTheme.of(context).theme.messageInsetsVertical,
              ),
              width: 64,
              child: ClipRRect(
                borderRadius: BorderRadius.circular(15),
                child: Image(
                  fit: BoxFit.cover,
                  image: _image!,
                ),
              ),
            ),
            Flexible(
              child: Container(
                margin: EdgeInsets.fromLTRB(
                  0,
                  InheritedChatTheme.of(context).theme.messageInsetsVertical,
                  InheritedChatTheme.of(context).theme.messageInsetsHorizontal,
                  InheritedChatTheme.of(context).theme.messageInsetsVertical,
                ),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: <Widget>[
                    Text(
                      widget.message.fileResponse.name,
                      style: _user.id == widget.message.author.id
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
                        formatBytes(widget.message.fileResponse.size.truncate()),
                        style: _user.id == widget.message.author.id
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
      );
    } else {
      return Container(
        constraints: BoxConstraints(
          maxHeight: widget.messageWidth.toDouble(),
          minWidth: 170,
        ),
        child: AspectRatio(
          aspectRatio: _size.aspectRatio > 0 ? _size.aspectRatio : 1,
          child: Image(
            fit: BoxFit.contain,
            image: _image!,
          ),
        ),
      );
    }
  }
}
