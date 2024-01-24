package com.book.bookmanager.service;
import com.book.bookmanager.entity.Book;

import java.util.List;

public interface BookService extends BaseService<Book>{

    List<Book> getByTagId(Integer tagId);
}
