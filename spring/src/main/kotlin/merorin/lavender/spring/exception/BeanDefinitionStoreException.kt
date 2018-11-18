package merorin.lavender.spring.exception

/**
 * Description:
 * @author guobin On date 2018/11/18.
 * @since jdk 1.8
 * @version 1.0
 */
class BeanDefinitionStoreException : BeansException {

    constructor() : super()

    constructor(message: String) : super(message)

    constructor(cause : Throwable) : super(cause)

    constructor(message: String, cause: Throwable) : super(message, cause)
}