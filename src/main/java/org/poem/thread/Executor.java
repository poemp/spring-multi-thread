package org.poem.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.poem.api.ExecutorRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.*;

/**
 * @author poem
 */
public class Executor {


    private static final Logger logger = LoggerFactory.getLogger(Executor.class);


    /**
     * 线程池
     */
    private static final ExecutorService THREA_POOL =
            new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 2,
                    100,
                    0L, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<Runnable>(1024),
                    new ThreadFactoryBuilder()
                            .setNameFormat("executor-pool-%d").build(),
                    new ThreadPoolExecutor.AbortPolicy());


    /**
     * this class
     */
    private static Executor executor = null;

    static {
        logger.info("12345");
        executor = new Executor();
    }

    private Executor() {

    }

    public static Executor build() {
        return Executor.executor;
    }


    /**
     * 多个执行
     *
     * @param executorRunners
     */
    public void run(List<ExecutorRunner> executorRunners) {
        if (!CollectionUtils.isEmpty(executorRunners)) {
            executorRunners.forEach(this::run);
        }
    }

    /**
     * 开始执行
     *
     * @param runner
     */
    public void run(ExecutorRunner<?> runner) {
        try {
            Future<?> future = THREA_POOL.submit(runner);
            while (!future.isDone()) {
                future.get();
            }
        }catch (Exception e){
            logger.info("executor exception:" + e.getMessage());
            runner.exception();
        }

    }

}
