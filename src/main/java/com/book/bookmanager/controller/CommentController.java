package com.book.bookmanager.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.book.bookmanager.entity.Comment;
import com.book.bookmanager.service.CommentService;
import com.book.bookmanager.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@CrossOrigin
@RestController
@RequestMapping("comment")
public class CommentController extends BaseController<CommentService, Comment> {


    @Resource
    private CommentService commentService;

    @GetMapping("getList")
    public Result getList(Page<Comment> page) {
        return Result.success(commentService.getList(page));
    }


    @Override
    public void saveBefore(Comment entity) {
        entity.setCreateTime(LocalDateTime.now());
    }
}

