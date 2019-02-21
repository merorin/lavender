package main

import "golang.org/x/tour/reader"

func main() {
	reader.Validate(MyReader{})
}

func (reader MyReader) Read(b []byte) (int, error) {
	length := len(b)
	for index := range b {
		b[index] = byte('A')
	}
	return length, nil
}

type MyReader struct {
}
