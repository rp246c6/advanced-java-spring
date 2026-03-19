/* CodingNomads (C)2024 */
package com.codingnomads.springdata.example.dml.usingqueryannotation.repositories;

import com.codingnomads.springdata.example.dml.usingqueryannotation.models.Plant;
import com.codingnomads.springdata.example.dml.usingqueryannotation.models.SoilType;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SoilTypeRepo extends JpaRepository<SoilType, Long> {

    //////////////// NO VARIABLE JPQL AND SQL ////////////////
    // use JPQL to query only the soil type
    @Query("SELECT st FROM SoilType st")
    List<SoilType> getSoilType();

    // use JPQL to query only dry soil type
    @Query("SELECT st FROM SoilType st WHERE dry = true")
    List<SoilType> findDrySoilType();

    // use native SQL to query the soilType with id 1 without its plant
    @Query(value = "SELECT * FROM soil_types WHERE id = 1", nativeQuery = true)
    SoilType findSoilTypeWithIdOne();

    //////////////// SORTING USING @QUERY ////////////////

    // get all dry soil type with the option to sort them
    @Query("SELECT st FROM SoilType st WHERE dry = true")
    List<SoilType> getDrySoilType(Sort sort);

    //////////////// VARIABLE SQL AND JPQL ////////////////

    @Query("SELECT st FROM SoilType st WHERE name = ?1")
    SoilType getSoilTypeByName(String soilTypeName);

}
