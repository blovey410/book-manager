package com.book.bookmanager.config;

import com.book.bookmanager.annotation.BaseResponse;
import com.book.bookmanager.util.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;


/**
 * 统一响应体处理器
 *
 * @author NULL
 * @date 2019-07-16
 */
@RestControllerAdvice(annotations = BaseResponse.class)
public class ResponseResultHandlerAdvice implements ResponseBodyAdvice<Object> {

    @Autowired
    ObjectMapper om;

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        if ((body instanceof String) && !MediaType.APPLICATION_XML_VALUE.equals(selectedContentType.toString())) {
            response.getHeaders().set("Content-Type", "application/json");
            return om.writeValueAsString(Result.success(body));
        }

        if (Objects.isNull(body) && MediaType.TEXT_HTML_VALUE.equals(selectedContentType.toString())) {
            response.getHeaders().set("Content-Type", "application/json");
            return om.writeValueAsString(Result.success(body));
        }

        // 非JSON格式body直接返回即可
        return Result.success(body);
    }
}

