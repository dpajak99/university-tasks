import 'package:flutter/material.dart';
import 'package:projekt/config/design_colors.dart';

class CustomTextFormField extends StatelessWidget {
  final String label;
  final TextEditingController controller;

  const CustomTextFormField({
    required this.label,
    required this.controller,
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    InputBorder border = OutlineInputBorder(
      borderSide: const BorderSide(
        color: DesignColors.blue,
        width: 1,
      ),
      borderRadius: BorderRadius.circular(15),
    );

    return SizedBox(
      width: double.infinity,
      height: 36,
      child: TextFormField(
        controller: controller,
        decoration: InputDecoration(
          label: Text(
            label,
            style: const TextStyle(
              color: DesignColors.blue,
            ),
          ),
          border: border,
          disabledBorder: border,
          enabledBorder: border,
          errorBorder: border,
          focusedBorder: border,
          focusedErrorBorder: border,
        ),
      ),
    );
  }
}
