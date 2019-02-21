package main

import (
	"io"
	"os"
	"strings"
)

func main() {
	s := strings.NewReader("Lbh penpxrq gur pbqr!")
	r := rot13Reader{s}
	_, _ = io.Copy(os.Stdout, &r)
}

func (reader rot13Reader) Read(b []byte) (n int, err error) {
	n, err = reader.r.Read(b)
	for index := range b {
		b[index] = rot13(b[index])
	}
	return
}

func rot13(b byte) byte {
	var point byte
	switch {
	case b >= 'A' && b <= 'Z':
		point = 'A'
	case b >= 'a' && b < 'z':
		point = 'a'
	default:
		return b
	}
	return ((b-point)+13)%26 + point
}

type rot13Reader struct {
	r io.Reader
}
