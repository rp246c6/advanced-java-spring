package com.codingnomads.springdata.example.dml.usingqueryannotation;

import com.codingnomads.springdata.example.dml.usingqueryannotation.models.SoilType;
import com.codingnomads.springdata.example.dml.usingqueryannotation.repositories.SoilTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SoilTypeService {

    @Autowired
    SoilTypeRepo soilTypeRepo;

    @Transactional(readOnly = true)
    public void getSoilTypeDetails () {

            System.out.println("SORTED DRY SOIL TYPE");
            List<SoilType> drySoilTypesSorted = soilTypeRepo.getDrySoilType(Sort.by(Sort.Order.desc("id")));

            for (SoilType soilType : drySoilTypesSorted) {
                System.out.println(soilType.toString());
            }

            System.out.println("GET ONLY SOIL TYPE ");
            List<SoilType> soilTypes = soilTypeRepo.getSoilType();

            for (SoilType soilType : soilTypes) {
                System.out.println(soilType.toString());
            }

            System.out.println("GET ONLY DRY SOIL TYPE ");
            List<SoilType> drySoilTypes = soilTypeRepo.findDrySoilType();

            for (SoilType soilType : drySoilTypes) {
                System.out.println(soilType.toString());
            }

            System.out.println("FIND SOIL TYPE WITH ID ONE ");
            SoilType soilTypeWithOneId = soilTypeRepo.findSoilTypeWithIdOne();

            System.out.println(soilTypeWithOneId.toString());

            System.out.println("FIND SOIL TYPE BY NAME  ");
            SoilType soilTypeByName = soilTypeRepo.getSoilTypeByName("tester");

            System.out.println(soilTypeByName.toString());

        }

}
