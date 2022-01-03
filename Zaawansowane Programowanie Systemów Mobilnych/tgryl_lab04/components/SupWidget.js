import React, { Component } from 'react';
import { Text, View, StyleSheet, TouchableOpacity } from 'react-native';

export default function SupWidget(props) {
  return (
    <View style={{ flexDirection: 'row', alignItems: 'flex-start' }}>
      <Text>{props.text}</Text>
      <Text style={{ fontSize: 15, lineHeight: 18 }}>{props.value}</Text>
    </View>
  );
}
