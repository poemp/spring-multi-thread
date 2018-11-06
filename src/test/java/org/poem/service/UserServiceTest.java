package org.poem.service;

import com.google.common.collect.Lists;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.poem.SpringUtil;
import org.poem.api.ExecutorRunner;
import org.poem.thread.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceTest.class);


    @Autowired
    UserService userService;

    @Test
    public void insertInto() {
        for (int i=0;i<100;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Executor.build().run(new ExecutorRunner() {

                        @Override
                        public Object call(){
                            userService.insertInto();
                            logger.info(Thread.currentThread().getName());
                            return new Object();
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