/* CodingNomads (C)2024 */
package com.codingnomads.springdata.example.querydsl;

import com.codingnomads.springdata.example.querydsl.models.*;
import com.codingnomads.springdata.example.querydsl.repository.AreaRepository;
import com.codingnomads.springdata.example.querydsl.repository.RouteRepository;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@RequiredArgsConstructor
// @EnableJpaRepositories("com.codingnomads.springdata.example.querydsl.repository")
public class QueryDSLDemo implements CommandLineRunner {

    private final RouteRepository routeRepository;
    private final AreaRepository areaRepository;
    private final EntityManager entityManager;

    public static void main(String[] args) {
        SpringApplication.run(QueryDSLDemo.class);
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {

        QRoute qRoute = QRoute.route;

        final List<Area> areas = areaRepository.saveAll(Arrays.asList(
                Area.builder().code("A").name("A").build(),
                Area.builder().code("B").name("B").build(),
                Area.builder().code("C").name("C").build(),
                Area.builder().code("D").name("D").build(),
                Area.builder().code("E").name("E").build(),
                Area.builder().code("F").name("F").build(),
                Area.builder().code("G").name("G").build()));

        final List<Route> routes = routeRepository.saveAll(Arrays.asList(
                Route.builder()
                        .code("A-B")
                        .name("A-B")
                        .origin(areaRepository.findByCode("A"))
                        .destination(areaRepository.findByCode("B"))
                        .build(),
                Route.builder()
                        .code("B-C")
                        .name("B-C")
                        .origin(areaRepository.findByCode("B"))
                        .destination(areaRepository.findByCode("C"))
                        .build(),
                Route.builder()
                        .code("C-D")
                        .name("C-D")
                        .origin(areaRepository.findByCode("C"))
                        .destination(areaRepository.findByCode("D"))
                        .build(),
                Route.builder()
                        .code("D-E")
                        .name("D-E")
                        .origin(areaRepository.findByCode("D"))
                        .destination(areaRepository.findByCode("E"))
                        .build(),
                Route.builder()
                        .code("E-F")
                        .name("E-F")
                        .origin(areaRepository.findByCode("E"))
                        .destination(areaRepository.findByCode("F"))
                        .build(),
                Route.builder()
                        .code("F-G")
                        .name("F-G")
                        .origin(areaRepository.findByCode("F"))
                        .destination(areaRepository.findByCode("G"))
                        .build()));

        final List<Route> routesByCode = routeRepository.findAllRoutesBySearchQuery(
                SearchQuery.builder().code("A-B").build());
        routesByCode.forEach(System.out::println);

        final List<Route> routesByCodeAndOrigin = routeRepository.findAllRoutesBySearchQuery(
                SearchQuery.builder().code("A-B").origin("A").build());

        routesByCodeAndOrigin.forEach(System.out::println);

        // query the database straight-up without using repository
        QArea qArea = QArea.area;
        JPAQuery<?> query = new JPAQuery<>(entityManager);
        Area area = query.select(qArea).from(qArea).where(qArea.code.eq("A")).fetchOne();
        System.out.println(area);



        // --- AREA QUERIES ---

        // 1. Fetch multiple areas with a name filter and sort
        List<Area> rangeAreas = new JPAQuery<Area>(entityManager)
                .select(qArea)
                .from(qArea)
                .where(qArea.code.between("A", "C"))
                .orderBy(qArea.code.desc())
                .fetch();
        System.out.println("Areas A-C (Desc): " + rangeAreas);

        // 2. Count areas whose name starts with a specific letter
        long countE = new JPAQuery<>(entityManager)
                .from(qArea)
                .where(qArea.name.startsWith("E"))
                .fetchCount();
        System.out.println("Count of areas starting with E: " + countE);

        // 3. Fetch a single result with multiple conditions
        Area specificArea = new JPAQuery<Area>(entityManager)
                .select(qArea)
                .from(qArea)
                .where(qArea.code.eq("G").and(qArea.name.isNotNull()))
                .fetchOne();
        System.out.println("Specific Area G: " + specificArea);

        // --- ROUTE QUERIES ---

        // 1. Join query: Find routes where the Origin Area code is "B"
        List<Route> routesFromB = new JPAQuery<Route>(entityManager)
                .select(qRoute)
                .from(qRoute)
                .innerJoin(qRoute.origin, qArea)
                .where(qArea.code.eq("B"))
                .fetch();
        System.out.println("Routes starting at Area B: " + routesFromB);

        // 2. Complex Where: Routes where code contains a hyphen AND name starts with 'A'
        List<Route> filteredRoutes = new JPAQuery<Route>(entityManager)
                .select(qRoute)
                .from(qRoute)
                .where(qRoute.code.contains("-").and(qRoute.name.startsWith("A")))
                .fetch();
        System.out.println("Routes containing '-' and starting with A: " + filteredRoutes);

        // 3. Projection: Fetch only the codes of all routes (returns List<String>)
        List<String> routeCodes = new JPAQuery<>(entityManager)
                .select(qRoute.code)
                .from(qRoute)
                .fetch();
        System.out.println("All Route Codes: " + routeCodes);
        routeRepository.deleteAll();
        areaRepository.deleteAll();
    }
}
