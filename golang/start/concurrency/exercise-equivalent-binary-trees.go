package main

import (
	"fmt"
	"golang.org/x/tour/tree"
)

const capacity = 10

func main() {
	t1, t2 := tree.New(1), tree.New(1)
	fmt.Println(Same(t1, t2))
}

func Walk(t *tree.Tree, ch chan int) {
	if t == nil || len(ch) >= capacity {
		return
	}
	Walk(t.Left, ch)
	ch <- t.Value
	Walk(t.Right, ch)
}

func Same(t1, t2 *tree.Tree) bool {
	fmt.Println(t1, t2)
	ch1, ch2 := make(chan int, capacity), make(chan int, capacity)
	go Walk(t1, ch1)
	go Walk(t2, ch2)

	result := true
	count := 0
	for result && count < capacity {
		var i1, i2 int
		select {
		case i1 = <-ch1:
			i2 = selectFromAnotherChannel(ch2)
		case i2 = <-ch2:
			i1 = selectFromAnotherChannel(ch1)
		}
		fmt.Println(i1, i2)
		result = i1 == i2
		count++
	}

	return result
}

func selectFromAnotherChannel(ch chan int) int {
	select {
	case value := <-ch:
		return value
	}
}
