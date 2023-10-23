package com.dx.commentstudy.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class test {

    @GetMapping("test")
    public String testCon(){
        return "这是一个test";
    }
}
