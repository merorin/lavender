package main

import "fmt"

const (
	Big   = 1 << 100
	Small = Big >> 99
)

func main() {
	fmt.Println(needInt(Small))
	// needInt for Big will overflow
	//fmt.Println(needInt(Big))
	fmt.Println(needFloat(Small))
	fmt.Println(needFloat(Big))
}

func needInt(x int) int {
	return x*10 + 1
}

func needFloat(x float64) float64 {
	return x * 0.1
}
