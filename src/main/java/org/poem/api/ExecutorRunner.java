package org.poem.api;

import java.util.concurrent.Callable;

/**
 * @author poem
 */
public interface ExecutorRunner<T> extends Callable<T> {

    /**
     * 初始化
     * @throws Exception
     */
    void init() throws Exception;

    /**
     * 执行
     * @return
     * @throws Exception
     */
    @Override
    T call() throws Exception;

    /**
     * 执行之后
     * @throws Exception
     */
    void after() throws Exception;

    /**
     * 发生异常
     */
    void exception();


    /**
     * 执行
     * @throws Exception
     */
    void finall();
}
