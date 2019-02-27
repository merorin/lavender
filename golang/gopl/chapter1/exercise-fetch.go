package main

import (
	"fmt"
	"io"
	"io/ioutil"
	"net/http"
	"os"
	"strings"
)

const urlPrefix = "http://"

func main() {
	version := os.Args[1]
	for _, url := range os.Args[2:] {
		// 1.8
		if !strings.HasPrefix(url, urlPrefix) {
			url = fmt.Sprintf("%s%s", urlPrefix, url)
		}
		resp, err := http.Get(url)
		if err != nil {
			_, _ = fmt.Fprintf(os.Stderr, "fetch: %v\n", err)
			os.Exit(1)
		}
		switch version {
		case "1.7":
			// 1.7
			_, err = io.Copy(os.Stdout, resp.Body)
			_ = resp.Body.Close()
			failureOnReading(url, err)
		case "1.9":
			// 1.9
			b, err := ioutil.ReadAll(resp.Body)
			_ = resp.Body.Close()
			failureOnReading(url, err)
			fmt.Printf("%s", b)
		default:
			b, err := ioutil.ReadAll(resp.Body)
			_ = resp.Body.Close()
			failureOnReading(url, err)
			fmt.Printf("Fetching status:%s\n", resp.Status)
			fmt.Printf("%s", b)
		}
	}
}

func failureOnReading(url string, err error) {
	if err != nil {
		_, _ = fmt.Fprintf(os.Stderr, "fetch: readinng %s: %v\n", url, err)
		os.Exit(1)
	}
}
