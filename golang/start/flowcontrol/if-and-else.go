package main

import (
	"fmt"
	"math"
)

func main() {
	fmt.Println(powWithElse(3, 2, 10), powWithElse(3, 3, 20))
}

func powWithElse(x, n, limit float64) float64 {
	if v := math.Pow(x, n); v < limit {
		return v
	} else {
		fmt.Printf("%g >= %g\n", v, limit)
	}
	return limit
}
