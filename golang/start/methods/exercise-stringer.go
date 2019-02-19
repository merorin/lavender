package main

import "fmt"

func main() {
	hosts := map[string]IPAddr{
		"loopback":  {127, 0, 0, 1},
		"googleDNS": {8, 8, 8, 8},
	}
	for name, ip := range hosts {
		fmt.Printf("%v: %v\n", name, ip)
	}
}

type IPAddr [4]byte

func (ipArr IPAddr) String() string {
	return fmt.Sprintf("%d.%d.%d.%d", ipArr[0], ipArr[1], ipArr[2], ipArr[3])
}
