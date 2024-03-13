package com.book.bookmanager.controller;


import com.book.bookmanager.entity.User;
import com.book.bookmanager.exception.BookException;
import com.book.bookmanager.service.UserService;
import com.book.bookmanager.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@CrossOrigin
@RestController
@RequestMapping("user")
public class UserController extends BaseController<UserService, User> {

    @Resource
    private UserService userService;


    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        return Result.success(userService.login(user));
    }

    @Override
    public void saveBefore(User entity) {
        User user = userService.lambdaQuery()
                .eq(User::getUsername, entity.getUsername()).one();
        if (user != null) throw new BookException("该用户已经添加注册过了");
        entity.setRegTime(LocalDateTime.now());
    }


}

