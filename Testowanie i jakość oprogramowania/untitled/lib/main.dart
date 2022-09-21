import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      debugShowCheckedModeBanner: false,
      theme: ThemeData(),
      home: const MyHomePage(),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({Key? key}) : super(key: key);

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: <Widget>[
            // Some empty widget or header
            Container(
              color: Colors.blue,
              padding: const EdgeInsets.all(20),
              child: const Text('Title'),
            ),
            _DemoImage(),
            _DemoText(),
          ],
        ),
      ),
    );
  }
}

class _DemoImage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    const String imageUrl = 'https://static.wikia.nocookie.net/085b1f88-cd20-4657-96fa-12d77da38f39';
    return Container(
      padding: const EdgeInsets.all(10),
      color: Colors.green,
      child: Image.network(
        imageUrl,
        height: 200,
      ),
    );
  }
}

class _DemoText extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.all(10),
      color: Colors.yellow,
      child: const Text('no! god please! no!'),
    );
  }
}
