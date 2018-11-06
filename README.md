# spring-multi-thread
spring 多线程测试, bean 在线程中的使用, 详细的测试

可以使用多线程， 但是需要实现接口

````
ExecutorRunner
    |- call()
    |- exception()
````

并且使用
````
Executor.build().run(new ExecutorRunner(){...})
````

需要处理 *exception* 接口