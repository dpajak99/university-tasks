import React, {useCallback, useEffect, useState} from 'react';
import {FlatList, View, Text, StyleSheet, RefreshControl, AsyncStorage} from 'react-native';
import {Card} from "react-native-paper";
import QuizRepository from "../../repositories/quiz-repository";

const ResultScreen = ({navigation}) => {
    const [refreshing, setRefreshing] = useState(false);
    const [resultsData, setResultsData] = useState([]);

    const onRefresh = useCallback(() => {
        setRefreshing(true);
        fetchData().then(() => setRefreshing(false));
    }, []);

    const fetchData = async () => {
        const cachedResult = JSON.parse(await AsyncStorage.getItem('storage-results'));
        setResultsData(cachedResult);
        try {
            const result = await QuizRepository.getResults();
            setResultsData(result);
        } catch (err) {
            console.error(err);
        }
    }

    useEffect(() => {
        fetchData().then();
    }, []);

    const renderItem = ({item}) => (
        <Card style={styles.itemCard}>
            <Text style={styles.itemNick}>{item.nick}</Text>
            <Text>Wynik: {item.score}/{item.total}</Text>
            <Text>Kategoria: {item.type}</Text>
            <Text>Data utworzenia: {item.createdOn}</Text>
        </Card>
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
        </View>
    );
};

const styles = StyleSheet.create({
    itemCard: {
        margin: 5,
        padding: 10,
    },
    itemNick: {
        fontFamily: 'Lobster',
        fontSize: 20,
    }
});

export default ResultScreen;
