package main

import (
	"fmt"
	"github.com/360EntSecGroup-Skylar/excelize"
	"os"
	"regexp"
	"strings"
)

func main() {
	xlsx, err := excelize.OpenFile(os.Args[1])
	counter := make(map[string]int)
	if err != nil {
		fmt.Println(err)
		return
	}

	rows := xlsx.GetRows("Sheet 1")
	for _, row := range rows {
		if len(row) <= 1 {
			continue
		}
		values := strings.Split(strings.ToLower(row[1]), " ")
		for _, value := range values {
			if res, _ := regexp.MatchString("^[a-z]+$", value); !res {
				continue
			}
			counter[value]++
		}
	}
	for key, value := range counter {
		fmt.Println(key, ":", value)
	}
}
