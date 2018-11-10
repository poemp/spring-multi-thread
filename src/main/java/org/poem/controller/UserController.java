package org.poem.controller;

import org.poem.api.ExecutorRunner;
import org.poem.service.UserService;
import org.poem.thread.Executor;
import org.poem.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author poem
 */
@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @Autowired
    private Executor executor;

    @GetMapping("/insertInto")
    public String insertInto(){
        for (int i=0;i<100;i++){
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
        return "异步任务再跑。";
    }


    /**
     *
     * @return
     */
    @PostMapping("/delete")
    public String deleteAll(){
        this.userService.deleteAll();
        return "删除完了";
    }

    @GetMapping("/getAll")
    public List<UserVO> getAll(){
        return  userService.getUser();
    }
}
