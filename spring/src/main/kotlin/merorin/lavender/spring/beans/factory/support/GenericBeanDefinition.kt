package merorin.lavender.spring.beans.factory.support

import merorin.lavender.spring.beans.BeanDefinition

/**
 * Description:
 * @author guobin On date 2018/11/18.
 * @since jdk 1.8
 * @version 1.0
 */
class GenericBeanDefinition(
        private val id : String,
        private val beanClassName : String
) : BeanDefinition {

    override fun getBeanClassName() = beanClassName
}