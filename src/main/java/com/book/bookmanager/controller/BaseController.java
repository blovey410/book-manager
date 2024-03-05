package com.book.bookmanager.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.book.bookmanager.entity.BaseEntity;
import com.book.bookmanager.util.Result;
import com.book.bookmanager.exception.BookException;
import com.book.bookmanager.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@SuppressWarnings("all")
public abstract class BaseController<S extends BaseService<T>, T extends BaseEntity> {

    @Autowired
    protected S service;

    /**
     * 域对象类型
     */
    protected Class<T> entityClass;

    /* 方法注解 */
    @PostMapping("/save")
    public Result save(@RequestBody T entity) {
        saveBefore(entity);
        if (!service.save(entity)) throw new BookException("保存失败");
        saveAfter(entity);
        return Result.success(entity);
    }

    /**
     * 前置数据的操作
     *
     * @param entity
     */
    public void saveBefore(T entity) {

    }

    /**
     * 后置数据的操作
     *
     * @param entity
     */
    public void saveAfter(T entity) {

    }


    @DeleteMapping("/delete")
    public void delete(T entity) {
        if (!service.removeById(entity)) throw new BookException("删除失败");
    }

    @DeleteMapping("/batchDelete")
    public void batchDelete(@RequestParam("ids") ArrayList<Integer> ids) {
        if (!service.removeByIds(ids)) throw new BookException("删除失败");
    }

    @PostMapping("/updateById")
    public Result updateById(@RequestBody T entity) {
        updateBefore(entity);
        if (!service.updateById(entity)) {
            throw new BookException();
        }
        updateAfter(entity);
        return Result.success(entity);
    }

    /**
     * 前置数据修改的操作
     *
     * @param entity
     */
    public void updateBefore(T entity) {

    }

    /**
     * 修改后置数据的操作
     *
     * @param entity
     */
    public void updateAfter(T entity) {

    }

    @GetMapping("/page")
    public Result page(Page<T> page) {
        return Result.success(service.page(page));
    }

    @GetMapping("/getOne")
    public Result getOne(String id) {
        return Result.success(service.getById(id));
    }

    @GetMapping("/list")
    public Result list() {
        return Result.success(service.list());
    }
}

