package com.book.bookmanager.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 图书表(Book)表实体类
 *
 * @author 12444
 * @since 2024-01-24 10:16:46
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Book extends BaseEntity implements Serializable {
    
    
            /**
            *图书名称
            */
        private String name;
    
            /**
            *图书作者
            */
        private String author;
    
            /**
            *图书封面
            */
        private String imgUrl;
    
            /**
            *图书数量
            */
        private Integer num;
    
            /**
            *图书简介
            */
        private String info;
    
            /**
            *图书类型
            */
        private Integer tagId;
    
            /**
            *创建时间
            */
        private LocalDateTime createTime;
    
}
