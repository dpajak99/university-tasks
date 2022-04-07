import 'package:flutter/material.dart';
import 'package:projekt/config/design_colors.dart';

class CustomOutlinedButton extends StatelessWidget {
  final String label;
  final void Function() onPressed;

  const CustomOutlinedButton({
    required this.label,
    required this.onPressed,
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      width: double.infinity,
      height: 36,
      child: OutlinedButton(
        onPressed: onPressed,
        style: ButtonStyle(
          shape: MaterialStateProperty.all(
            RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(15),
              side: const BorderSide(
                color: DesignColors.blue,
              ),
            ),
          ),
          padding: MaterialStateProperty.all(
            const EdgeInsets.symmetric(
              horizontal: 20,
              vertical: 10,
            ),
          ),
        ),
        child: Text(
          label.toUpperCase(),
          style: const TextStyle(
            color: DesignColors.blue,
          ),
        ),
      ),
    );
  }
}
