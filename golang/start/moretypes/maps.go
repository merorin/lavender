package main

import "fmt"

const name string = "Bell Labs"

var m map[string]Vertex

func main() {
	m = make(map[string]Vertex)
	m[name] = Vertex{
		32.12145, -23.12312,
	}
	fmt.Println(m[name])
}

type Vertex struct {
	Lat, Long float64
}
