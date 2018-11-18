package merorin.lavender.spring.util

import java.lang.Exception
import java.lang.NullPointerException
import java.lang.RuntimeException

/**
 * Description:
 * @author guobin On date 2018/11/18.
 * @since jdk 1.8
 * @version 1.0
 */
object ClassUtils {

    fun getDefaultClassLoader() : ClassLoader {
        var cl : ClassLoader?
        try {
            cl = Thread.currentThread().contextClassLoader
        } catch (e : Exception) {
            cl = null
            e.printStackTrace()
        }
        if (cl == null) {
            cl = ClassUtils.javaClass.classLoader
            if (cl == null) {
                try {
                    cl = ClassLoader.getSystemClassLoader()
                } catch (e : Exception) {
                    throw RuntimeException(e)
                }
            }
        }
        if (cl == null) {
            throw NullPointerException()
        }
        return cl
    }
}