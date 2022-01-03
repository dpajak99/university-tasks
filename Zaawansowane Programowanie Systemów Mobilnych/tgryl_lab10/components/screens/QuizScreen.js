import React, {useEffect, useState} from 'react';
import {AsyncStorage, FlatList, Pressable, StyleSheet, Text, View} from 'react-native';
import {ProgressBar} from "react-native-paper";
import QuizRepository from "../../repositories/quiz-repository";
import _ from "lodash";

const wait = (timeout) => {
    return new Promise(resolve => setTimeout(resolve, timeout));
}

const QuizScreen = (props: any) => {
    const [currentQuiz, setCurrentQuiz] = useState(props.route.params.item);
    const [currentQuestion, setCurrentQuestion] = useState();
    const [currentQuestionIndex, setCurrentQuestionIndex] = useState(1);
    const [points, setPoints] = useState(0);

    const [selectedAnswer, setSelectedAnswer] = useState(null);
    const [correctAnswer, setCorrectAnswer] = useState(null);

    const [seconds, setSeconds] = useState(30);
    const [isEnd, setIsEnd] = useState(false);

    const [quizData, setQuizData] = useState([]);
    const [isLoading, setIsLoading] = useState(false);
    const [initialLoading, setInitialLoading] = useState(true);

    const fetchData = async () => {
        setInitialLoading(true);
        let result = {};
        try {
            result = await QuizRepository.getTestDetails(currentQuiz.id);
            setQuizData(result);
        } catch(error) {
            console.error(error);
            result = JSON.parse(await AsyncStorage.getItem('storage-tests-details'))[currentQuiz.id];
            setQuizData(_.shuffle(result));
        }
        result.tasks = _.shuffle(result.tasks);
        let currQuestion = result.tasks[0];
        currQuestion.answers = _.shuffle(currQuestion.answers);
        setCurrentQuestion(currQuestion);
        setSeconds(currQuestion.duration);
        setInitialLoading(false);
    }

    const sendResult = async () => {
        await QuizRepository.sendResults({
           'nick': 'dpajak99',
            'score': points,
            'total': quizData.tasks.length,
            'type': currentQuiz.tags.join(','),
        });
    }

    useEffect(async () => {
        await fetchData();
    }, []);

    useEffect(() => {
        const interval = setInterval(() => {
            if (seconds === 0) {
                goToNextQuestion().then();
            } else {
                setSeconds(seconds - 1);
            }
        }, 1000);
        return () => clearInterval(interval);
    }, [seconds]);

    const handleQuestionPress = async (question) => {
        await goToNextQuestion(question);
    }
    const goToNextQuestion = async (question) => {
        if (!isLoading) {
            setIsLoading(true);
            setCorrectAnswer(currentQuestion.answers.filter((e) => e.isCorrect)[0].content);
            if (question != null) {
                setSelectedAnswer(question.content);
                if (question.isCorrect) {
                    setPoints(points + 1);
                }
            }

            await wait(500);
            setIsLoading(false);
            if (currentQuestionIndex < quizData.tasks.length) {
                setCurrentQuestionIndex(currentQuestionIndex + 1);
                setCurrentQuestion(quizData.tasks[currentQuestionIndex]);
                currentQuestion.answers = _.shuffle(currentQuestion.answers);
                setSeconds(currentQuestion.duration);
            } else if(!isEnd){
                setIsEnd(true);
                await sendResult();
            }
            setCorrectAnswer(null);
            setSelectedAnswer(null);
        }
    }

    const renderItem = (item) => {
        return <Pressable
            key={item.id}
            style={[styles.answer, item.content === correctAnswer ? styles.correctAnswer : item.content === selectedAnswer ? styles.selectedAnswer : styles.answer]}
            onPress={() => handleQuestionPress(item)}
        >
            <Text>{item.content}</Text>
        </Pressable>
    }

    if (isEnd) {
        return <View style={styles.pointsPage}>
            <Text style={styles.finishText}>Test finished</Text>
            <Text>Your points: {points}</Text>
        </View>
    }
    if (initialLoading) {
        return (
            <Text>Loading...</Text>
        );
    }
    return (
        <View style={styles.page}>
            <View style={styles.header}>
                <Text>Question {currentQuestionIndex} of {quizData.tasks.length}</Text>
                <Text>Time: {seconds} sec</Text>
            </View>
            <ProgressBar progress={(seconds / currentQuestion.duration)}/>
            <Text>{quizData.tasks.name}</Text>

            <Text style={styles.questionText}>{currentQuestion.question}</Text>
            <FlatList
                data={currentQuestion.answers}
                keyExtractor={item => `${item.id}${Math.random()}`}
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
    questionText: {
        fontFamily: 'Lobster',
        textAlign: 'center',
        fontSize: 18,
        marginTop: 30,
        marginBottom: 30,
    },
    finishText: {
        fontFamily: 'Lobster',
        textAlign: 'center',
        fontSize: 35,
        marginBottom: 30,
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
