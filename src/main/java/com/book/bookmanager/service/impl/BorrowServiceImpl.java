package com.book.bookmanager.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.book.bookmanager.dto.BorrowDto;
import com.book.bookmanager.entity.Book;
import com.book.bookmanager.entity.Borrow;
import com.book.bookmanager.entity.User;
import com.book.bookmanager.enums.BookStatus;
import com.book.bookmanager.enums.BookType;
import com.book.bookmanager.exception.BookException;
import com.book.bookmanager.mapper.BookMapper;
import com.book.bookmanager.mapper.UserMapper;
import com.book.bookmanager.service.BorrowService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class BorrowServiceImpl extends BaseServiceImpl<Borrow> implements BorrowService {

    @Resource
    private BookMapper bookMapper;

    @Resource
    private UserMapper userMapper;

    @Override
    public Book borrow(Borrow borrow) {
        Book book = bookMapper.selectById(borrow.getBookId());
        // 查询当前用户是否借阅过这本书
        Borrow one = this.lambdaQuery()
                .eq(Borrow::getUserId, borrow.getUserId())
                .eq(Borrow::getBookId, borrow.getBookId())
                .eq(Borrow::getStatus, BookStatus.NOT_RETURNED.getCode())
                .one();
        if (one != null) {
            this.updateById(one);
            return book;
        }

        // 判断当前图书的数量是否大于0
        if (book.getNum() <= 0) {
            throw new BookException("当前图书已经被借完了！");
        }
        borrow.setType(String.valueOf(BookType.BORROW.getCode()));
        borrow.setStatus(BookStatus.NOT_RETURNED.getCode());
        borrow.setCreateTime(LocalDateTime.now());
        boolean save = this.save(borrow);
        if (save) {
            book.setNum(book.getNum() - 1);
            bookMapper.updateById(book);
        }
        return book;
    }

    @Override
    public Book returnBook(Borrow borrow) {
        Book book = bookMapper.selectById(borrow.getBookId());
        // 查询当前用户是否归还过这本书
        Borrow one = this.lambdaQuery()
                .eq(Borrow::getUserId, borrow.getUserId())
                .eq(Borrow::getBookId, borrow.getBookId())
                .eq(Borrow::getStatus, BookStatus.RETURNED.getCode())
                .one();
        if (one != null) {
            this.updateById(one);
            return book;
        }
        borrow.setType(String.valueOf(BookType.RETURN.getCode()));
        borrow.setStatus(BookStatus.RETURNED.getCode());
        borrow.setCreateTime(LocalDateTime.now());
        boolean save = this.save(borrow);
        if (save) {
            book.setNum(book.getNum() + 1);
            bookMapper.updateById(book);
        }
        return book;
    }

    @Override
    public Page<BorrowDto> getListByUser(Page<Borrow> page, String userId, int type) {
        Page<Borrow> borrowPage = this.lambdaQuery()
                .eq(StringUtils.hasLength(userId), Borrow::getUserId, userId)
                .eq(Borrow::getType, type).page(page);
        List<BorrowDto> borrowDtos = new ArrayList<>();
        borrowPage.getRecords().forEach(borrow -> {
            BorrowDto borrowDto = new BorrowDto();
            Book book = bookMapper.selectById(borrow.getBookId());
            borrowDto.setName(book.getName());
            User user = userMapper.selectById(borrow.getUserId());
            borrowDto.setUser(user.getUsername());
            BeanUtils.copyProperties(borrow, borrowDto);
            borrowDtos.add(borrowDto);
        });
        Page<BorrowDto> dtoPage = new Page<>();
        BeanUtils.copyProperties(borrowPage, dtoPage);
        dtoPage.setRecords(borrowDtos);
        return dtoPage;
    }
}