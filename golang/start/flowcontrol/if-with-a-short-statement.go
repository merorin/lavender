package main

import (
	"fmt"
	"math"
)

func main() {
	fmt.Println(
		pow(3, 2, 10),
		pow(3, 3, 20),
	)
}

func pow(x, n, limit float64) float64 {
	if v := math.Pow(x, n); v < limit {
		return v
	}
	// v can only be used in if block
	return limit
}
