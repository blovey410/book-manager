package com.book.bookmanager.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BorrowDto {
    private Long id;
    private Long bookId;
    private String author;
    private String imgUrl;
    private String name;
    private Integer num;
    private String user;
    private Long userId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
    private String status;
}
