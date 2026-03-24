package com.codingnomads.springdata.example.mybatis.resultsandresult;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Book {

    private Long id;

    private String title;

    private String author;

    private String genre;

    // number of pages in the book
    private int pageCount;

    public Book(String title, String author, String genre, int pageCount) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.pageCount = pageCount;
    }
}
