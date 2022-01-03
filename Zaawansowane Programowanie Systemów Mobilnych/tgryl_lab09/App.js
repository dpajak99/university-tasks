import React, {useState} from 'react';
import {NavigationContainer} from '@react-navigation/native';
import {createNativeStackNavigator} from '@react-navigation/native-stack';
import {createDrawerNavigator, DrawerItemList} from '@react-navigation/drawer';
import HomeScreen from "./components/screens/HomeScreen";
import ResultsScreen from "./components/screens/ResultsScreen";
import QuizScreen from "./components/screens/QuizScreen";
import {AsyncStorage, Image, StyleSheet, Text, View} from 'react-native';
import TermsScreen from "./components/screens/TermsScreen";
import * as Font from 'expo-font';
import AppLoadingPlaceholder from "expo/build/launch/AppLoadingPlaceholder";

const CustomDrawer = (props) => {
    return (
        <View style={styles.app}>
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
            }}
            drawerContent={props => <CustomDrawer {...props} />}
        >
            <Drawer.Screen name="Menu" component={HomeScreen}/>
            <Drawer.Screen name="Results" component={ResultsScreen}/>
        </Drawer.Navigator>
    );
}

const Stack = createNativeStackNavigator();
const Drawer = createDrawerNavigator();

export default function App () {
    const [isLoadingComplete, setLoadingComplete] = useState(false);

    async function loadResourcesAsync() {
        await Promise.all([
            Font.loadAsync({
                'Lobster': require('./assets/fonts/Lobster.ttf'),
                'OpenSans': require('./assets/fonts/OpenSans.ttf'),
            })
        ]);
        setLoadingComplete(true);
    }

    loadResourcesAsync().then();

    const getInitialPage = async () => {
        const showTerms = await AsyncStorage.getItem('terms');
        if (showTerms) return 'Terms';
        return 'Home';
    }



    return (
        <NavigationContainer>
            <Stack.Navigator initialRouteName={getInitialPage}>
                <Stack.Screen
                    name="Root"
                    component={DrawerNavigator}
                    options={{headerShown: false}}
                />
                <Stack.Screen name="Home" component={HomeScreen}/>
                <Stack.Screen name="Quiz" component={QuizScreen}/>
                <Stack.Screen name="Results" component={ResultsScreen}/>
                <Stack.Screen name="Terms" component={TermsScreen}/>
            </Stack.Navigator>
        </NavigationContainer>
    );
}

const styles = StyleSheet.create({
    app: {
      fontFamily: 'OpenSans',
    },
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
