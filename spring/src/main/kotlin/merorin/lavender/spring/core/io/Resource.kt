package merorin.lavender.spring.core.io

import java.io.IOException
import java.io.InputStream

/**
 * Description:
 * @author guobin On date 2019/1/5.
 * @since jdk 1.8
 * @version 1.0
 */
interface Resource {

    @Throws(IOException::class)
    fun getInputStream() : InputStream

    fun getDescription() : String
}