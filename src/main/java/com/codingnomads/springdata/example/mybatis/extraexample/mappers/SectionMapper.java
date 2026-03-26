/* CodingNomads (C)2024 */
package com.codingnomads.springdata.example.mybatis.extraexample.mappers;

import com.codingnomads.springdata.example.mybatis.extraexample.models.Section;
import java.util.List;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

@Mapper
public interface SectionMapper {

    // Pass the Section OBJECT, not just a String name
    @Insert("INSERT INTO mybatis.sections (name) VALUES (#{name});")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertSection(Section section);

    @Select("SELECT id, name FROM mybatis.sections WHERE id = #{param1};")
    @Results(
            @Result(
                    property = "chapters",
                    column = "id",
                    javaType = List.class,
                    many =
                            @Many(
                                    select =
                                            "com.codingnomads.springdata.example.mybatis.extraexample.mappers.ChapterMapper.getChaptersBySectionId",
                                    fetchType = FetchType.LAZY)))
    Section getSectionById(Long sectionId);

    @Delete("DELETE FROM mybatis.sections WHERE id = #{id};")
    int deleteSectionById(Long id);
}
