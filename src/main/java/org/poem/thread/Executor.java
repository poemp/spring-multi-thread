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
public class Executor<T> {


    private static final Logger logger = LoggerFactory.getLogger(Executor.class);
    /**
     * 线程池
     */
    private static final ExecutorService THREA_POOL =
            new ThreadPoolExecutor(5, 10,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1024),
            new ThreadFactoryBuilder()
                    .setNameFormat("executor-pool-%d").build(),
            new ThreadPoolExecutor.AbortPolicy());


    /**
     * this class
     */
    private static Executor executor = null;

    private Executor() {

    }

    public static Executor build() {
        if (executor == null) {
            executor = new Executor();
        }
        return executor;
    }


    /**
     * 多个执行
     * @param executorRunners
     */
    public void run(List<ExecutorRunner> executorRunners){
        if(!CollectionUtils.isEmpty(executorRunners)){
            executorRunners.forEach(this::run);
        }
    }

    /**
     * 开始执行
     *
     * @param runner
     */
    public Future<T> run(ExecutorRunner<T> runner) {
        Future<T> future = null;
        try {
            runner.init();
            future = THREA_POOL.submit(runner);
            runner.after();
        }catch (Exception e){
            logger.error("executor has exception:"+ e.getMessage());
            runner.exception();
        }finally {
            runner.finall();
        }
        return future;
    }

}
