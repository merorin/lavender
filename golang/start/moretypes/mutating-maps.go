package main

import "fmt"

const key string = "Answer"

func main() {
	m := make(map[string]int)

	m[key] = 42
	fmt.Println("The value:", m[key])

	m[key] = 48
	fmt.Println("The value:", m[key])

	v, ok := m[key]
	fmt.Println("The value:", v, "Present?", ok)

	delete(m, key)
	fmt.Println("The value:", m[key])

	nv, res := m[key]
	fmt.Println("The value:", nv, "Present?", res)
}
