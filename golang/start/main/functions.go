package main

import "fmt"

func main() {
	fmt.Println(add(7, 11))
}

func add(x int, y int) int {
	return x + y
}
