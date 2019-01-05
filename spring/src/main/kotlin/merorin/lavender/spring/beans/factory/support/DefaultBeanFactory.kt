package merorin.lavender.spring.beans.factory.support

import merorin.lavender.spring.beans.BeanDefinition
import merorin.lavender.spring.beans.factory.BeanFactory
import merorin.lavender.spring.exception.BeanCreationException
import merorin.lavender.spring.util.ClassUtils
import java.util.concurrent.ConcurrentHashMap

/**
 * Description:
 * @author guobin On date 2018/11/18.
 * @since jdk 1.8
 * @version 1.0
 */
class DefaultBeanFactory : BeanFactory, BeanDefinitionRegistry {

    private val beanDefinitionMap : MutableMap<String, BeanDefinition> = ConcurrentHashMap()

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

    override fun registerBeanDefinition(beanId: String, bd: BeanDefinition) {
        this.beanDefinitionMap[beanId] = bd
    }
}