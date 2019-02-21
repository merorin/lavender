package main

import (
	"fmt"
	"math"
)

func main() {
	fmt.Println(Sqrt(2))
	fmt.Println(Sqrt(-2))
}

func Sqrt(x float64) (float64, error) {
	if x < 0 {
		return 0, ErrNegativeSqrt(x)
	}
	return math.Sqrt(x), nil
}

func (e ErrNegativeSqrt) Error() string {
	return fmt.Sprintf("cannot Sqrt negative number: %f", float64(e))
}

type ErrNegativeSqrt float64
