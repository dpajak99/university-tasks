import React, {useEffect, useState} from 'react';
import {FlatList, StyleSheet, Text, View, Pressable} from 'react-native';
import {ProgressBar} from "react-native-paper";

const wait = (timeout) => {
    return new Promise(resolve => setTimeout(resolve, timeout));
}

const QuizScreen = (props: any) => {
    const [currentQuestion, setCurrentQuestion] = useState(props.route.params.item.tasks[0]);
    const [currentQuestionIndex, setCurrentQuestionIndex] = useState(1);
    const [points, setPoints] = useState(0);

    const [selectedAnswer, setSelectedAnswer] = useState(null);
    const [correctAnswer, setCorrectAnswer] = useState(null);

    const [seconds, setSeconds] = useState(currentQuestion.duration);
    const [isEnd, setIsEnd] = useState(false);

    let isLoading = false;

    useEffect(() => {
        const interval = setInterval(async () => {
            if( seconds === 0 ) {
                await goToNextQuestion();
            } else {
                setSeconds(seconds - 1);
            }
        }, 1000);
        return () => clearInterval(interval)
    }, [seconds])

    const handleQuestionPress = async (question) => {
        await goToNextQuestion(question);
    }
    const goToNextQuestion = async (question) => {
        if( !isLoading ) {
            isLoading = true;
            setCorrectAnswer(currentQuestion.answers.filter((e) => e.isCorrect)[0].content);
            if( question != null ) {
                setSelectedAnswer(question.content);
                if (question.isCorrect) {
                    setPoints(points + 1);
                }
            }

            await wait(500);
            isLoading = false;
            if (currentQuestionIndex < props.route.params.item.numberOfTasks) {
                setCurrentQuestionIndex(currentQuestionIndex + 1);
                setCurrentQuestion(props.route.params.item.tasks[currentQuestionIndex]);

                setSeconds(currentQuestion.duration);
            } else {
                setIsEnd(true);
            }
            setCorrectAnswer(null);
            setSelectedAnswer(null);
        }
    }

    const renderItem = (item) => {
        return <Pressable
            key={item.content}
            style={[styles.answer, item.content === correctAnswer ? styles.correctAnswer : item.content === selectedAnswer ? styles.selectedAnswer : styles.answer]}
            onPress={() => handleQuestionPress(item)}
        >
            <Text>{item.content}</Text>
        </Pressable>
    }

    if( isEnd ) {
        return <View style={styles.pointsPage}>
            <Text>Test finished</Text>
            <Text>Your points: {points}</Text>
        </View>
    }
    return (
        <View style={styles.page}>
            <View style={styles.header}>
                <Text>Question {currentQuestionIndex } of {props.route.params.item.numberOfTasks}</Text>
                <Text>Time: {seconds} sec</Text>
            </View>
            <ProgressBar progress={(seconds / currentQuestion.duration) }/>
            <Text>{props.route.params.item.name}</Text>

            <Text>{currentQuestion.question}</Text>
            <FlatList
                data={currentQuestion.answers}
                renderItem={(item) => renderItem(item.item)}
                numColumns={2}
            />
        </View>
    );
};

const styles = StyleSheet.create({
    page: {
      padding: 15,
    },
    pointsPage: {
        height: '100%',
        padding: 15,
        justifyContent: 'center',
        alignItems: 'center',
    },
    header: {
        flexDirection: 'row',
        justifyContent: 'space-between',
    },
    answer: {
        margin: 5,
        padding: 10,
        flex: 1,
        borderWidth: 1,
        textAlign: 'center',
        justifyContent: 'center',
        alignItems: 'center',
    },
    selectedAnswer: {
        backgroundColor: 'red',
    },
    correctAnswer: {
        backgroundColor: 'green',
    }
});

export default QuizScreen;
