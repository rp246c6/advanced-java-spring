package com.codingnomads.springdata.example.dml.derivedquerymethods.myexample;

import com.codingnomads.springdata.example.dml.derivedquerymethods.plantexample.Plant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class FlowerApplication implements CommandLineRunner {

    @Autowired
    FlowerRepo flowerRepo;

    public static void main(String[] args) {
        SpringApplication.run(FlowerApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {

        Flower rose = Flower.builder()
                .name("rose")
                .fragranceBearing(true)
                .sunType("full sun")
                .numDaysTillDayOfBloom(60)
                .build();

        Flower marigold = Flower.builder()
                .name("marigold")
                .fragranceBearing(true)
                .sunType("full sun")
                .numDaysTillDayOfBloom(55)
                .build();

        Flower tulips = Flower.builder()
                .name("tulips")
                .fragranceBearing(false)
                .sunType("full sun")
                .numDaysTillDayOfBloom(40)
                .build();

        flowerRepo.save(rose);
        flowerRepo.save(marigold);
        flowerRepo.save(tulips);

        // DEMONSTRATE USE OF DERIVED QUERY METHODS

        System.out.println("\n********* findByName() *********");
        List<Flower> flower1 = flowerRepo.findByName("rose");
        flower1.forEach(System.out::println);

        System.out.println("\n********* findByFragranceBearingIsFalse *********");
        List<Flower> flower2 = flowerRepo.findByFragranceBearingIsFalse();
        flower2.forEach(System.out::println);

        System.out.println("\n********* findByNameEndingWith() *********");
        List<Flower> flower3 = flowerRepo.findByNameEndingWith("tulip");
        flower3.forEach(System.out::println);



    }
}
