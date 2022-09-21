import 'package:auto_route/auto_route.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:projekt/blocs/specifics_blocs/chat_cubit/chat_cubit.dart';
import 'package:projekt/blocs/specifics_blocs/websocket_listener_cubit/websocket_listener_cubit.dart';
import 'package:projekt/config/design_colors.dart';
import 'package:projekt/config/locator.dart';
import 'package:projekt/infra/services/auth/auth_service.dart';
import 'package:projekt/providers/account_provider.dart';
import 'package:projekt/shared/models/account.dart';
import 'package:projekt/shared/router/router.gr.dart';
import 'package:projekt/views/widgets/buttons/custom_elevated_button.dart';
import 'package:projekt/views/widgets/buttons/custom_outlined_button.dart';
import 'package:projekt/views/widgets/form/custom_text_form_field.dart';

class LoginPage extends StatefulWidget {
  const LoginPage({Key? key}) : super(key: key);

  @override
  State<StatefulWidget> createState() => _LoginPage();
}

class _LoginPage extends State<LoginPage> {
  final TextEditingController emailTextController = TextEditingController();
  final TextEditingController passwordTextController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Form(
      child: Padding(
        padding: const EdgeInsets.symmetric(horizontal: 20),
        child: Column(
          children: <Widget>[
            const Spacer(),
            const Icon(
              Icons.lock_open,
              size: 100,
            ),
            const Text(
              'Unsafe Chat App',
              style: TextStyle(
                fontSize: 24,
                fontWeight: FontWeight.bold,
              ),
            ),
            const Text(
              'we know everything about you',
              style: TextStyle(
                fontSize: 16,
                fontWeight: FontWeight.w300,
                color: DesignColors.blue,
              ),
            ),
            const Spacer(),
            CustomTextFormField(label: 'email', controller: emailTextController),
            const SizedBox(height: 16),
            CustomTextFormField(label: 'password', controller: passwordTextController),
            const SizedBox(height: 16),
            CustomElevatedButton(
              label: 'login',
              onPressed: _onLoginPressed,
            ),
            const Spacer(),
            const Text('don\'t have an account?'),
            const SizedBox(height: 16),
            CustomOutlinedButton(
              label: 'sign up',
              onPressed: () => AutoRouter.of(context).navigate(const RegisterRoute()),
            ),
            const Spacer(),
          ],
        ),
      ),
    );
  }

  Future<void> _onLoginPressed() async {
    try {
      Account account = await globalLocator<AuthService>().login(emailTextController.text, passwordTextController.text);
      BlocProvider.of<WebsocketListenerCubit>(context).init();
      BlocProvider.of<ChatCubit>(context).init();
      AutoRouter.of(context).navigate(const MainWrapperRoute(
        children: <PageRouteInfo>[
          ChatRoomsRoute(),
        ],
      ));
    } catch (_) {
      print('Error while logging in');
    }
  }
}
