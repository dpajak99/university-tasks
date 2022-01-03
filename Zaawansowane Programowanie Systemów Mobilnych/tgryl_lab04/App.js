import React, { useState, useEffect } from 'react';

import { Text, View, Dimensions, StyleSheet } from 'react-native';
import Constants from 'expo-constants';

import { Card } from 'react-native-paper';
import CalculatorActionButton from './components/CalculatorActionButton';
import SupWidget from './components/SupWidget';
import SubWidget from './components/SubWidget';

const window = Dimensions.get('window');
const screen = Dimensions.get('screen');

var math = require('math-expression-evaluator');

const isPortrait = true;

const calculatorItems = [
  [
    {
      name: '√',
      color: '#626364',
      type: 'function',
      onClick: (value) => {
        return { visible: `root(${value})`, hidden: `√(${value})` };
      },
    },
    {
      name: 'x!',
      color: '#626364',
      type: 'function',
      onClick: (value) => {
        return { visible: `(${value})!`, hidden: `(${value})!` };
      },
    },
    {
      name: 'AC',
      type: 'clear',
      color: '#626364',
    },
    {
      type: 'flexbox',
      flex: 2,
      color: '#626364',
    },
    {
      name: '÷',
      value: '/',
      type: 'operation',
      color: '#FF8E2C',
    },
  ],
  [
    {
      name: <SupWidget text={'e'} value={'x'} />,
      color: '#626364',
      type: 'function',
      onClick: (value) => {
        return {
          visible: `pow(e, ${value})`,
          hidden: <SupWidget text={'e'} value={value} />,
        };
      },
    },
    {
      name: <SupWidget text={'10'} value={'x'} />,
      color: '#626364',
      type: 'function',
      onClick: (value) => {
        return {
          visible: `pow(10,${value})`,
          hidden: <SupWidget text={'10'} value={value} />,
        };
      },
    },

    {
      name: '9',
      type: 'basic',
      value: 9,
      color: '#7C7C7D',
    },
    {
      name: '8',
      type: 'basic',
      value: 8,
      color: '#7C7C7D',
    },
    {
      name: '7',
      type: 'basic',
      value: 7,
      color: '#7C7C7D',
    },
    {
      name: 'x',
      value: '*',
      type: 'operation',
      color: '#FF8E2C',
    },
  ],
  [
    {
      name: 'ln',
      color: '#626364',
      type: 'function',
      onClick: (value) => {
        return { visible: `ln(${value})`, hidden: `ln(${value})` };
      },
    },
    {
      name: <SubWidget text={'log'} value={'10'} />,
      color: '#626364',
      type: 'function',
      onClick: (value) => {
        return {
          visible: `log(${value})`,
          hidden: <SubWidget text={'log'} value={value} />,
        };
      },
    },

    {
      name: '4',
      type: 'basic',
      value: 4,
      color: '#7C7C7D',
    },
    {
      name: '5',
      type: 'basic',
      value: 5,
      color: '#7C7C7D',
    },
    {
      name: '6',
      type: 'basic',
      value: 6,
      color: '#7C7C7D',
    },
    {
      name: '-',
      value: '-',
      type: 'operation',
      color: '#FF8E2C',
    },
  ],
  [
    {
      name: 'e',
      type: 'basic',
      color: '#626364',
      value: 3.14,
    },
    {
      name: <SupWidget text={'x'} value={'2'} />,
      color: '#626364',
      type: 'function',
      onClick: (value) => {
        return {
          visible: `pow(${value},2)`,
          hidden: <SupWidget text={value} value={'2'} />,
        };
      },
    },
    {
      name: '1',
      type: 'basic',
      value: 1,
      color: '#7C7C7D',
    },
    {
      name: '2',
      type: 'basic',
      value: 2,
      color: '#7C7C7D',
    },
    {
      name: '3',
      type: 'basic',
      value: 3,
      color: '#7C7C7D',
    },
    {
      name: '+',
      value: '+',
      type: 'operation',
      color: '#FF8E2C',
    },
  ],
  [
    {
      color: '#626364',
      name: 'π',
      type: 'basic',
      value: 3.14,
    },
    {
      name: <SupWidget text={'x'} value={'3'} />,
      color: '#626364',
      type: 'function',
      onClick: (value) => {
        return {
          visible: `pow(${value}, 3)`,
          hidden: <SupWidget text={value} value={'3'} />,
        };
      },
    },

    {
      name: '0',
      type: 'basic',
      flex: 2,
      value: 0,
      color: '#7C7C7D',
    },
    {
      name: '.',
      type: 'basic',
      value: '.',
      color: '#7C7C7D',
    },
    {
      name: '=',
      value: '=',
      type: 'sum',
      color: '#FF8E2C',
    },
  ],
];

export default function App() {
  const [dimensions, setDimensions] = useState({ window, screen });
  const [calculatorValue, setCalculatorValue] = useState('0');
  const [isLastAction, setIsLastAction] = useState(false);
  const [currentValue, setCurrentValue] = useState('');
  const [expression, setExpression] = useState('');

  useEffect(() => {
    const subscription = Dimensions.addEventListener(
      'change',
      ({ window, screen }) => {
        setDimensions({ window, screen });
      }
    );
    return () => subscription?.remove();
  });

  const renderButtons = () => {
    const layouts = calculatorItems.map((row, index) => {
      const rowItem = row.map((columnItem, buttonIndex) => {
        return (
          <CalculatorActionButton
            value={columnItem.name}
            color={columnItem.color}
            flex={columnItem.flex}
            onPressed={() => handleCalculatorButtonClick(columnItem)}
          />
        );
      });

      return (
        <View style={styles.calculatorInputRow} key={'row-' + index}>
          {rowItem}{' '}
        </View>
      );
    });

    return layouts;
  };

  const handleCalculatorButtonClick = (item) => {
    let newValue = calculatorValue;
    let newCurrentValue = currentValue;
    if (newValue === '0') {
      newCurrentValue = '';
      newValue = '';
    }
    if (item.type === 'basic') {
      setIsLastAction(false);
      setCalculatorValue(newValue + item.name);
      newCurrentValue = newCurrentValue + item.name;
    } else if (item.type === 'operation') {
      if (isLastAction) {
        newValue = calculatorValue.slice(0, calculatorValue.length - 1);
      }
      setCalculatorValue(newValue + item.value);
      newCurrentValue = '';
      setIsLastAction(true);
    } else if (item.type === 'function') {
      newValue = newValue.slice(0, newValue.length - newCurrentValue.length);
      setCalculatorValue(newValue + item.onClick(newCurrentValue)['visible']);
    } else if (item.type === 'clear') {
      newCurrentValue = '';
      setCalculatorValue('0');
    } else if (item.type === 'sum') {
      handleCalculatorValueSum();
    }

    setCurrentValue(newCurrentValue);
  };

  const handleCalculatorValueSum = () => {
    let sumValue = calculatorValue;
    setCalculatorValue(math.eval(sumValue));
  };

  const getFontSize = () => {
    const calculatorValueLength = calculatorValue.length;
    if (calculatorValueLength > 50) {
      return 15;
    } else if (calculatorValueLength > 40) {
      return 20;
    } else if (calculatorValueLength > 20) {
      return 30;
    } else if (calculatorValueLength > 2) {
      return 50;
    } else {
      return 80;
    }
  };

  return (
    <View style={styles.container}>
      <View style={styles.screenContainer}>
        <Text
          style={[
            styles.screenText,
            {
              fontSize: getFontSize(),
            },
          ]}>
          {calculatorValue.toString()}
        </Text>
      </View>

      <View style={styles.buttonsContainer}>{renderButtons()}</View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    height: '100%',
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
