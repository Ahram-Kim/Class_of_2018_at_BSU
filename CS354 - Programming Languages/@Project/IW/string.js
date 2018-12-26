var str = "C#";
var first = "";
var second = "";

    if(str.search("C") >= 0)
    {
        first = str.replace("C", "Java");
        if(first.search("#") >= 0)
            {
               second = first.replace("#", "Script");
            }          
    }
//var res = first.concat(second);
console.log(second);