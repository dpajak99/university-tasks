function addToArray(array, positionStatus, item) {
    if(positionStatus === 0) {
        array.unshift(item);
    } else {
        array.push(item);
    }
}

const calcSquareArea = (side) => {
    return side*side
}

console.log("Pierwszy skrypt - działa");

const tab = [1,2,3,4,5,6];
tab.pop();

console.log(`Długość tablicy ${tab} jest równa ${tab.length}`);

addToArray(tab, 0, 8);
console.log(tab);
addToArray(tab, 1, 9);
console.log(tab);

const text = "1.2.3.4.5.6.7.8.9";
const textArray = text.split(".");
const mappedArray = textArray.map(function square(n) {
    return n * n;
});
const finalArray = mappedArray.join(".");
console.log(finalArray);


console.log(calcSquareArea(5));
console.log(calcSquareArea(50));
