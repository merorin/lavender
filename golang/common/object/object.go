package object

func NotNil(o interface{}) bool {
	return o != nil
}

func IsNil(o interface{}) bool {
	return o == nil
}
