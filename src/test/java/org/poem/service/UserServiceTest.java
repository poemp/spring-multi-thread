package org.poem.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.poem.api.ExecutorRunner;
import org.poem.thread.Executor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void insertInto() {
        Executor.build().run(new ExecutorRunner<Void>() {
            @Override
            public void init() throws Exception {

            }

            @Override
            public Void call() throws Exception {
                userService.insertInto();
                return null;
            }

            @Override
            public void after() throws Exception {

            }

            @Override
            public void exception() {

            }

            @Override
            public void finall() {

            }
        });
    }

    @Test
    public void getUser() {
    }
}