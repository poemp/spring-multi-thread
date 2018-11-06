package org.poem.api;

import java.util.concurrent.Callable;

/**
 * @author poem
 */
public interface ExecutorRunner<T> extends Callable<T> {


    /**
     * 执行
     *
     * @return
     * @throws Exception
     */
    @Override
    T call() throws Exception;

    /**
     * 发生异常
     */
    void exception();
}
