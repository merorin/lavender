package main

import "fmt"

/**
 like Object.toString() in java

 type stringer interface {
	 String() string
 }

*/
func main() {
	a := Person{"Authur Dent", 42}
	z := Person{"Merorin", 26}
	fmt.Println(a, z)
}

func (p Person) String() string {
	return fmt.Sprintf("%v (%v years)", p.Name, p.Age)
}

type Person struct {
	Name string
	Age  int
}
