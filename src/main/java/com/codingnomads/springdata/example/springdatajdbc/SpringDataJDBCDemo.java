/* CodingNomads (C)2024 */
package com.codingnomads.springdata.example.springdatajdbc;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class SpringDataJDBCDemo implements CommandLineRunner {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJDBCDemo.class);
    }

    @Override
    public void run(String... strings) {

        try {
            // create employee table using the JdbcTemplate method "execute"
            jdbcTemplate.execute("CREATE TABLE employees (id BIGINT AUTO_INCREMENT PRIMARY KEY,"
                    + "first_name VARCHAR(255) NOT NULL,last_name  VARCHAR(255) NOT NULL);");
        } catch (Exception e) {
            // nothing
        }

        try {
            // create course table using the JdbcTemplate method "execute"
            jdbcTemplate.execute("CREATE TABLE courses (id BIGINT AUTO_INCREMENT PRIMARY KEY,"
                    + "course_name VARCHAR(255) NOT NULL,instructor_name  VARCHAR(255) NOT NULL);");
        } catch (Exception e) {
            // nothing
        }

        // create a list of first and last names
        List<Object[]> splitUpNames = Stream.of("Java Ninja", "Spring Guru", "Java Guru", "Spring Ninja")
                .map(name -> name.split(" "))
                .collect(Collectors.toList());

        // create a list of course and instructor names
        List<Object[]> splitUpCourseNames = Stream.of("Java James", "Spring Ryan","Java Ryan", "Spring James")
                .map(name -> name.split(" "))
                .collect(Collectors.toList());

        // for each first & last name pair insert an Employee into the database
        for (Object[] name : splitUpNames) {
            jdbcTemplate.execute(
                    String.format("INSERT INTO employees(first_name, last_name) VALUES ('%s','%s')", name[0], name[1]));
        }

        // for each course & instructor name pair insert an Course into the database
        for (Object[] name : splitUpCourseNames) {
            jdbcTemplate.execute(
                    String.format("INSERT INTO courses(course_name, instructor_name) VALUES ('%s','%s')", name[0], name[1]));
        }

        // query the database for Employees with first name Java
        jdbcTemplate
                .query(
                        "SELECT id, first_name, last_name FROM employees WHERE first_name = 'Java'",
                        (rs, rowNum) ->
                                new Employee(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name")))
                // print each found employee to the console
                .forEach(employee -> System.out.println(employee.toString()));

        // query the database for Courses with course name Java
        jdbcTemplate
                .query(
                        "SELECT id, course_name, instructor_name FROM courses WHERE course_name = 'Java'",
                        (rs, rowNum) ->
                                new Course(rs.getLong("id"), rs.getString("course_name"), rs.getString("instructor_name")))
                // print each found course to the console
                .forEach(course -> System.out.println(course.toString()));

        // truncate the table
        jdbcTemplate.execute("TRUNCATE TABLE employees;");
        // delete the table
        jdbcTemplate.execute("DROP TABLE employees");

        // truncate the table
        jdbcTemplate.execute("TRUNCATE TABLE courses;");
        // delete the table
        jdbcTemplate.execute("DROP TABLE courses");

    }
}
