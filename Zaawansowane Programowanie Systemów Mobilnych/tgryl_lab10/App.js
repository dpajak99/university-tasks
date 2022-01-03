import React, {useState} from 'react';
import {NavigationContainer} from '@react-navigation/native';
import {createNativeStackNavigator} from '@react-navigation/native-stack';
import {createDrawerNavigator, DrawerItemList} from '@react-navigation/drawer';
import HomeScreen from "./components/screens/HomeScreen";
import ResultsScreen from "./components/screens/ResultsScreen";
import QuizScreen from "./components/screens/QuizScreen";
import {AsyncStorage, Button, Image, StyleSheet, Text, View} from 'react-native';
import TermsScreen from "./components/screens/TermsScreen";
import * as Font from 'expo-font';
import AppLoadingPlaceholder from "expo/build/launch/AppLoadingPlaceholder";
import NetInfo from "@react-native-community/netinfo";
import Toast from "react-native/Libraries/Components/ToastAndroid/ToastAndroid";
import QuizRepository from "./repositories/quiz-repository";
import _ from "lodash";

const CustomDrawer = (props) => {
    return (
        <View style={styles.app}>
            <View style={styles.header}>
                <Text>Quiz App</Text>
                <Image source={require('./assets/logo.png')} style={styles.logo}/>
                <View style={styles.sizedBox}/>
                <Button onPress={fetchAllDatabase} title={'Pobierz bazę danych'}/>
                <View style={styles.sizedBox}/>
                <Button onPress={() => getRandomTest(props)} title={'Losuj test'}/>
                <View style={styles.sizedBox}/>
            </View>
            <DrawerItemList {...props} />
        </View>
    );
};

const fetchAllDatabase = async () => {
    Toast.show('Pobieram bazę danych', 1000);
    const results = await QuizRepository.getResults();
    const tests = await QuizRepository.getAllTests();
    let testsDetails = {};
    for(let i = 0; i < tests.length; i++ ) {
        testsDetails[tests[i].id] = await QuizRepository.getTestDetails(tests[i].id);
    }

    await AsyncStorage.setItem('storage-results', JSON.stringify(results));
    await AsyncStorage.setItem('storage-tests', JSON.stringify(tests));
    await AsyncStorage.setItem('storage-tests-details', JSON.stringify(testsDetails));
    Toast.show('Baza została pobrana', 1000);
}

const getRandomTest = async (props) => {
    const test = await fetchRandomTests();
    props.navigation.navigate('Quiz', { item: test });
}

const fetchRandomTests = async () => {
    try {
        const result = await QuizRepository.getAllTests();
        return _.shuffle(result)[0];
    } catch(error) {
        const cachedResult = JSON.parse(await AsyncStorage.getItem('storage-tests'));
        return _.shuffle(cachedResult)[0];
    }
}

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
    const [isConnectedToNetwork, setIsConnectedToNetwork] = useState(false);


    const setUpInitial = () => {
        loadResourcesAsync().then();
        fetchAllDatabase().then();
        const unsubscribe = NetInfo.addEventListener(state => {
            if( state.isConnected !== isConnectedToNetwork ) {
                setIsConnectedToNetwork(state.isConnected);
                if( !state.isConnected ) {
                    Toast.show('No network connection', 1000);
                } else {
                    Toast.show('Connected to network', 1000);
                }
            }
        });

    }

    async function loadResourcesAsync() {
        await Promise.all([
            Font.loadAsync({
                'Lobster': require('./assets/fonts/Lobster.ttf'),
                'OpenSans': require('./assets/fonts/OpenSans.ttf'),
            })
        ]);
        setLoadingComplete(true);
    }

    setUpInitial();

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
        height: 400,
        justifyContent: 'center',
        alignItems: 'center',
    },
    logo: {
        height: 80,
        width: 80,
    },
    sizedBox: {
        height: 20,
    }
});
