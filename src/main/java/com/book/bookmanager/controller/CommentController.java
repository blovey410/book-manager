package com.book.bookmanager.controller;


import com.book.bookmanager.entity.Comment;
import com.book.bookmanager.service.CommentService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("comment")
public class CommentController extends BaseController<CommentService, Comment> {


}

