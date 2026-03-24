package com.codingnomads.springdata.example.mybatis.resultsandresult;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookMapper {
    // 1. Insert a new Book
    @Insert("INSERT INTO mybatis.books (title, author, genre, page_count) " +
            "VALUES (#{title}, #{author}, #{genre}, #{pageCount})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insertBook(Book book);

    // 2. Select a Book by ID
    @Select("SELECT id, title, author, genre, page_count FROM mybatis.books WHERE id = #{id}")
    @Results(id = "BookResultMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "author", column = "author"),
            @Result(property = "genre", column = "genre"),
            @Result(property = "pageCount", column = "page_count")
    })
    Book findBookById(Long id);

    // 3. Select all Books
    @Select("SELECT id, title, author, genre, page_count FROM mybatis.books")
    @ResultMap("BookResultMap")
    List<Book> findAllBooks();


    // 4. Delete a Book by ID
    @Delete("DELETE FROM mybatis.books WHERE id = #{id}")
    void deleteBook(Long id);
}
