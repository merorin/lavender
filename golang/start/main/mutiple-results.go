package main

import "fmt"

func main() {
	fmt.Println(swap("jay", "jj"))
}

func swap(x, y string) (string, string) {
	return y, x
}
