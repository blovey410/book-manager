package com.book.bookmanager.service.impl;

import com.book.bookmanager.entity.Book;
import com.book.bookmanager.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookServiceImpl extends BaseServiceImpl<Book> implements BookService {

    @Override
    public List<Book> getByTagId(Integer tagId) {
        return this.lambdaQuery().eq(Book::getTagId, tagId).list();
    }
}
