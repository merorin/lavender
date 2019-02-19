package main

import (
	"fmt"
	"math"
)

func main() {
	v := &Vertex{3, 4}
	v.Scale(5)
	fmt.Println(v.Abs())

	ver := Vertex{5, 12}
	ver.Scale(13)
	fmt.Println(ver.Abs())
}

func (v *Vertex) Scale(f float64) {
	v.X = v.X * f
	v.Y = v.Y * f
}

func (v *Vertex) Abs() float64 {
	return math.Sqrt(v.X*v.X + v.Y*v.Y)
}

type Vertex struct {
	X, Y float64
}
