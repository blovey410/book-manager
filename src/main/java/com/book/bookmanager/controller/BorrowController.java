package com.book.bookmanager.controller;


import com.book.bookmanager.entity.Borrow;
import com.book.bookmanager.service.BorrowService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("borrow")
public class BorrowController extends BaseController<BorrowService, Borrow> {


}

