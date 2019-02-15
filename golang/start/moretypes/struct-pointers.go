package main

import "fmt"

func main() {
	v := Vertex{1, 2}
	p := &v
	p.X = 1e9
	fmt.Println(v)
}

type Vertex struct {
	X int
	Y int
}
