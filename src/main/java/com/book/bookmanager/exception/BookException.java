package com.book.bookmanager.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@EqualsAndHashCode(callSuper = true)
@Data
public class BookException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private Integer code;

    public BookException(String message) {
        super(message);
    }

    public BookException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    //public BookException(ResultEnum resultEnum) {
    //    this(resultEnum.getCode(), resultEnum.getMsg());
    //}
}
