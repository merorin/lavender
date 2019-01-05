package merorin.lavender.spring.beans.factory.support

import merorin.lavender.spring.beans.BeanDefinition

/**
 * Description:
 * @author guobin On date 2019/1/5.
 * @since jdk 1.8
 * @version 1.0
 */
interface BeanDefinitionRegistry {

    fun getBeanDefinition(beanName: String): BeanDefinition

    fun registerBeanDefinition(beanId : String, bd : BeanDefinition)
}