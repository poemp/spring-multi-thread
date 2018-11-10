package org.poem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author poem
 */

@EnableAsync
@SpringBootApplication
public class SpringMultiThreadApp {

    public static void main(String[] args) {
        SpringApplication.run(SpringMultiThreadApp.class, args);
    }
}
