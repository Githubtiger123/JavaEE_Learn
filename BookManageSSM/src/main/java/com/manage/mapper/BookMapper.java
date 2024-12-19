package com.manage.mapper;

import com.manage.entity.Book;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BookMapper {


    @Select("select * from book")
    List<Book> getBookList();

    @Delete("delete from book where bid = #{id}")
    void deleteBook(int id);

    @Insert("insert into book(bid,title,'desc',price) values(#{id},#{title},#{desc},#{price})")
    void addBook(@Param("id") int id, @Param("title") String title, @Param("desc") String desc, @Param("price") double price);
}
