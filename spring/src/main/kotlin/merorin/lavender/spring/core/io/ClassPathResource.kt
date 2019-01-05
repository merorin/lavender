package merorin.lavender.spring.core.io

import merorin.lavender.spring.util.ClassUtils
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream

/**
 * Description:
 * @author guobin On date 2019/1/5.
 * @since jdk 1.8
 * @version 1.0
 */
class ClassPathResource(private val configPath : String,
                        private val classLoader : ClassLoader = ClassUtils.getDefaultClassLoader()) : Resource {

    @Throws(IOException::class)
    override fun getInputStream() : InputStream {
        return this.classLoader.getResourceAsStream(this.configPath)
                ?: throw FileNotFoundException("$configPath cannot be opened.")
    }

    override fun getDescription() : String {
        return this.configPath
    }
}