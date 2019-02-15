package main

import "fmt"

func main() {
	i, j := 42, 2701

	p := &i
	fmt.Println(*p) // should be 42
	fmt.Println(p)  // should be the address in memory

	*p = 21
	fmt.Println(i) // should be 21

	p = &j
	*p = *p / 37
	fmt.Println(j) // should be 73
}
