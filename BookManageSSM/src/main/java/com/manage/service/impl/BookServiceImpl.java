package com.manage.service.impl;

import com.manage.entity.Book;
import com.manage.mapper.BookMapper;
import com.manage.service.BookService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Resource
    private BookMapper bookMapper;

    @Override
    public List<Book> getBookList() {
        return bookMapper.getBookList();
    }
}
