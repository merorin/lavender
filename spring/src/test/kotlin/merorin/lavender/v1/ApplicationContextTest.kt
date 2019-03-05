package merorin.lavender.v1

import merorin.lavender.prepare.PetStoreService
import merorin.lavender.spring.context.ApplicationContext
import merorin.lavender.spring.context.support.ClassPathXmlApplicationContext
import merorin.lavender.spring.context.support.FileSystemXmlApplicationContext
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

/**
 * Description:
 * @author guobin On date 2019/1/5.
 * @since jdk 1.8
 * @version 1.0
 */
class ApplicationContextTest {

    private lateinit var ctx : ApplicationContext

    @Test
    fun testGetBeanFromClassPathContext() {
        ctx = ClassPathXmlApplicationContext("petstore_v1.xml")
        val petstore = ctx.getBean("petStore") as PetStoreService
        assertNotNull(petstore)
    }

    @Test
    fun testGetBeanFromFileSystemContext() {
        ctx = FileSystemXmlApplicationContext("")
        val petstore = ctx.getBean("petStore") as PetStoreService
        assertNotNull(petstore)
    }
}