package com.codingnomads.springdata.example.mybatis.oneandmany.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@ToString(exclude = "songs")
public class Album {
    private Long id;
    private String name;
    private String year;
    private Artist artist; // Many-to-One
    private List<Song> songs; // One-to-Many
}
