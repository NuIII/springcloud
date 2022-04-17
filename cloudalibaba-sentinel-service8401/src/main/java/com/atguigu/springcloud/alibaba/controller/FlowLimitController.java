package com.atguigu.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;


@RestController
public class FlowLimitController {
    @GetMapping("/testA")
    public String testA() {
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB() {
        System.out.println("======" + Thread.currentThread().getId() + "=========");
        return "------testB";
    }

    @GetMapping("/testRT")
    public String testRT() {
        System.out.println("======testRT=========");
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {

        }
        return "------testRT";
    }

    @GetMapping("/testException")
    public String testException() {
        System.out.println("======testException=========");
        int age = 10 / 0;
        return "------testException";
    }


    @GetMapping("/testHotkey")
    @SentinelResource(value = "testHotkey", blockHandler = "deal_testHotkey")
    public String testHotkey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2) {
        return "------testHotkey";
    }


    public String deal_testHotkey(String p1, String p2, BlockException e) {
        return "------deal_testHotkey------" + p1;

    }
}
