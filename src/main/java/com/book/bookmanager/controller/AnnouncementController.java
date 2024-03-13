package com.book.bookmanager.controller;


import com.book.bookmanager.entity.Announcement;
import com.book.bookmanager.service.AnnouncementService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@CrossOrigin
@RestController
@RequestMapping("announcement")
public class AnnouncementController extends BaseController<AnnouncementService, Announcement> {

    @Override
    public void saveBefore(Announcement entity) {
        entity.setCreateTime(LocalDateTime.now());
    }
}

