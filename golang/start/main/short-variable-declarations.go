package main

import "fmt"

/**
:= cannot be used outside the function block
*/
func main() {
	var i, j = 1, 2
	k := 3
	c, python, java := true, false, "no!"

	fmt.Println(i, j, k, c, python, java)
}
