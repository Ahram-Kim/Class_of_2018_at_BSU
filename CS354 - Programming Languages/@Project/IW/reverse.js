let  str = "HeLlO ThErE ThiS iS Js";
let num  = str.length;


function backwards(str){
    var lstr = str.toLowerCase();
    var rev = lstr.split('',num);
    rev.reverse();
    let finalStr = rev.join('');
    console.log(finalStr);
    }
    
console.log(str);
backwards(str);
