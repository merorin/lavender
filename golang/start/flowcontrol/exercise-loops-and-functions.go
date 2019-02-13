package main

import (
	"fmt"
	"math"
)

const MinimumDelta = 1.0e-6

func main() {
	fmt.Println(Sqrt(2))
	fmt.Println(math.Sqrt(2))
}

func Sqrt(x float64) float64 {
	return calculate(float64(2), x)
}

func calculate(z float64, x float64) float64 {
	var delta = x
	for delta > MinimumDelta {
		newValue := z - (z*z-x)/(2*z)
		delta = absolute(newValue - z)
		fmt.Println(delta)
		z = newValue
	}
	return z
}

func absolute(x float64) float64 {
	if x > 0 {
		return x
	}
	return -x
}
