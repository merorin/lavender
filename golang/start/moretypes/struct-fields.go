package main

import "fmt"

func main() {
	v := Vertex{1, 2}
	v.X = 4
	fmt.Println(v)
}

type Vertex struct {
	X int
	Y int
}
