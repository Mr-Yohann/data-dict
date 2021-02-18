package com.yohann.consumer.controller;

import com.yohann.consumer.service.StudentService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * 前端控制器
 *
 * @author Yohann
 * @since 2021-02-18
 */
@RestController
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @RequestMapping("/test")
    public Object test() {
    return service.list();
    }
}
