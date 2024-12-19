package com.manage.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Borrow {

    int id;
    int sid;
    int bid;
    Date borrowTime;
    String bookName;
    String studentName;
}
