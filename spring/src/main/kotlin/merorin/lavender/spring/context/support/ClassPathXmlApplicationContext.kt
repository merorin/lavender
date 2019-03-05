package merorin.lavender.spring.context.support

import merorin.lavender.spring.beans.factory.BeanFactory
import merorin.lavender.spring.beans.factory.support.BeanDefinitionRegistry
import merorin.lavender.spring.beans.factory.support.DefaultBeanFactory
import merorin.lavender.spring.beans.factory.xml.XmlBeanDefinitionReader
import merorin.lavender.spring.context.ApplicationContext

/**
 * Description:
 * @author guobin On date 2019/1/5.
 * @since jdk 1.8
 * @version 1.0
 */
class ClassPathXmlApplicationContext(configPath: String) : ApplicationContext {

    private val factory : BeanFactory = DefaultBeanFactory()
    private val reader : XmlBeanDefinitionReader = XmlBeanDefinitionReader(factory as BeanDefinitionRegistry)

    init {
        reader.loadBeanDefinition(configPath)
    }

    override fun getBean(beanName: String): Any? {
        return this.factory.getBean(beanName)
    }
}
