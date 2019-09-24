package com.fy.specialday.Config.boot;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * 〈资源目录检索〉
 *
 * @author fangyan
 * @create 2019/9/22
 * @since 1.0.0
 */
@Configuration
@Order(1)
public class reourcePath implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("----------------------------纪念日----------------------------------");
    }
}
