package main

import (
	"fmt"
	"math"
)

func main() {
	v := Vertex{3, 4}
	fmt.Println(Abs(v))
}

func Abs(v Vertex) float64 {
	return math.Sqrt(v.X*v.X + v.Y*v.Y)
}

type Vertex struct {
	X, Y float64
}
