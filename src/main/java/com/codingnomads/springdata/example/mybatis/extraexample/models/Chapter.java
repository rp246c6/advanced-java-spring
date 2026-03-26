/* CodingNomads (C)2024 */
package com.codingnomads.springdata.example.mybatis.extraexample.models;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Chapter {

    private Long id;
    private String name;
    private Long sectionId;
    private List<Lesson> lessons;

    public Chapter(Long id, String name, Long sectionId, List<Lesson> lessons) {
        this.id = id;
        this.name = name;
        this.sectionId =sectionId;
        this.lessons = lessons;
    }
}
