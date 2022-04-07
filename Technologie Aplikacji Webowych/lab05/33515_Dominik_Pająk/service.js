function isEven(array) {
    let oddsArray = [];
    for (let i = 0; i < array.length; i++) {
        if (array[i] % 2 !== 0) {
            oddsArray.push(array[i])
        }
    }
    return oddsArray;
}


module.exports = {
    isEven: isEven
}
