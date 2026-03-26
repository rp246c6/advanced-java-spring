/* CodingNomads (C)2024 */
package com.codingnomads.springdata.example.mybatis.extraexample.mappers;

import com.codingnomads.springdata.example.mybatis.extraexample.models.Chapter;
import java.util.LinkedList;
import java.util.List;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

@Mapper
public interface ChapterMapper {

    // Change this to use the Chapter object
    @Insert("INSERT INTO mybatis.chapters (name, section_id) VALUES (#{name}, #{sectionId});")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertNewChapter(Chapter chapter);

    @Select("SELECT id, name FROM mybatis.chapters WHERE id = #{param1}")
    @Results(
            @Result(
                    column = "id",
                    property = "lessons",
                    javaType = List.class,
                    many =
                            @Many(
                                    select =
                                            "com.codingnomads.springdata.example.mybatis.extraexample.mappers.LessonMapper.getLessonByChapterId",
                                    fetchType = FetchType.LAZY)))
    Chapter getByChapterId(Long id);

    @Select("SELECT id, name FROM mybatis.chapters WHERE section_id = #{param1};")
    @Results(
            @Result(
                    column = "id",
                    property = "lessons",
                    javaType = List.class,
                    many =
                            @Many(
                                    select =
                                            "com.codingnomads.springdata.example.mybatis.extraexample.mappers.LessonMapper.getLessonByChapterId",
                                    fetchType = FetchType.LAZY)))
    LinkedList<Chapter> getChaptersBySectionId(Long sectionId);

    @Delete("DELETE FROM mybatis.chapters WHERE id = #{param1};")
    void deleteChapterById(Long chapterId);
}
