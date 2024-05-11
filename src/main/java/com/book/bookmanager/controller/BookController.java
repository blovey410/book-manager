package com.book.bookmanager.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.book.bookmanager.entity.Book;
import com.book.bookmanager.service.BookService;
import com.book.bookmanager.util.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.time.LocalDateTime;

//@CrossOrigin
@RestController
@RequestMapping("book")
public class BookController extends BaseController<BookService, Book> {

    @Resource
    private BookService bookService;

    @GetMapping("findPage")
    public Result page(Page<Book> page, String search) {
        return Result.success(service.lambdaQuery()
                .like(StringUtils.hasLength(search), Book::getName, search)
                .page(page));
    }

    @GetMapping("getByTagId")
    public Result getByTagId(@RequestParam(value = "tagId", required = false) Integer tagId, @RequestParam(required = false, defaultValue = "true")boolean more) {
        return Result.success(bookService.getByTagId(tagId, more));
    }

    @GetMapping("hot")
    public Result hot() {
        return Result.success(bookService.hot());
    }

    @GetMapping("search")
    public Result search(@RequestParam(value = "search", required = false) String search) {
        return Result.success(bookService.search(search));
    }

    /**
     * 文件上传
     */
    @RequestMapping("/upload")
    public Result upload(@RequestParam(value = "file", required = false) MultipartFile file){
        return bookService.upload(file);
    }

    @Override
    public void saveBefore(Book entity) {
        entity.setClickNum(0);
        entity.setCreateTime(LocalDateTime.now());
    }
}
