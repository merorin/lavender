package main

import "fmt"

func main() {
	fmt.Println(Vertex{X: 1, Y: 2})
}

type Vertex struct {
	X int
	Y int
}
