import React, { Component } from 'react';
import { Text, View, StyleSheet, TouchableOpacity } from 'react-native';

export default function CalculatorActionButton(props) {

  const styles = StyleSheet.create({
    container: {
      flex: props.flex != null ? props.flex : 1,
      margin: 1,
      backgroundColor: props.color,
      justifyContent: 'center',
      alignItems: 'center',
    },

    text: {
    color: 'white',
    fontSize: 26,
  },
  });

  return (
    <TouchableOpacity
      style={styles.container}
      onPress={() => props.onPressed(props.value)}
      >
      <Text style={styles.text}>{props.value}</Text>
    </TouchableOpacity>
  );


}