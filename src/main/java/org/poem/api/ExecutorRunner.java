package org.poem.api;

/**
 * @author poem
 */
public interface ExecutorRunner extends Runnable{


    /**
     * 执行
     * @return
     * @throws Exception
     */
    @Override
    void run();

    /**
     * 发生异常
     */
    void exception();
}
