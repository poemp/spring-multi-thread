package org.poem.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.poem.api.ExecutorRunner;
import org.poem.thread.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceTest.class);


    @Autowired
    UserService userService;

    @Autowired
    private  Executor executor;

    @Test
    public void insertInto() {
        for (int i=0;i<100;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    executor.run(new ExecutorRunner() {

                        @Override
                        public void run(){
                            userService.insertInto();
                            logger.info(Thread.currentThread().getName());
                        }


                        @Override
                        public void exception() {
                            logger.info("exception");
                        }
                    });
                }
            }).run();
        }
    }

    @Test
    public void getUser() {
    }
}