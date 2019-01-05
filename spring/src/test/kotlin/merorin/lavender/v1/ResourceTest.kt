package merorin.lavender.v1

import merorin.lavender.spring.core.io.ClassPathResource
import merorin.lavender.spring.core.io.FileSystemResource
import org.junit.Assert.assertNotNull
import org.junit.Test

/**
 * Description:
 * @author guobin On date 2019/1/5.
 * @since jdk 1.8
 * @version 1.0
 */
class ResourceTest {

    @Test
    fun testClassPathResources() {
        ClassPathResource("petstore_v1.xml")
                .getInputStream().use {
                    assertNotNull(it)
                }

    }

    @Test
    fun testFileSystemResources() {

        val url = this.javaClass.getResource("/petstore_v1.xml").path
        FileSystemResource(url)
                .getInputStream().use {
                    assertNotNull(it)
                }
    }
}