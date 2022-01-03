const serverUrl = 'https://tgryl.pl';

class QuizRepository {
    getResults() {
        return fetch(`${serverUrl}/quiz/results`, {
            method: 'GET'
        })
            .then((response) => response.json())
            .then((json) => {
                return json;
            }).catch((error) => {
                console.error(error);
            });
    }

    sendResults(data) {
        return fetch(`${serverUrl}/quiz/result`, {
            method: 'POST',
            body: JSON.stringify(data),
        }).then((response) => response.json())
            .then((json) => {
                console.log('Succesfull');
                return json;
            }).catch((error) => {
                console.error(error);
            });
    }

    getAllTests() {
        return fetch(`${serverUrl}/quiz/tests`, {
            method: 'GET'
        }).then((response) => response.json())
            .then((json) => {
                return json;
            }).catch((error) => {
                console.error(error);
            });
    }

    getTestDetails(id) {
        return fetch(`${serverUrl}/quiz/test/${id}`, {
            method: 'GET'
        }).then((response) => response.json())
            .then((json) => {
                return json;
            }).catch((error) => {
                console.error(error);
            });
    }
}

export default new QuizRepository();