import React, {useCallback, useState} from 'react';
import {FlatList, RefreshControl, StyleSheet, Text, View} from 'react-native';
import {Card} from "react-native-paper";
import Pressable from "react-native/Libraries/Components/Pressable/Pressable";
import quizMock from "../../assets/mocks/quizMock";

const wait = (timeout) => {
    return new Promise(resolve => setTimeout(resolve, timeout));
}

const HomeScreen = ({navigation}) => {
    const [refreshing, setRefreshing] = useState(false);

    const onRefresh = useCallback(() => {
        setRefreshing(true);
        wait(2000).then(() => setRefreshing(false));
    }, []);

    const renderItem = ({item}) => (
        <Pressable onPress={() => navigation.navigate('Quiz', { item: item },)}>
            <Card style={styles.itemCard}>
                <Text>{item.name}</Text>
                <Text>{item.tags}</Text>
                <Text>{item.description}</Text>
            </Card>
        </Pressable>
    );

    return (
        <View>
            <FlatList
                contentContainerStyle={styles.scrollView}
                data={quizMock}
                renderItem={renderItem}
                refreshControl={
                    <RefreshControl
                        refreshing={refreshing}
                        // onRefresh={onRefresh}
                    />
                }
            />
            <Card style={styles.card}>
                <Text style={styles.heading}>Get to know your ranking result</Text>
                <Pressable style={styles.button} onPress={() => navigation.navigate('Results')}>
                    <Text style={styles.buttonTitle}>Check!</Text>
                </Pressable>
            </Card>
        </View>
    );
};

const styles = StyleSheet.create({
    card: {
        textAlign: 'center',
        margin: 15,
        padding: 15,
    },
    heading: {
        textAlign: 'center',
        marginBottom: 15,
    },
    buttonTitle: {
        textAlign: 'center',
    },
    button: {
        textAlign: 'center',
        paddingTop: 15,
        paddingBottom: 15,
        paddingStart: 20,
        paddingEnd: 20,
        backgroundColor: '#eeeeee'
    },
    itemCard: {
        margin: 5,
        padding: 10,
    },
});

export default HomeScreen;
