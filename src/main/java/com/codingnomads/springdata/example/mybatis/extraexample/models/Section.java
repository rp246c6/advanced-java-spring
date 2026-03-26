/* CodingNomads (C)2024 */
package com.codingnomads.springdata.example.mybatis.extraexample.models;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Section {

    private Long id;
    private String name;
    private List<Chapter> chapters;

    public Section(String name, List<Chapter> chapters, Long id) {
        this.name = name;
        this.chapters = chapters;
        this.id = id;
    }
}
