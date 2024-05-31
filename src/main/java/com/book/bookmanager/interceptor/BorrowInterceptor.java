package com.book.bookmanager.interceptor;


import com.book.bookmanager.entity.Book;
import com.book.bookmanager.exception.BookException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Component
@Slf4j
public class BorrowInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        if (requestURI.contains("/borrow")) {
            log.info("拦截请求：{}", requestURI);
            throw new BookException(400, "清先登陆在进行访问");
        }
        return true;
    }
}
