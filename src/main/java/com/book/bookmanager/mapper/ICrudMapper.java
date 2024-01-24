package com.book.bookmanager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.book.bookmanager.entity.BaseEntity;

public interface ICrudMapper<T extends BaseEntity> extends BaseMapper<T> {
}
