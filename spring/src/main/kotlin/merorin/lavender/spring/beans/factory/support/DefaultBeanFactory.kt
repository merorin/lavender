package merorin.lavender.spring.beans.factory.support

import merorin.lavender.spring.beans.BeanDefinition
import merorin.lavender.spring.beans.factory.BeanFactory
import merorin.lavender.spring.exception.BeanCreationException
import merorin.lavender.spring.exception.BeanDefinitionStoreException
import merorin.lavender.spring.util.ClassUtils
import org.dom4j.io.SAXReader
import java.util.concurrent.ConcurrentHashMap

/**
 * Description:
 * @author guobin On date 2018/11/18.
 * @since jdk 1.8
 * @version 1.0
 */
class DefaultBeanFactory(configFile: String) : BeanFactory {

    private val beanDefinitionMap : MutableMap<String, BeanDefinition> = ConcurrentHashMap()

    companion object {
        private const val ID_ATTRIBUTE : String = "id"
        private const val CLASS_ATTRIBUTE : String = "class"
    }

    init {
        loadBeanDefinition(configFile)
    }

    override fun getBeanDefinition(beanName: String): BeanDefinition =
            if (this.beanDefinitionMap.containsKey(beanName))
                this.beanDefinitionMap[beanName]!!
            else
                throw BeanCreationException("No such bean for name, $beanName")

    override fun getBean(beanName: String): Any? {
        val beanDefinition = this.beanDefinitionMap[beanName] ?: throw BeanCreationException("No such bean for name, $beanName")
        val classLoader = ClassUtils.getDefaultClassLoader()
        try {
            return classLoader.loadClass(beanDefinition.getBeanClassName()).newInstance()
        } catch (e : Exception) {
            throw BeanCreationException(e)
        }
    }

    private fun loadBeanDefinition(configFile: String) {
        try {
            ClassUtils.getDefaultClassLoader()
                    .getResourceAsStream(configFile).use {
                        val doc = SAXReader().read(it)
                        val root = doc.rootElement
                        root.elementIterator().forEach { element ->
                            val id = element.attributeValue(ID_ATTRIBUTE)
                            val beanClassName = element.attributeValue(CLASS_ATTRIBUTE)
                            this.beanDefinitionMap[id] = GenericBeanDefinition(id, beanClassName)
                        }
                    }
        } catch (e: Exception) {
            throw BeanDefinitionStoreException("Cannot parse xml, $configFile")
        }
    }


}