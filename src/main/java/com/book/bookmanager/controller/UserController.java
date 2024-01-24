package com.book.bookmanager.controller;


import com.book.bookmanager.entity.User;
import com.book.bookmanager.service.UserService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("user")
public class UserController extends BaseController<UserService, User> {


}

