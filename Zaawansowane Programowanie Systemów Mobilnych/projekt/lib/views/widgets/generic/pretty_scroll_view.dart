import 'package:flutter/material.dart';
import 'package:projekt/views/widgets/generic/no_glov_behaviour.dart';
import 'package:projekt/views/widgets/generic/pretty_title.dart';

class PrettyScrollView extends StatefulWidget {
  final String title;
  final String? subTitle;
  final Widget body;
  final Widget? leading;
  final List<Widget> actions;

  const PrettyScrollView({
    required this.title,
    required this.body,
    required this.actions,
    this.subTitle,
    this.leading,
    Key? key,

  }) : super(key: key);

  @override
  State<StatefulWidget> createState() => _PrettyScrollView();
}

class _PrettyScrollView extends State<PrettyScrollView> {
  ScrollController scrollController = ScrollController();
  bool _visible = false;

  @override
  void initState() {
    super.initState();
    scrollController.addListener(() {
      setState(() {
        if (scrollController.offset > 42) {
          _visible = true;
        } else if (_visible) {
          _visible = false;
        }
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    return NestedScrollView(
      controller: scrollController,
      headerSliverBuilder: (BuildContext context, bool innerBoxIsScrolled) {
        return <Widget>[
          SliverOverlapAbsorber(
            handle: NestedScrollView.sliverOverlapAbsorberHandleFor(context),
            sliver: SliverAppBar(
              expandedHeight: 120.0,
              floating: false,
              automaticallyImplyLeading: false,
              pinned: true,
              elevation: 1,
              forceElevated: _visible ? true : false,
              backgroundColor: Theme.of(context).scaffoldBackgroundColor,
              titleSpacing: 0,
              title: AppBar(
                leading: widget.leading,
                actions: widget.actions,
                title: AnimatedOpacity(
                    opacity: _visible ? 1.0 : 0.0,
                    duration: const Duration(milliseconds: 150),
                    // The green box must be a child of the AnimatedOpacity widget.
                    child: PrettyTitle(
                      bigSize: 20,
                      smallSize: 10,
                      title: widget.title,
                      subTitle: widget.subTitle,
                    )),
                backgroundColor: Theme.of(context).scaffoldBackgroundColor,
              ),
              flexibleSpace: FlexibleSpaceBar(
                titlePadding: const EdgeInsetsDirectional.only(
                  bottom: 16.0,
                  start: 12.0,
                ),
                title: Row(
                  crossAxisAlignment: CrossAxisAlignment.end,
                  children: [
                    PrettyTitle(
                      bigSize: 22,
                      smallSize: 8,
                      title: widget.title,
                      subTitle: widget.subTitle,
                    ),
                  ],
                ),
              ),
            ),
          ),
        ];
      },
      body: Builder(builder: (context) {
        return ScrollConfiguration(
          behavior: NoGlowBehaviour(),
          child: CustomScrollView(
            slivers: <Widget>[
              SliverOverlapInjector(
                handle: NestedScrollView.sliverOverlapAbsorberHandleFor(context),
              ),
              SliverToBoxAdapter(
                child: widget.body,
              ),
            ],
          ),
        );
      }),
    );
  }
}
