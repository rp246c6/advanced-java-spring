/* CodingNomads (C)2024 */
package com.codingnomads.springdata.example.mybatis.mapperslesson;

import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MyBatisDemoApplication {

    /* Before running this app, be sure to:

       * create a new empty schema in the mysql database named "mybatis"

       * execute the SQL found "songs_table.sql" on the mybatis schema

       * update the "spring.datasource.url" property in your application.properties file to
         jdbc:mysql://localhost:3306/mybatis?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC

    */

    public static void main(String[] args) {
        SpringApplication.run(MyBatisDemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadInitialData(SongMapper songMapper) {
        return (args) -> {
            Song song1 = new Song();
            song1.setName("Minnesota, WI");
            song1.setAlbum_name("Bon Iver");
            song1.setArtist_name("Bon Iver");
            song1.setSong_length(232);

            Song song2 = new Song();
            song2.setName("Post Humorous");
            song2.setAlbum_name("Orca");
            song2.setArtist_name("Gus Dapperton");
            song2.setSong_length(279);

            // Third sample song
            Song song3 = new Song();
            song3.setName("Yellow");
            song3.setAlbum_name("Parachutes");
            song3.setArtist_name("Coldplay");
            song3.setSong_length(269);

           // Fourth sample song
            Song song4 = new Song();
            song4.setName("Smells Like Teen Spirit");
            song4.setAlbum_name("Nevermind");
            song4.setArtist_name("Nirvana");
            song4.setSong_length(301);

            // Fifth sample song
            Song song5 = new Song();
            song5.setName("Bohemian Rhapsody");
            song5.setAlbum_name("A Night at the Opera");
            song5.setArtist_name("Queen");
            song5.setSong_length(354);


            songMapper.insertNewSong(song1);
            songMapper.insertNewSong(song2);

            //Persist using mapper
            songMapper.insertNewSong(song3);
            songMapper.insertNewSong(song4);
            songMapper.insertNewSong(song5);

            Song song6 = songMapper.getSongById(1L);

            List<Song> longSongs = songMapper.getSongsWithLengthGreaterThan(250);

            longSongs.forEach(System.out::println);

            System.out.println(song6.toString());

            // 1. Read by ID
            Song fetchedById = songMapper.getSongById(song3.getId());
            System.out.println("Fetched by ID: " + fetchedById.getName());

            // 2. Read by Name
            List<Song> songsByName = songMapper.getSongsByName("Yellow");
            songsByName.forEach(s -> System.out.println("Found by name: " + s.getName()));

            // 3. Read by Length Greater Than
            List<Song> lengthySongs = songMapper.getSongsWithLengthGreaterThan(300);
            lengthySongs.forEach(s -> System.out.println("Long song: " + s.getName()));

            // 4. Read by Album and Artist
            List<Song> albumArtistSongs = songMapper.getSongsByAlbumAndArtist("Bon Iver", "Bon Iver");
            albumArtistSongs.forEach(s -> System.out.println("Album+Artist match: " + s.getName()));

            // 5. Read by Artist
            List<Song> artistSongs = songMapper.getSongsByArtist("Queen");
            artistSongs.forEach(s -> System.out.println("By artist Queen: " + s.getName()));

            // 6. Update a Song
            song4.setAlbum_name("Parachutes (Deluxe)");
            songMapper.updateSong(song4);
            System.out.println("Updated album for song4");

            // 7. Delete by ID
            songMapper.deleteSongById(song4.getId());
            System.out.println("Deleted Nirvana song by ID");

            // 8. Delete by Album and Artist
            songMapper.deleteSongsByAlbumAndArtist("Queen", "A Night at the Opera");
            System.out.println("Deleted Queen songs by album+artist");
        };
    }
}
