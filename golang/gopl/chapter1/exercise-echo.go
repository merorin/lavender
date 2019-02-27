package main

import (
	"fmt"
	"os"
	"strings"
	"time"
)

const times = 10

func main() {
	// 1.1
	fmt.Println("1.1-----------------")
	fmt.Println(strings.Join(os.Args, "-"))

	// 1.2
	fmt.Println("1.2-----------------")
	for index, arg := range os.Args {
		fmt.Printf("%d:%s\n", index, arg)
	}

	// 1.3
	fmt.Println("1.3-----------------")
	// expert
	testPerformance(func() {
		var s, sep string
		for i := 1; i < len(os.Args); i++ {
			s += sep + os.Args[i]
			sep = "-"
		}
		fmt.Println(s)
	}, "expert")

	// advanced
	testPerformance(func() {
		s, sep := "", ""
		for _, arg := range os.Args[1:] {
			s += sep + arg
			sep = "-"
		}
		fmt.Println(s)
	}, "advanced")

	// master
	testPerformance(func() {
		fmt.Println(strings.Join(os.Args[1:], "-"))
	}, "expert")

	// result:
	//for-the-alliance-we-will-crush-our-enermy-I-hate-walking-with-these-human-we-need-more-words-to-support-our-test-first-blood-I-am-so-cute-when-I-was-young
	//Duration for expert is 44100
	//for-the-alliance-we-will-crush-our-enermy-I-hate-walking-with-these-human-we-need-more-words-to-support-our-test-first-blood-I-am-so-cute-when-I-was-young
	//Duration for advanced is 53900
	//for-the-alliance-we-will-crush-our-enermy-I-hate-walking-with-these-human-we-need-more-words-to-support-our-test-first-blood-I-am-so-cute-when-I-was-young
	//Duration for master is 15100
}

func testPerformance(function func(), name string) {
	start := time.Now().Nanosecond()
	for i := times; i > 0; i-- {
		function()
	}
	end := time.Now().Nanosecond()
	fmt.Printf("Duration for %s is %d\n", name, (end-start)/times)
}
