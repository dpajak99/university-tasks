// Zadanie 1
let complex1 = {real: 1, imaginary: 2}
let complex2 = {real: 3, imaginary: 4}
let complexSum = 0;

function addComplex(a, b) {
    let real = a.real + b.real;
    let imaginary = a.imaginary + b.imaginary;
    return {real: real, imaginary: imaginary};
}

function subtractComplex(a, b) {
    let real = a.real - b.real;
    let imaginary = a.imaginary - b.imaginary;
    return {real: real, imaginary: imaginary};
}

function calcModuloComplex(a) {
    return Math.sqrt((Math.pow(a.real, 2)) + (Math.pow(a.imaginary, 2)))
}

function complexToString(a) {
    console.log("real: " + a.real + " imaginary: " + a.imaginary)
}

complexSum = addComplex(complex1, complex2);
complexToString(complexSum);
complexSum = subtractComplex(complexSum, complex1);
complexToString(complexSum);
let moduloComplex = calcModuloComplex(complexSum);
console.log(moduloComplex);

// ZADANIE 2
const students = ["Olek", "Janek", "Stefan", "Tymek", "Sławek"];

function selectRandomFromArray(array) {
    let number = Math.floor(Math.random() * (array.length - 1))
    return array[number];
}

let person;
person = selectRandomFromArray(students);
console.log(`Random person: ${person}`);


// ZADANIE 3
function drawRandomInteger(randomsCount, intervalDuration) {
    let randomIntervalGenerator = setInterval(function () {
        if (randomsCount > 0) {
            let randomInteger = Math.floor(Math.random() * (30));
            console.log(randomInteger);
            randomsCount--;
        } else {
            clearTimeout(randomIntervalGenerator);
        }
    }, intervalDuration);
}

function simulateMockedRequest(body) {
    setInterval(function () {
        console.log("Witam " + body.name);
    }, 3000);
}

drawRandomInteger(3,1000);
simulateMockedRequest({name: "Dominik"});