package merorin.lavender

import merorin.lavender.prepare.PetStoreService
import merorin.lavender.spring.beans.BeanDefinition
import merorin.lavender.spring.beans.factory.BeanFactory
import merorin.lavender.spring.beans.factory.support.DefaultBeanFactory
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

    private lateinit var beanFactory : BeanFactory

    @Before
    fun setUp() {
        beanFactory = DefaultBeanFactory("petstore_v1.xml")
    }

    @Test
    fun testGetBean() {
        val beanDefinition : BeanDefinition = beanFactory.getBeanDefinition("petStore")

        assertEquals("merorin.lavender.prepare.PetStoreServiceImpl", beanDefinition.getBeanClassName())

        val petStore : PetStoreService = beanFactory.getBean("petStore") as PetStoreService

        assertNotNull(petStore)
    }

    @Test
    fun testInvalidBean() {
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
            DefaultBeanFactory("xxxxxx.xml")
        } catch (e : BeanDefinitionStoreException) {
            return
        }
        fail("Expect BeanDefinitionStoreException")
    }
}