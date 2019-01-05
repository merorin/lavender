package merorin.lavender.v1

import org.junit.runner.RunWith
import org.junit.runners.Suite

/**
 * Description:
 * @author guobin On date 2019/1/5.
 * @since jdk 1.8
 * @version 1.0
 */
@RunWith(Suite::class)
@Suite.SuiteClasses(ApplicationContextTest::class,
        BeanFactoryTest::class,
        ResourceTest::class)
class V1AllTest