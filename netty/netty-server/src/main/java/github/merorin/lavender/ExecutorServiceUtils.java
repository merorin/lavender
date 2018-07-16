package github.merorin.lavender;

import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description:线程池工具类
 *
 * @author guobin On date 2018/7/16.
 * @version 1.0
 * @since jdk 1.8
 */
public class ExecutorServiceUtils {

    private static final int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors();

    private static final int MAX_POOL_SIZE = CORE_POOL_SIZE >> 1 + CORE_POOL_SIZE;

    private static final Map<String, ExecutorService> EXECUTOR_SERVICE_POOL = new ConcurrentHashMap<>(16);

    /**
     * 不允许实例化
     */
    private ExecutorServiceUtils() {}

    /**
     * 创建一个新的{@link ThreadPoolExecutor}.如果先前已经创建过那么会直接复用原来的线程池
     * @param factoryNamePrefix 线程池名字前缀
     * @return 得到的线程池
     */
    public static ExecutorService getOrCreateThreadPoolExecutor(String factoryNamePrefix) {
        return EXECUTOR_SERVICE_POOL.computeIfAbsent(factoryNamePrefix, k -> new ThreadPoolExecutor(
                CORE_POOL_SIZE, MAX_POOL_SIZE, 10000L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(Integer.MAX_VALUE),
                new ThreadFactory() {

                    private final AtomicInteger counter = new AtomicInteger(0);

                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r, "PlainOioServerWorker-" + this.counter.getAndIncrement());
                    }
                },
                new ThreadPoolExecutor.AbortPolicy()
        ));
    }
}
