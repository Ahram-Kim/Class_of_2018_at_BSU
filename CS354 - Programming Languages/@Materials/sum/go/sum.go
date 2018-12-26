/* Go sum program */

package main

import ("fmt")

func sum(seq []int) int {
    s:=0
    for _, v:=range seq {
        s+=v
    }
    return s;
}

func main() {
    seq:=[]int{5,6,1,8,3,7}
    fmt.Printf("%d\n",sum(seq))
}

