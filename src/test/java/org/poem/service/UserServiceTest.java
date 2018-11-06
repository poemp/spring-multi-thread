package org.poem.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.poem.api.ExecutorRunner;
import org.poem.thread.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.ApplicationContextTestUtils;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Future;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceTest.class);
    @Autowired
    UserService userService;

    @Test
    public void insertInto() {
        Executor.build().run(new ExecutorRunner() {

            private UserService userService;

            @Override
            public void run() {
                this.userService.insertInto();
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