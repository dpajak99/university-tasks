import React, { Component } from 'react';
import { Text, View, StyleSheet, TouchableOpacity } from 'react-native';

export default function CalculatorActionButton(props) {
  return (
    <TouchableOpacity
      style={[
        styles.container,
        {
          flex: props.flex != null ? props.flex : 1,
          backgroundColor: props.color,
        },
      ]}
      onPress={() => props.onPressed(props.value)}>
      <View style={{ flexDirection: 'row', alignItems: 'flex-start' }}>
          <Text style={styles.text}>{props.value}</Text>
      </View>
    </TouchableOpacity>
  );
}

const styles = StyleSheet.create({
  container: {
    margin: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },

  text: {
    color: 'white',
    fontSize: 26,
    lineHeight: 30,
  },
});
