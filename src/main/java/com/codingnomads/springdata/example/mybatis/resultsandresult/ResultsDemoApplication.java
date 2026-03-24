/* CodingNomads (C)2024 */
package com.codingnomads.springdata.example.mybatis.resultsandresult;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ResultsDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResultsDemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadInitialData(SongMapper songMapper,BookMapper bookMapper) {
        return (args) -> {
            // notice the setter names have changed to match Java naming conventions
            Song song1 = new Song();
            song1.setName("Minnesota, WI");
            song1.setAlbumName("Bon Iver");
            song1.setArtistName("Bon Iver");
            song1.setSongLength(232);

            Song song2 = new Song();
            song2.setName("Post Humorous");
            song2.setAlbumName("Orca");
            song2.setArtistName("Gus Dapperton");
            song2.setSongLength(279);

            songMapper.insertNewSong(song1);
            songMapper.insertNewSong(song2);

            Song song3 = songMapper.getSongById(1L);
            System.out.println(song3.toString());

            // 1. Insert a new Book
            Book book = new Book("1984", "George Orwell", "Dystopian", 328);
            bookMapper.insertBook(book);
            System.out.println("Inserted Book with ID: " + book.getId());

           // 2. Find Book by ID
            Book foundBook = bookMapper.findBookById(book.getId());
            System.out.println("Found Book: " + foundBook);

           // 3. Find all Books
            List<Book> allBooks = bookMapper.findAllBooks();
            System.out.println("All Books in DB:");
            allBooks.forEach(System.out::println);

            // 4. Delete Book by ID
            bookMapper.deleteBook(book.getId());
            System.out.println("Deleted Book with ID: " + book.getId());

            // Verify deletion
            List<Book> afterDelete = bookMapper.findAllBooks();
            System.out.println("Books after deletion:");
            afterDelete.forEach(System.out::println);

        };
    }
}
