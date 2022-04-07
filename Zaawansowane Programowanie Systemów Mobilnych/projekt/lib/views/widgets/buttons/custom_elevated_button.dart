import 'package:flutter/material.dart';
import 'package:projekt/config/design_colors.dart';

class CustomElevatedButton extends StatelessWidget {
  final String label;
  final void Function() onPressed;

  const CustomElevatedButton({
    required this.label,
    required this.onPressed,
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      width: double.infinity,
      height: 36,
      child: ElevatedButton(
        onPressed: onPressed,
        style: ButtonStyle(
          backgroundColor: MaterialStateProperty.all<Color>(DesignColors.blue),
          shape: MaterialStateProperty.all(
            RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(15),
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
            color: DesignColors.white,
          ),
        ),
      ),
    );
  }
}
