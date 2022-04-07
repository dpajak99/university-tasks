import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:projekt/infra/dto/chat/chat_messages/partial_text.dart';
import 'package:projekt/views/widgets/chat/model/send_button_visibility_mode.dart';
import 'package:projekt/views/widgets/chat/widgets/inherited_chat_theme.dart';

const double kDefaultItemsHeight = 45;

class NewLineIntent extends Intent {
  const NewLineIntent();
}

class SendMessageIntent extends Intent {
  const SendMessageIntent();
}

/// A class that represents bottom bar widget with a text field, attachment and
/// send buttons inside. By default hides send button when text field is empty.
class Input extends StatefulWidget {
  /// Creates [Input] widget
  const Input({
    required this.sendButtonVisibilityMode,
    required this.onSendPressed,
    this.isAttachmentUploading,
    this.onAttachmentPressed,
    this.onTextChanged,
    this.onTextFieldTap,
    Key? key,
  }) : super(key: key);

  /// See [AttachmentButton.onPressed]
  final void Function()? onAttachmentPressed;

  /// Whether attachment is uploading. Will replace attachment button with a
  /// [CircularProgressIndicator]. Since we don't have libraries for
  /// managing media in dependencies we have no way of knowing if
  /// something is uploading so you need to set this manually.
  final bool? isAttachmentUploading;

  /// Will be called on [SendButton] tap. Has [PartialText] which can
  /// be transformed to [TextMessage] and added to the messages list.
  final void Function(PartialText) onSendPressed;

  /// Will be called whenever the text inside [TextField] changes
  final void Function(String)? onTextChanged;

  /// Will be called on [TextField] tap
  final void Function()? onTextFieldTap;

  /// Controls the visibility behavior of the [SendButton] based on the
  /// [TextField] state inside the [Input] widget.
  /// Defaults to [SendButtonVisibilityMode.editing].
  final SendButtonVisibilityMode sendButtonVisibilityMode;

  @override
  _InputState createState() => _InputState();
}

/// [Input] widget state
class _InputState extends State<Input> {
  final FocusNode _inputFocusNode = FocusNode();
  bool _sendButtonVisible = false;
  final TextEditingController _textController = TextEditingController();

  @override
  void initState() {
    super.initState();

    if (widget.sendButtonVisibilityMode == SendButtonVisibilityMode.editing) {
      _sendButtonVisible = _textController.text.trim() != '';
      _textController.addListener(_handleTextControllerChange);
    } else {
      _sendButtonVisible = true;
    }
  }

  @override
  void dispose() {
    _inputFocusNode.dispose();
    _textController.dispose();
    super.dispose();
  }

  void _handleSendPressed() {
    final String trimmedText = _textController.text.trim();
    if (trimmedText != '') {
      final PartialText _partialText = PartialText(text: trimmedText, metadata: const <String, dynamic>{});
      widget.onSendPressed(_partialText);
      _textController.clear();
    }
  }

  void _handleTextControllerChange() {
    setState(() {
      _sendButtonVisible = _textController.text.trim() != '';
    });
  }

  Widget _inputBuilder() {
    return Focus(
      autofocus: true,
      child: Padding(
        padding: InheritedChatTheme.of(context).theme.inputMargin,
        child: Material(
          child: Container(
            padding: const EdgeInsets.only(left: 20, right: 20, bottom: 30),
            child: Row(
              children: <Widget>[
                if (widget.onAttachmentPressed != null) _leftWidgetBuilder(),
                const SizedBox(width: 10),
                Expanded(
                  child: SizedBox(
                    height: kDefaultItemsHeight,
                    child: TextFormField(
                      controller: _textController,
                      focusNode: _inputFocusNode,
                      keyboardType: TextInputType.multiline,
                      maxLines: 5,
                      minLines: 1,
                      onChanged: widget.onTextChanged,
                      onTap: widget.onTextFieldTap,
                      textCapitalization: TextCapitalization.sentences,
                    ),
                  ),
                ),
                const SizedBox(width: 10),
                Visibility(
                  visible: _sendButtonVisible,
                  child: _buildComposerButton(
                      icon: Icons.send,
                      color: Theme.of(context).primaryColor,
                      iconColor: Theme.of(context).colorScheme.onPrimary,
                      onTap: _handleSendPressed),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }

  Widget _leftWidgetBuilder() {
    if (widget.isAttachmentUploading == true) {
      return Container(
        height: 24,
        margin: const EdgeInsets.only(right: 16),
        width: 24,
        child: CircularProgressIndicator(
          backgroundColor: Colors.transparent,
          strokeWidth: 1.5,
          valueColor: AlwaysStoppedAnimation<Color>(
            InheritedChatTheme.of(context).theme.inputTextColor,
          ),
        ),
      );
    } else {
      return _buildComposerButton(
        icon: Icons.attach_file,
        color: const Color(0xFFE5E5E5),
        iconColor: Colors.black,
        onTap: widget.onAttachmentPressed != null ? () => widget.onAttachmentPressed!() : null,
      );
    }
  }

  @override
  Widget build(BuildContext context) {
    final bool isAndroid = Theme.of(context).platform == TargetPlatform.android;
    final bool isIOS = Theme.of(context).platform == TargetPlatform.iOS;

    return GestureDetector(
      onTap: _inputFocusNode.requestFocus,
      child: isAndroid || isIOS
          ? _inputBuilder()
          : Shortcuts(
              shortcuts: <ShortcutActivator, Intent>{
                LogicalKeySet(LogicalKeyboardKey.enter): const SendMessageIntent(),
                LogicalKeySet(LogicalKeyboardKey.enter, LogicalKeyboardKey.alt): const NewLineIntent(),
                LogicalKeySet(LogicalKeyboardKey.enter, LogicalKeyboardKey.shift): const NewLineIntent(),
              },
              child: Actions(
                actions: <Type, Action<Intent>>{
                  SendMessageIntent: CallbackAction<SendMessageIntent>(
                    onInvoke: (SendMessageIntent intent) => _handleSendPressed(),
                  ),
                  NewLineIntent: CallbackAction<NewLineIntent>(
                    onInvoke: (NewLineIntent intent) {
                      final String _newValue = '${_textController.text}\r\n';
                      _textController.value = TextEditingValue(
                        text: _newValue,
                        selection: TextSelection.fromPosition(
                          TextPosition(offset: _newValue.length),
                        ),
                      );
                    },
                  ),
                },
                child: _inputBuilder(),
              ),
            ),
    );
  }

  Widget _buildComposerButton({
    required IconData icon,
    required Color color,
    required Color iconColor,
    required void Function()? onTap,
  }) {
    return InkWell(
      onTap: onTap,
      child: Container(
        height: kDefaultItemsHeight,
        width: kDefaultItemsHeight + 10,
        decoration: BoxDecoration(
          color: color,
          borderRadius: BorderRadius.circular(8),
        ),
        child: Center(
          child: Icon(
            icon,
            color: iconColor,
          ),
        ),
      ),
    );
  }
}
