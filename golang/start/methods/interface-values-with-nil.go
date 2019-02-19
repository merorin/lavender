package main

import (
	"fmt"
	"math"
)

func main() {
	var i I

	var t *T

	i = t
	describe(i)
	i.M()

	i = F(math.Pi)
	describe(i)
	i.M()
}

type I interface {
	M()
}

type T struct {
	S string
}

type F float64

func (t *T) M() {
	if t == nil {
		fmt.Println("<nil>")
		return
	}
	fmt.Println(t.S)
}

func (f F) M() {
	fmt.Println(f)
}

func describe(i I) {
	fmt.Printf("(%v, %T)\n", i, i)
}
