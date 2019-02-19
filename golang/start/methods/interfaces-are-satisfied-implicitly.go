package main

import "fmt"

func main() {
	var i I = T{"hello"}
	i.M()
}

type I interface {
	M()
}

type T struct {
	S string
}

func (t T) M() {
	fmt.Println(t.S)
}
