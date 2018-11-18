package merorin.lavender.spring.beans.factory

import merorin.lavender.spring.beans.BeanDefinition

/**
 * Description:
 * @author guobin On date 2018/11/18.
 * @since jdk 1.8
 * @version 1.0
 */
interface BeanFactory {

    fun getBeanDefinition(beanName: String): BeanDefinition


    fun getBean(beanName: String): Any?
}