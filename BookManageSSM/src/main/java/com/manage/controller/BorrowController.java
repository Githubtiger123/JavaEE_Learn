package com.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BorrowController {

    @GetMapping({"/borrow", "/"})
    public String borrow() {
        return "borrow";
    }
}