package com.manage.mapper;

import com.manage.entity.Borrow;
import org.apache.ibatis.annotations.*;
import org.springframework.security.core.parameters.P;

import java.util.Date;
import java.util.List;

public interface BorrowMapper {


    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "sid", property = "sid"),
            @Result(column = "bid", property = "bid"),
            @Result(column = "time", property = "borrowTime"),
            @Result(column = "title", property = "bookName"),
            @Result(column = "name", property = "studentName"),
    })
    @Select("""
            select * from borrow left join student on borrow.sid = stduent.sid
            left join book on borrow.bid = book.bid
            """)
    List<Borrow> getBorrowList();


    @Insert("insert into borrow(id,sid,bid,time) values(#{id},#{sid},#{bid},#{borrowTime})")
    void addBorrow(@Param("id") int id, @Param("sid") int sid, @Param("bid") int bid, @Param("borrowTime") Date borrowTime);

    @Delete("delete from borrow where id = #{id}")
    void deleteBorrow(int id);

}
