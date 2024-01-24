package com.book.bookmanager.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * (User)表实体类
 *
 * @author 12444
 * @since 2024-01-24 10:16:58
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity implements Serializable {
    
    
        private String name;
    
        private String password;
    
        private String sex;
    
        private String telephone;
    
        private LocalDateTime regTime;
    
        private String status;
    
        private Integer role;
    
}
