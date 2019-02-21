package main

import (
	"golang.org/x/tour/pic"
	"image"
	"image/color"
)

func main() {
	m := MyImage{4, 5}
	pic.ShowImage(m)
}

func (i MyImage) Bounds() image.Rectangle {
	return image.Rect(0, 0, i.w, i.h)
}

func (i MyImage) ColorModel() color.Model {
	return color.RGBAModel
}

func (i MyImage) At(x, y int) color.Color {
	v := uint8(x ^ y)
	return color.RGBA{R: v, G: v, B: 255, A: 255}
}

type MyImage struct {
	w, h int
}
