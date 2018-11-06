package org.poem.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.poem.SpringUtil;
import org.poem.api.ExecutorRunner;
import org.poem.thread.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceTest.class);



    @Test
    public void insertInto() {
        Executor.build().run(new ExecutorRunner() {

            @Override
            public void run() {
                UserService userService = SpringUtil.getBean(UserService.class);
                userService.insertInto();
                logger.info("call");
            }


            @Override
            public void exception() {
                logger.info("exception");
            }
        });
    }

    @Test
    public void getUser() {
    }
}