package com.manage.controller;

import com.manage.service.impl.UserServiceImpl;
import com.manage.service.impl.BorrowServiceImpl;
import com.manage.service.impl.BookServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BorrowController {

    @Resource
    BorrowServiceImpl borrowServiceImpl;

    @Resource
    BookServiceImpl bookServiceImpl;

    @Resource
    UserServiceImpl userServiceImpl;

    @GetMapping({"/borrow", "/"})
    public String borrow(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("nickname", user.getUsername());
        model.addAttribute("borrow_list", borrowServiceImpl.getBorrowList());
        model.addAttribute("book_count", bookServiceImpl.getBookList().size());
        model.addAttribute("student_count", userServiceImpl.getStudentList().size());
        return "borrow";
    }
}
