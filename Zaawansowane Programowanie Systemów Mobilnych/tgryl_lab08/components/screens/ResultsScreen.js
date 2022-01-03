import React, {useCallback, useState} from 'react';
import {FlatList, View, Text, StyleSheet, RefreshControl} from 'react-native';
import resultsMocks from "../../assets/mocks/resultsMock";
import {Card} from "react-native-paper";

const wait = (timeout) => {
    return new Promise(resolve => setTimeout(resolve, timeout));
}

const Item = ({nick, score, type, date}) => (
    <Card style={styles.itemCard}>
        <Text>{nick}</Text>
        <Text>{score}</Text>
        <Text>{type}</Text>
        <Text>{date}</Text>
    </Card>
);

const ResultScreen = ({navigation}) => {
    const [refreshing, setRefreshing] = useState(false);

    const onRefresh = useCallback(() => {
        setRefreshing(true);
        wait(2000).then(() => setRefreshing(false));
    }, []);

    const renderItem = ({item}) => (
        <Item
            key={item.id}
            nick={item.nick}
            score={item.score}
            type={item.type}
            date={item.date}
        />
    );

    return (
        <View>
            <FlatList
                contentContainerStyle={styles.scrollView}
                data={resultsMocks}
                renderItem={renderItem}
                refreshControl={
                    <RefreshControl
                        refreshing={refreshing}
                        // onRefresh={onRefresh}
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
});

export default ResultScreen;
