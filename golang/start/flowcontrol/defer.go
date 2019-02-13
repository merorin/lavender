package main

import "fmt"

func main() {
	defer fmt.Println("world")

	fmt.Println("Hello")

	outside()
	defer fmt.Println("WOW")
}

func outside() {
	inner()
	fmt.Println("outside")
}

func inner() {
	defer fmt.Println("inner")
	defer fmt.Println("inner2")

	fmt.Println("Right")
}
