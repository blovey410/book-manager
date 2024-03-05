package com.book.bookmanager.service.impl;

import com.book.bookmanager.entity.User;
import com.book.bookmanager.service.UserService;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Override
    public User login(User user) {
        return this.lambdaQuery().eq(User::getUsername, user.getUsername())
                .eq(User::getPassword, user.getPassword())
                .one();
    }
}
