package com.codingnomads.springdata.example.mybatis.oneandmany.mappers;

import com.codingnomads.springdata.example.mybatis.oneandmany.models.Album;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AlbumMapper {

    @Select("SELECT * FROM mybatis.albums WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "artist", column = "artist_id",
                    one = @One(select = "com.codingnomads.springdata.example.mybatis.oneandmany.mappers.ArtistMapper.getArtistByIdWithoutSongs")),
            @Result(property = "songs", column = "id",
                    many = @Many(select = "com.codingnomads.springdata.example.mybatis.oneandmany.mappers.SongMapper.getSongsByAlbumId"))
    })
    Album getAlbumById(Long id);

    @Select("SELECT * FROM mybatis.albums WHERE artist_id = #{artistId}")
    List<Album> getAlbumsByArtistId(Long artistId);

    @Insert("INSERT INTO mybatis.albums (name, year, artist_id) " +
            "VALUES (#{name}, #{year}, #{artist.id})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertAlbum(Album album);
}
