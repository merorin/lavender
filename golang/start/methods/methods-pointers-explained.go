package main

import (
	"fmt"
	"math"
)

func main() {
	v := Vertex{3, 4}
	Scale(&v, 5)
	fmt.Println(Abs(v))
}

func Abs(v Vertex) float64 {
	return math.Sqrt(v.X*v.X + v.Y*v.Y)
}

func Scale(v *Vertex, f float64) {
	v.X = v.X * f
	v.Y = v.Y * f
}

type Vertex struct {
	X, Y float64
}
