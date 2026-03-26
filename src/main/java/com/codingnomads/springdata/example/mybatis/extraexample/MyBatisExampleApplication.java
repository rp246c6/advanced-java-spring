/* CodingNomads (C)2024 */
package com.codingnomads.springdata.example.mybatis.extraexample;

import com.codingnomads.springdata.example.mybatis.extraexample.mappers.ChapterMapper;
import com.codingnomads.springdata.example.mybatis.extraexample.mappers.ImageMapper;
import com.codingnomads.springdata.example.mybatis.extraexample.mappers.LessonMapper;
import com.codingnomads.springdata.example.mybatis.extraexample.mappers.SectionMapper;
import com.codingnomads.springdata.example.mybatis.extraexample.models.Chapter;
import com.codingnomads.springdata.example.mybatis.extraexample.models.Lesson;
import com.codingnomads.springdata.example.mybatis.extraexample.models.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class MyBatisExampleApplication implements CommandLineRunner {

    /* Before running this app, be sure to:

       * create a new empty schema in the mysql database named "mybatis"

       * execute the SQL found "database_structure.sql" on the mybatis schema

       * update the "spring.datasource.url" property in your application.properties file to
         jdbc:mysql://localhost:3306/mybatis?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC

    */

    @Autowired
    ImageMapper imageMapper;

    @Autowired
    LessonMapper lessonMapper;

    @Autowired
    ChapterMapper chapterMapper;

    @Autowired
    SectionMapper sectionMapper;

    public static void main(String[] args) {
        SpringApplication.run(MyBatisExampleApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {

     // 1. Create Section (Required for Foreign Key)
        Section section = new Section();
        section.setName("MyBatis Extra Examples");
        sectionMapper.insertSection(section);
        Long sectionId = section.getId();

     // 2. Setup the Chapter object
        Chapter newChapter = new Chapter();
        newChapter.setName("Relational Mapping");
        newChapter.setSectionId(sectionId); // Use the ID from your Section insert

        // 3. Insert the object (MyBatis now populates newChapter.id)
        chapterMapper.insertNewChapter(newChapter);

        // 4. Retrieve the non-null ID
        Long chapterId = newChapter.getId();
        System.out.println("Generated Chapter ID: " + chapterId);

        // 5. Create the Lesson object
        String lessonName = "Understanding @Many";
        Lesson lesson = new Lesson();
        lesson.setName(lessonName);
        lesson.setText("This is the lesson content.");
        lesson.setChapterId(chapterId); // ID from the previous step

// 6. Insert the Lesson (This fills lesson.id)
        lessonMapper.insertNewLesson(lesson);

      // 7. Get the ID (It won't be null anymore!)
        Long lessonId = lesson.getId();
        System.out.println("Generated Lesson ID: " + lessonId);
        // 8. Create Image (Param1: Name, Param2: Byte Data)
        String imageName = "diagram.png";
        imageMapper.insertNewImage(imageName, "binary-data".getBytes());

        // 9. Link Image to Lesson (Join Table)
        lessonMapper.addImageToLesson(lessonId, imageName);
        System.out.println("Linked Image '" + imageName + "' to Lesson: " + lessonName);

        // 10. Final Verification (Deep Loading)
        System.out.println("\n--- Verification: Deep Loading Lesson with Images ---");
        Lesson finalCheck = lessonMapper.getLessonById(lessonId);
        System.out.println("Lesson: " + finalCheck.getName());

        if (finalCheck.getImageList() != null) {
            finalCheck.getImageList().forEach(img ->
                    System.out.println("  - Attached Image Found: " + img.getName())
            );
        }


    }
}

