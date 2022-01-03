import React, { Component } from 'react';
import { Text, View, StyleSheet, TouchableOpacity } from 'react-native';

export default function SubWidget(props) {
  return (
    <View style={{ flexDirection: 'row', alignItems: 'flex-start', marginTop: 10}}>
      <Text>{props.text}</Text>
      <Text style={{ fontSize: 15, lineHeight: 40 }}>{props.value}</Text>
    </View>
  );
}
