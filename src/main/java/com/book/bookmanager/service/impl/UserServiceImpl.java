package com.book.bookmanager.service.impl;

import com.book.bookmanager.entity.User;
import com.book.bookmanager.exception.BookException;
import com.book.bookmanager.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;


@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Override
    public User login(User user) {
        User one = this.lambdaQuery().eq(User::getUsername, user.getUsername())
                .eq(User::getPassword, user.getPassword())
                .one();
        if (ObjectUtils.isEmpty(one)) throw new BookException("用户名或密码错误");
        return one;
    }
}
