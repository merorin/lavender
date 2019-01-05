package merorin.lavender.spring.beans.factory.xml

import merorin.lavender.spring.beans.factory.support.BeanDefinitionRegistry
import merorin.lavender.spring.beans.factory.support.GenericBeanDefinition
import merorin.lavender.spring.exception.BeanDefinitionStoreException
import merorin.lavender.spring.util.ClassUtils
import org.dom4j.io.SAXReader
import java.io.InputStream

/**
 * Description:
 * @author guobin On date 2019/1/5.
 * @since jdk 1.8
 * @version 1.0
 */
class XmlBeanDefinitionReader(private val registry : BeanDefinitionRegistry) {

    companion object {
        private const val ID_ATTRIBUTE : String = "id"
        private const val CLASS_ATTRIBUTE : String = "class"
    }

    fun loadBeanDefinition(configFile: String) {
        try {
            ClassUtils.getDefaultClassLoader()
                    .getResourceAsStream(configFile).use { it.beRead() }
        } catch (e: Exception) {
            throw BeanDefinitionStoreException("Cannot parse xml, $configFile")
        }
    }

    private fun InputStream.beRead() {
        val doc = SAXReader().read(this)
        val root = doc.rootElement
        root.elementIterator().forEach {
            val id = it.attributeValue(ID_ATTRIBUTE)
            val beanClassName = it.attributeValue(CLASS_ATTRIBUTE)
            registry.registerBeanDefinition(id, GenericBeanDefinition(id, beanClassName))
        }
    }
}