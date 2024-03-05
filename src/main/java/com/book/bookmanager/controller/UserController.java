package com.book.bookmanager.controller;


import com.book.bookmanager.entity.User;
import com.book.bookmanager.service.UserService;
import com.book.bookmanager.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@CrossOrigin
@RestController
@RequestMapping("user")
public class UserController extends BaseController<UserService, User> {

    @Resource
    private UserService service;


    @PostMapping("/login")
    public Result login(@RequestBody User user, HttpServletRequest request) {
        return Result.success(service.login(user));
    }

    @Override
    public void saveBefore(User entity) {
        entity.setRegTime(LocalDateTime.now());
    }
}

