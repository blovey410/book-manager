package com.book.bookmanager.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum BookStatus {

    /**
     * 在库
     */
    NOT_RETURNED(1, "未归还"),
    /**
     * 借出
     */
    RETURNED(2, "已归还"),
    ;

    private int code;
    private String desc;
}
