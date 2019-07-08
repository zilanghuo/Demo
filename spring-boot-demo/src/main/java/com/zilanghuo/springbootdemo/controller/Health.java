package com.zilanghuo.springbootdemo.controller;

import com.zilanghuo.springbootdemo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author laiwufa
 * @date 2019/3/26 0026 下午 5:27
 */
@RestController
@Scope("singleton")
@Slf4j
public class Health {

    @RequestMapping(value = "/health/check")
    @ResponseBody
    public User check() {
        try {
            log.info("健康检查-----------------------------------");
            log.info("最大可用内存:" + Runtime.getRuntime().maxMemory() / 1024 / 1024 + "M\t");
            log.info("当前JVM空闲内存:" + Runtime.getRuntime().freeMemory() / 1024 / 1024 + "M\t");
            log.info("当前JVM占用的内存总数:" + Runtime.getRuntime().totalMemory() / 1024 / 1024 + "M\t");
            log.info("-----------------------------------");
            User user = new User();
            user.setAge(12);
            user.setId(2323L);
            user.setName("小红");
            return user;
        } catch (Exception e) {
            log.error("checkHealth error", e);
            return new User();
        }

    }

}
