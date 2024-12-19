package com.manage.service.impl;

import com.manage.entity.Borrow;
import com.manage.mapper.BorrowMapper;
import com.manage.service.BorrowService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowServiceImpl implements BorrowService {

    @Resource
    private BorrowMapper borrowMapper;


    @Override
    public List<Borrow> getBorrowList() {
        return borrowMapper.getBorrowList();
    }
}
