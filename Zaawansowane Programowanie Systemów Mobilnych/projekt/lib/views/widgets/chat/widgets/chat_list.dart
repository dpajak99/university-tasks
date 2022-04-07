import 'package:diffutil_dart/diffutil.dart';
import 'package:flutter/material.dart';
import 'package:projekt/infra/dto/chat/chat_message.dart';
import 'package:projekt/views/widgets/chat/widgets/inherited_chat_theme.dart';
import 'package:projekt/views/widgets/chat/widgets/inherited_user.dart';

/// Animated list which handles automatic animations and pagination
class ChatList extends StatefulWidget {
  /// Creates a chat list widget
  const ChatList({
    required this.itemBuilder,
    required this.items,
    this.isLastPage,
    this.onEndReached,
    this.onEndReachedThreshold,
    this.scrollPhysics,
    Key? key,
  }) : super(key: key);

  /// Used for pagination (infinite scroll) together with [onEndReached].
  /// When true, indicates that there are no more pages to load and
  /// pagination will not be triggered.
  final bool? isLastPage;

  /// Items to build
  final List<Object> items;

  /// Item builder
  final Widget Function(Object, int? index) itemBuilder;

  /// Used for pagination (infinite scroll). Called when user scrolls
  /// to the very end of the list (minus [onEndReachedThreshold]).
  final Future<void> Function()? onEndReached;

  /// Used for pagination (infinite scroll) together with [onEndReached].
  /// Can be anything from 0 to 1, where 0 is immediate load of the next page
  /// as soon as scroll starts, and 1 is load of the next page only if scrolled
  /// to the very end of the list. Default value is 0.75, e.g. start loading
  /// next page when scrolled through about 3/4 of the available content.
  final double? onEndReachedThreshold;

  /// Determines the physics of the scroll view
  final ScrollPhysics? scrollPhysics;

  @override
  _ChatListState createState() => _ChatListState();
}

/// [ChatList] widget state
class _ChatListState extends State<ChatList> with SingleTickerProviderStateMixin {
  bool _isNextPageLoading = false;
  final GlobalKey<SliverAnimatedListState> _listKey = GlobalKey<SliverAnimatedListState>();
  late List<Object> _oldData = List<Object>.from(widget.items);
  final ScrollController _scrollController = ScrollController();

  late final AnimationController _controller = AnimationController(vsync: this);

  late final Animation<double> _animation = CurvedAnimation(
    curve: Curves.easeOutQuad,
    parent: _controller,
  );

  @override
  void initState() {
    super.initState();

    didUpdateWidget(widget);
  }

  @override
  void didUpdateWidget(covariant ChatList oldWidget) {
    super.didUpdateWidget(oldWidget);

    _calculateDiffs(oldWidget.items);
  }

  @override
  void dispose() {
    super.dispose();

    _controller.dispose();
    _scrollController.dispose();
  }

  Future<void> _calculateDiffs(List<Object> oldList) async {
    final DiffResult<Object> diffResult = calculateListDiff<Object>(
      oldList,
      widget.items,
      equalityChecker: (Object item1, Object item2) {
        if (item1 is Map<String, Object> && item2 is Map<String, Object>) {
          final ChatMessage message1 = item1['message']! as ChatMessage;
          final ChatMessage message2 = item2['message']! as ChatMessage;

          return message1.id == message2.id;
        } else {
          return item1 == item2;
        }
      },
    );

    for (final DiffUpdate update in diffResult.getUpdates(batch: false)) {
      update.when(
        insert: (int pos, int count) {
          _listKey.currentState?.insertItem(pos);
        },
        remove: (int pos, int count) {
          final Object item = oldList[pos];
          _listKey.currentState?.removeItem(
            pos,
            (_, Animation<double> animation) => _removedMessageBuilder(item, animation),
          );
        },
        change: (int pos, Object? payload) {},
        move: (int from, int to) {},
      );
    }

    _scrollToBottomIfNeeded(oldList);

    _oldData = List<Object>.from(widget.items);
  }

  Widget _newMessageBuilder(int index, Animation<double> animation) {
    try {
      final Object item = _oldData[index];
      return SizeTransition(
        axisAlignment: -1,
        sizeFactor: animation.drive(CurveTween(curve: Curves.easeOutQuad)),
        child: widget.itemBuilder(item, index),
      );
    } catch (e) {
      return const SizedBox();
    }
  }

  Widget _removedMessageBuilder(Object item, Animation<double> animation) {
    return SizeTransition(
      axisAlignment: -1,
      sizeFactor: animation.drive(CurveTween(curve: Curves.easeInQuad)),
      child: FadeTransition(
        opacity: animation.drive(CurveTween(curve: Curves.easeInQuad)),
        child: widget.itemBuilder(item, null),
      ),
    );
  }

  // Hacky solution to reconsider
  void _scrollToBottomIfNeeded(List<Object> oldList) {
    try {
      // Take index 1 because there is always a spacer on index 0
      final Object oldItem = oldList[1];
      final Object item = widget.items[1];

      if (oldItem is Map<String, Object> && item is Map<String, Object>) {
        final ChatMessage oldMessage = oldItem['message']! as ChatMessage;
        final ChatMessage message = item['message']! as ChatMessage;

        // Compare items to fire only on newly added messages
        if (oldMessage != message) {
          // Run only for sent message
          if (message.author.id == InheritedUser.of(context).user.id) {
            // Delay to give some time for Flutter to calculate new
            // size after new message was added
            Future<void>.delayed(const Duration(milliseconds: 100), () {
              if (_scrollController.hasClients) {
                _scrollController.animateTo(
                  0,
                  duration: const Duration(milliseconds: 200),
                  curve: Curves.easeInQuad,
                );
              }
            });
          }
        }
      }
    } catch (e) {
      // Do nothing if there are no items
    }
  }

  @override
  Widget build(BuildContext context) {
    return NotificationListener<ScrollNotification>(
      onNotification: (ScrollNotification notification) {
        if (widget.onEndReached == null || widget.isLastPage == true) {
          return false;
        }

        if (notification.metrics.pixels >=
            (notification.metrics.maxScrollExtent * (widget.onEndReachedThreshold ?? 0.75))) {
          if (widget.items.isEmpty || _isNextPageLoading) return false;

          _controller.duration = const Duration();
          //ignore: cascade_invocations
          _controller.forward();

          setState(() {
            _isNextPageLoading = true;
          });

          widget.onEndReached!().whenComplete(() {
            _controller.duration = const Duration(milliseconds: 300);
            //ignore: cascade_invocations
            _controller.reverse();

            setState(() {
              _isNextPageLoading = false;
            });
          });
        }

        return false;
      },
      child: CustomScrollView(
        controller: _scrollController,
        physics: widget.scrollPhysics,
        reverse: true,
        slivers: <Widget>[
          SliverPadding(
            padding: const EdgeInsets.only(bottom: 4),
            sliver: SliverAnimatedList(
              initialItemCount: widget.items.length,
              key: _listKey,
              itemBuilder: (_, int index, Animation<double> animation) => _newMessageBuilder(index, animation),
            ),
          ),
          SliverPadding(
            padding: const EdgeInsets.only(
              top: 16,
            ),
            sliver: SliverToBoxAdapter(
              child: SizeTransition(
                axisAlignment: 1,
                sizeFactor: _animation,
                child: Center(
                  child: Container(
                    alignment: Alignment.center,
                    height: 32,
                    width: 32,
                    child: SizedBox(
                      height: 16,
                      width: 16,
                      child: _isNextPageLoading
                          ? CircularProgressIndicator(
                              backgroundColor: Colors.transparent,
                              strokeWidth: 1.5,
                              valueColor: AlwaysStoppedAnimation<Color>(
                                InheritedChatTheme.of(context).theme.primaryColor,
                              ),
                            )
                          : null,
                    ),
                  ),
                ),
              ),
            ),
          ),
        ],
      ),
    );
  }
}
