import React, {useState} from 'react';
import {AsyncStorage, Button, StyleSheet, Text, View} from 'react-native';
import {AntDesign} from "@expo/vector-icons";


const TermsScreen = ({navigation}) => {

    const [visible, setIsVisible] = useState(true);

    const handleSubmit = async () => {
        setIsVisible(false);
        const terms = { visible: visible }
        await AsyncStorage.setItem('terms', JSON.stringify(terms))
        props.navigation.navigate({ name: 'Home' });
    }

    return (
        <View style={styles.page}>
            <Text>Regulamin</Text>
            <View style={styles.sizedBox}/>
            <Text>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas mollis urna at
                sem condimentum viverra. Nulla lobortis elit eu erat tincidunt, nec laoreet sapien
                vulputate. Proin posuere nunc non lacus aliquet volutpat. Nam ullamcorper dictum
                enim, ac sagittis diam sollicitudin quis. Ut finibus viverra augue non pharetra. Ut
                sit amet nisl non dui congue efficitur. Donec sit amet lectus at est gravida
                scelerisque. Duis quis pretium leo, pharetra porttitor lectus.
            </Text>
            <View style={styles.sizedBox}/>
            <View style={styles.sizedBox}/>
            <Button title={"Akceptuje warunki korzystania"} onPress={handleSubmit}/>
        </View>
    );
};

const styles = StyleSheet.create({
    page: {
        paddingTop: 15,
        paddingBottom: 15,
        paddingStart: 15,
        paddingEnd: 15,
    },
    sizedBox: {
        height: 15,
    }
});

export default TermsScreen;
