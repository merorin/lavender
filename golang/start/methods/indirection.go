package main

import "fmt"

func main() {
	v := Vertex{3, 4}
	v.Scale(2)
	ScaleFunc(&v, 5)

	p := &Vertex{5, 12}
	p.Scale(13)
	ScaleFunc(p, 10)

	fmt.Println(v, p)
}

func (v *Vertex) Scale(f float64) {
	v.X = v.X * f
	v.Y = v.Y * f
}

func ScaleFunc(v *Vertex, f float64) {
	v.X = v.X * f
	v.Y = v.Y * f
}

type Vertex struct {
	X, Y float64
}
