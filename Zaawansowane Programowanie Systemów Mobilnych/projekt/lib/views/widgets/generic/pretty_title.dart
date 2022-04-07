import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class PrettyTitle extends StatelessWidget {
  final double bigSize;

  final double? smallSize;

  final String title;
  final String? subTitle;

  const PrettyTitle({
    Key? key,
    required this.bigSize,
    this.smallSize,
    required this.title,
    this.subTitle,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    final TextStyle widgetStyle =
        CupertinoTheme.of(context).textTheme.navLargeTitleTextStyle;

    return RichText(
      text: TextSpan(
        text: title,
        style: widgetStyle.copyWith(
          fontSize: bigSize,
          color: Colors.black,
        ),
        children: [
          if (subTitle != null)
            TextSpan(
              text: ' $subTitle',
              style: widgetStyle.copyWith(
                fontSize: smallSize,
                color: Theme.of(context).primaryColor.withOpacity(0.5),
              ),
            )
        ],
      ),
    );
  }
}
