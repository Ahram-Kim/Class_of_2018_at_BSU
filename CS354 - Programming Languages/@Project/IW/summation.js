function sumOfList(list) {
    var sum = 0;

    if(typeof list !== 'undefined' && list !== null) {
        for(var index = 0; index < list.length; index++) {
            sum += list[index];
        }
    }

    return sum;
}

var mySum = sumOfList(list);
console.log(mySum);