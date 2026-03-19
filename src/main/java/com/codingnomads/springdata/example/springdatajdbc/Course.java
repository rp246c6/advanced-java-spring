/* CodingNomads (C)2024 */
package com.codingnomads.springdata.example.springdatajdbc;

import lombok.Data;

@Data
public class Course {
    private long id;
    private String courseName, instructorName;

    public Course(long id, String courseName, String instructorName) {
        this.id = id;
        this.courseName = courseName;
        this.instructorName = instructorName;
    }
}
