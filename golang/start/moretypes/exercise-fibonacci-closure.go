package main

import "fmt"

func main() {
	f := fibonacci()
	for i := 0; i < 10; i++ {
		fmt.Println(f())
	}
}

func fibonacci() func() int {
	count := -1
	return func() int {
		count++
		return doFibonacci(count)
	}
}

func doFibonacci(count int) int {
	if count == 0 {
		return 0
	}
	if count == 1 {
		return 1
	}
	return doFibonacci(count-1) + doFibonacci(count-2)
}
