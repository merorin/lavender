package merorin.lavender.v1

import merorin.lavender.prepare.PetStoreService
import merorin.lavender.spring.beans.BeanDefinition
import merorin.lavender.spring.beans.factory.support.DefaultBeanFactory
import merorin.lavender.spring.beans.factory.xml.XmlBeanDefinitionReader
import merorin.lavender.spring.exception.BeanCreationException
import merorin.lavender.spring.exception.BeanDefinitionStoreException
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/**
 * Description:
 * @author guobin On date 2018/11/18.
 * @since jdk 1.8
 * @version 1.0
 */
class BeanFactoryTest {

    private lateinit var beanFactory : DefaultBeanFactory
    private lateinit var reader : XmlBeanDefinitionReader

    @Before
    fun setUp() {
        beanFactory = DefaultBeanFactory()
        reader = XmlBeanDefinitionReader(beanFactory)
    }

    @Test
    fun testGetBean() {
        reader.loadBeanDefinition("petstore_v1.xml")
        val beanDefinition : BeanDefinition = beanFactory.getBeanDefinition("petStore")

        assertEquals("merorin.lavender.prepare.PetStoreServiceImpl", beanDefinition.getBeanClassName())

        val petStore : PetStoreService = beanFactory.getBean("petStore") as PetStoreService

        assertNotNull(petStore)
    }

    @Test
    fun testInvalidBean() {
        reader.loadBeanDefinition("petstore_v1.xml")
        try {
            beanFactory.getBean("invalidBean")
        } catch (e : BeanCreationException) {
            return
        }
        fail("Expect BeanCreationException")
    }

    @Test
    fun testInvalidXml() {
        try {
            reader.loadBeanDefinition("xxxxxx.xml")
        } catch (e : BeanDefinitionStoreException) {
            return
        }
        fail("Expect BeanDefinitionStoreException")
    }
}