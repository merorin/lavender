package main

import "fmt"

/**
array must be defined together with a initial length, slice needn't
when passed as a parameter, array will be duplicated while slice won't
slice behaves like a ArrayList
*/
func main() {
	p := []int{2, 4, 5, 11, 13}
	fmt.Println("p ==", p)

	for i := 0; i < len(p); i++ {
		fmt.Printf("p[%d] == %d\n", i, p[i])
	}
}
