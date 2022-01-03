import React, {useCallback, useEffect, useState} from 'react';
import {FlatList, RefreshControl, StyleSheet, Text, View} from 'react-native';
import {Card} from "react-native-paper";
import Pressable from "react-native/Libraries/Components/Pressable/Pressable";
import quizMock from "../../assets/mocks/quizMock";
import QuizRepository from "../../repositories/quiz-repository";

const wait = (timeout) => {
    return new Promise(resolve => setTimeout(resolve, timeout));
}

const HomeScreen = ({navigation}) => {
    const [refreshing, setRefreshing] = useState(false);
    const [resultsData, setResultsData] = useState([]);

    const onRefresh = useCallback(() => {
        setRefreshing(true);
        fetchData().then(() => setRefreshing(false));
    }, []);

    const fetchData = async () => {
        const result = await QuizRepository.getAllTests();
        setResultsData(result);
    }

    useEffect(() => {
        fetchData().then();
    }, []);

    const renderItem = ({item}) => (
        <Pressable onPress={() => navigation.navigate('Quiz', { item: item },)}>
            <Card style={styles.itemCard}>
                <Text style={styles.itemTitle}>{item.name}</Text>
                <Text style={styles.itemDescription}>{item.description}</Text>
                <Text style={styles.itemTags}>Tagi: {item.tags.join(', ')}</Text>
            </Card>
        </Pressable>
    );

    return (
        <View>
            <FlatList
                contentContainerStyle={styles.scrollView}
                data={resultsData}
                renderItem={renderItem}
                refreshControl={
                    <RefreshControl
                        refreshing={refreshing}
                        onRefresh={onRefresh}
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
    itemTitle: {
        fontFamily: 'Lobster',
        fontSize: 20,
    },
    itemDescription: {
        fontStyle: 'italic',
    },
    itemTags: {
        marginTop: 20,
        fontWeight: 'bold',
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
