package org.poem.thread;

import org.poem.api.ExecutorRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

/**
 * @author poem
 */
@Component
public class Executor {


    private static final Logger logger = LoggerFactory.getLogger(Executor.class);


    /**
     * 自定义异步线程池
     * @return
     */
    @Bean
    public AsyncTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(Runtime.getRuntime().availableProcessors() * 2);
        executor.setMaxPoolSize(100);
        executor.setThreadNamePrefix("async-executor-%d");
        return executor;
    }
    /**
     * 开始执行
     *
     * @param runner
     */
    @Async
    public void run(ExecutorRunner runner) {
        try {
            runner.run();
        } catch (Exception e) {
            logger.info("executor exception:" + e.getMessage());
            runner.exception();
        }

    }

}
