/* Go sum program */

package main

import ("fmt")

func sum(seq []int, c chan int) {
    s:=0
    for _, v:=range seq {
        s+=v
    }
    c<-s;
}

func main() {
    seq:=[]int{5,6,1,8,3,7}
    c:=make(chan int)
    go sum(seq,c)
    fmt.Printf("%d\n",<-c)
}

