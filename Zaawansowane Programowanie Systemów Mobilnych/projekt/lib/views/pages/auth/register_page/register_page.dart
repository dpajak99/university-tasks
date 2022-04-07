import 'package:auto_route/auto_route.dart';
import 'package:flutter/material.dart';
import 'package:projekt/config/locator.dart';
import 'package:projekt/infra/dto/auth/register/request/register_req.dart';
import 'package:projekt/infra/repositories/auth/auth_repository.dart';
import 'package:projekt/infra/services/auth/auth_service.dart';
import 'package:projekt/shared/router/router.gr.dart';
import 'package:projekt/views/widgets/buttons/custom_elevated_button.dart';
import 'package:projekt/views/widgets/form/custom_text_form_field.dart';
import 'package:projekt/views/widgets/generic/pretty_scroll_view.dart';

class RegisterPage extends StatelessWidget {
  const RegisterPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    final TextEditingController _emailController = TextEditingController();
    final TextEditingController _passwordController = TextEditingController();
    final TextEditingController _firstNameController = TextEditingController();
    final TextEditingController _lastNameController = TextEditingController();
    final TextEditingController _phoneController = TextEditingController();

    TextStyle headerStyle = const TextStyle(
      fontSize: 16,
      color: Colors.black,
      fontWeight: FontWeight.bold,
    );

    TextStyle subheaderStyle = const TextStyle(
      fontSize: 16,
      color: Color(0xFF979699),
    );

    return Scaffold(
      body: PrettyScrollView(
        leading: IconButton(
          icon: const Icon(
            Icons.arrow_back,
            color: Colors.black,
          ),
          onPressed: () => Navigator.of(context).pop(),
        ),
        title: 'Sign up',
        actions: const [],
        body: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 10),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.start,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              Text('give us some sensitive data', style: headerStyle),
              Text('we prefer the same data as for your bank account, but its not required', style: subheaderStyle),
              const SizedBox(height: 15),
              CustomTextFormField(label: 'email', controller: _emailController),
              const SizedBox(height: 12),
              CustomTextFormField(label: 'password', controller: _passwordController),
              const SizedBox(height: 41),
              Text('who you are?', style: headerStyle),
              const SizedBox(height: 12),
              Text('lorem ipsum', style: subheaderStyle),
              const SizedBox(height: 15),
              CustomTextFormField(label: 'first name', controller: _firstNameController),
              const SizedBox(height: 12),
              CustomTextFormField(label: 'last name', controller: _lastNameController),
              const SizedBox(height: 41),
              Text('where should we call at 3 am?', style: headerStyle),
              const SizedBox(height: 12),
              Text('would you like to buy a pot?', style: subheaderStyle),
              const SizedBox(height: 15),
              CustomTextFormField(label: 'phone', controller: _phoneController),
              const SizedBox(height: 41),
              CustomElevatedButton(
                label: 'create account',
                onPressed: () async {
                  try {
                    await globalLocator<AuthService>().register(
                      RegisterRequest(
                        email: _emailController.text,
                        password: _passwordController.text,
                        phone: _phoneController.text,
                        lastName: _lastNameController.text,
                        firstName: _firstNameController.text,
                      ),
                    );
                    await globalLocator<AuthService>().login(_emailController.text, _passwordController.text);
                    await AutoRouter.of(context).navigate(const MainWrapperRoute(
                      children: <PageRouteInfo>[
                        ChatRoomsRoute(),
                      ],
                    ));
                  } catch (e) {
                    print(e);
                  }
                },
              ),
            ],
          ),
        ),
      ),
    );
  }
}
