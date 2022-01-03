import React, { useState } from 'react';
import { Text, View, StyleSheet } from 'react-native';
import Constants from 'expo-constants';

import { Card } from 'react-native-paper';
import CalculatorActionButton from './components/CalculatorActionButton';

export default function App() {
  let [calculatorValue, setCalculatorValue] = useState('0');

  const verticalCalculatorButtons = [
    [
      <CalculatorActionButton
        value={'AC'}
        color={'#646466'}
        onPressed={(value) => handleCalculatorValueClear()}
      />,
      <CalculatorActionButton
        value={''}
        flex={2}
        color={'#646466'}
        onPressed={(value) => {}}
      />,
      <CalculatorActionButton
        value={'÷'}
        color={'#646466'}
        onPressed={(value) => handleCalculatorTextButtonPressed(value)}
      />,
    ],
    [
      <CalculatorActionButton
        value={'7'}
        color={'#7C7E7F'}
        onPressed={(value) => handleCalculatorTextButtonPressed(value)}
      />,
      <CalculatorActionButton
        value={'8'}
        color={'#7C7E7F'}
        onPressed={(value) => handleCalculatorTextButtonPressed(value)}
      />,
      <CalculatorActionButton
        value={'9'}
        color={'#7C7E7F'}
        onPressed={(value) => handleCalculatorTextButtonPressed(value)}
      />,
      <CalculatorActionButton
        value={'×'}
        color={'#F1A33B'}
        onPressed={(value) => handleCalculatorTextButtonPressed(value)}
      />,
    ],
    [
      <CalculatorActionButton
        value={'4'}
        color={'#7C7E7F'}
        onPressed={(value) => handleCalculatorTextButtonPressed(value)}
      />,
      <CalculatorActionButton
        value={'5'}
        color={'#7C7E7F'}
        onPressed={(value) => handleCalculatorTextButtonPressed(value)}
      />,
      <CalculatorActionButton
        value={'6'}
        color={'#7C7E7F'}
        onPressed={(value) => handleCalculatorTextButtonPressed(value)}
      />,
      <CalculatorActionButton
        value={'-'}
        color={'#F1A33B'}
        onPressed={(value) => handleCalculatorTextButtonPressed(value)}
      />,
    ],
    [
      <CalculatorActionButton
        value={'1'}
        color={'#7C7E7F'}
        onPressed={(value) => handleCalculatorTextButtonPressed(value)}
      />,
      <CalculatorActionButton
        value={'2'}
        color={'#7C7E7F'}
        onPressed={(value) => handleCalculatorTextButtonPressed(value)}
      />,
      <CalculatorActionButton
        value={'3'}
        color={'#7C7E7F'}
        onPressed={(value) => handleCalculatorTextButtonPressed(value)}
      />,
      <CalculatorActionButton
        value={'+'}
        color={'#F1A33B'}
        onPressed={(value) => handleCalculatorTextButtonPressed(value)}
      />,
    ],
    [
      <CalculatorActionButton
        value={'0'}
        flex={2}
        color={'#7C7E7F'}
        onPressed={(value) => handleCalculatorTextButtonPressed(value)}
      />,
      <CalculatorActionButton
        value={','}
        color={'#7C7E7F'}
        onPressed={(value) => handleCalculatorTextButtonPressed(value)}
      />,
      <CalculatorActionButton
        value={'='}
        color={'#F1A33B'}
        onPressed={(value) => handleCalculatorValueSum()}
      />,
    ],
  ];

  const renderButtons = () => {
    const layouts = verticalCalculatorButtons.map((row, index) => {
      const rowItem = row.map((columnItem, buttonIndex) => {
        return columnItem;
      });

      return (
        <View style={styles.calculatorInputRow} key={'row-' + index}>
          {rowItem}{' '}
        </View>
      );
    });

    return layouts;
  };

  const handleCalculatorTextButtonPressed = (value) => {
    if( calculatorValue === '0') {
      setCalculatorValue(value);
    } else {
      setCalculatorValue(`${calculatorValue}${value}`);
    }
  };

  const handleCalculatorValueClear = () => {
    setCalculatorValue('0');
  };

  const handleCalculatorValueSum = () => {
    let sumValue = calculatorValue;
    sumValue = sumValue.replaceAll('×', '*').replaceAll('÷', '/');
    console.log(sumValue);
    setCalculatorValue(eval(sumValue));
  };

  const getFontSize = () => {
    const calculatorValueLength = calculatorValue.length;
    if( calculatorValueLength > 50 ) {
      return 20;
    } else if( calculatorValueLength > 40 ) {
      return 25;
    } else if( calculatorValueLength > 20 ) {
      return 40;
    } else if( calculatorValueLength > 5 ) {
      return 60;
    } else {
      return 80;
    }
  }

  return (
    <View style={styles.container}>
      <View style={styles.screenContainer}>
        <Text 
          style={[styles.screenText, {
            fontSize: getFontSize()
          }]}
        >
          {calculatorValue}
        </Text>
      </View>

      <View style={styles.buttonsContainer}>{renderButtons()}</View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    height: "100%",
    justifyContent: 'space-between',
    alignContent: 'center',
    backgroundColor: '#525456',
    flexDirection: 'column',
    padding: 8,
  },
  screenContainer: {
    flex: 3,
  },
  buttonsContainer: {
    flex: 5,
    flexDirection: 'column',
  },
  calculatorInputRow: {
    flex: 1,
    flexDirection: 'row',
  },
  screenText: {
    color: 'white',
    fontWeight: 'bold',
    padding: 20,
    textAlign: 'right',
  },
});
