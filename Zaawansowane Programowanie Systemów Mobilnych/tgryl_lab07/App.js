import React, {Component} from 'react';
import {NavigationContainer} from '@react-navigation/native';
import {createNativeStackNavigator} from '@react-navigation/native-stack';
import {createDrawerNavigator, DrawerItemList} from '@react-navigation/drawer';
import HomeScreen from "./components/screens/HomeScreen";
import ResultsScreen from "./components/screens/ResultsScreen";
import QuizScreen from "./components/screens/QuizScreen";
import {StyleSheet, Text, View, Image} from 'react-native';


const CustomDrawer = (props) => {
    return (
        <View>
            <View style={styles.header}>
                <Text>Quiz App</Text>
                <Image source={require('./assets/logo.png')} style={styles.logo}/>
            </View>
            <DrawerItemList {...props} />
        </View>
    );
};

function DrawerNavigator() {
    return (
        <Drawer.Navigator
            screenOptions={{
                headerShown: true,
                headerStyle: {
                    backgroundColor: 'transparent',
                    elevation: 0,
                    shadowOpacity: 0,
                },
                headerTitle: '',
            }}
            drawerContent={props => <CustomDrawer {...props} />}
        >
            <Drawer.Screen name="Menu" component={HomeScreen}/>
            <Drawer.Screen name="Result" component={ResultsScreen}/>
        </Drawer.Navigator>
    );
}

const Stack = createNativeStackNavigator();
const Drawer = createDrawerNavigator();

export default class App extends Component<{}> {
    render() {
        return (
            <NavigationContainer>
                <Stack.Navigator>
                    <Stack.Screen
                        name="Root"
                        component={DrawerNavigator}
                        options={{headerShown: false}}
                    />
                    <Stack.Screen name="Home Page" component={HomeScreen}/>
                    <Stack.Screen name="Quiz" component={QuizScreen}/>
                    <Stack.Screen name="Results" component={ResultsScreen}/>
                </Stack.Navigator>
            </NavigationContainer>
        );
    }
}

const styles = StyleSheet.create({
    header: {
        height: 200,
        justifyContent: 'center',
        alignItems: 'center',
    },
    logo: {
        height: 80,
        width: 80,
    }
});
