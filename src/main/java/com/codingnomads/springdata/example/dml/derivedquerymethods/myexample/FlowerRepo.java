package com.codingnomads.springdata.example.dml.derivedquerymethods.myexample;

import com.codingnomads.springdata.example.dml.derivedquerymethods.plantexample.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlowerRepo extends JpaRepository<Flower, Long> {

    //////////////// INTRODUCER VARIATIONS ////////////////

    List<Flower> findByName(String name);

    List<Flower> queryByName(String name);

    List<Flower> readByName(String name);

    List<Flower> getByName(String name);

    int countByNameIs(String name);

    //////////////// SIMILARITY KEYWORDS ////////////////

    // find all flowers with names starting with namePrefix
    List<Flower> findByNameStartingWith(String namePrefix);

    // find all flowers with names ending in nameSuffix
    List<Flower> findByNameEndingWith(String nameSuffix);

    // find all flowers with names containing pattern
    List<Flower> findByNameContaining(String pattern);

    //////////////// EQUALITY KEYWORDS ////////////////

    // Using Is vs. findByName(String name)
    List<Flower> findByNameIs(String name);

    // IsNot
    List<Flower> findByNameIsNot(String name);

    // IsNull
    List<Flower> findBySunTypeIsNull();

    // IsNotNull
    List<Flower> findBySunTypeIsNotNull();

    // use IsTrue and IsFalse instead of findByFragranceBearing(boolean fragranceBearing);
    List<Flower> findByFragranceBearingIsTrue();

    List<Flower> findByFragranceBearingIsFalse();

    //////////////// MORE EQUALITY KEYWORDS ////////////////

    List<Flower> findByNumDaysTillDayOfBloomLessThan(int numDays);

    List<Flower> findByNumDaysTillDayOfBloomLessThanEqual(int numDays);

    List<Flower> findByNumDaysTillDayOfBloomGreaterThan(int numDays);

    List<Flower> findByNumDaysTillDayOfBloomGreaterThanEqual(int numDays);

}
