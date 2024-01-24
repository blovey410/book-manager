package com.book.bookmanager.controller;


import com.book.bookmanager.entity.Book;
import com.book.bookmanager.entity.Result;
import com.book.bookmanager.service.BookService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

//@CrossOrigin
@RestController
@RequestMapping("book")
public class BookController extends BaseController<BookService, Book> {

    @Resource
    private BookService bookService;

    @GetMapping("getByTagId")
    public Result getByTagId(@RequestParam(value = "tagId", required = false) Integer tagId) {
        return Result.success(bookService.getByTagId(tagId));
    }
}

