import React, { useEffect, useState } from 'react';
import {View, StyleSheet, Text, Button} from 'react-native';
import {Card} from "react-native-paper";
import Pressable from "react-native/Libraries/Components/Pressable/Pressable";


const HomeScreen = ({navigation}) => {
  return (
    <View>
      <Text>Test</Text>

      <Card style={styles.card}>
        <Text style={styles.heading}>Get to know your ranking result</Text>
        <Pressable  style={styles.button} onPress={() => navigation.navigate('Results')}>
          <Text style={styles.buttonTitle}>Check!</Text>
        </Pressable>
      </Card>
    </View>
  );
};

const styles = StyleSheet.create({
  card: {
    textAlign: 'center',
    margin: 15,
    padding: 15,
  },
  heading: {
    textAlign: 'center',
    marginBottom: 15,
  },
  buttonTitle: {
    textAlign: 'center',
  },
  button: {
    textAlign: 'center',
    paddingTop: 15,
    paddingBottom: 15,
    paddingStart: 20,
    paddingEnd: 20,
    backgroundColor: '#eeeeee'
  }
});

export default HomeScreen;
