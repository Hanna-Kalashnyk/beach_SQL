package software.sagax.baywatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import software.sagax.baywatch.model.Beach;

import java.util.List;

@Repository
public interface BeachRepository extends JpaRepository<Beach, Long> {

    //Get a list of the names of the beaches where the number of lifeguards does not exceed N people
    @Query(value = "SELECT b.NAME " +
            "FROM PUBLIC.BEACH AS b " +
            "INNER JOIN PUBLIC.LIFEGUARD AS l ON b.ID = l.BEACH_ID " +
            "GROUP BY b.NAME " +
            "HAVING COUNT(l.ID) <= :n", nativeQuery = true)
    List<String> findAllBeachesWithLifeguardsLessThan(Integer n);

    //Get a list of beach names with the maximum overall speed of lifeguards
    //For example overall speed of Malibu beach is 14
    @Query(value = "SELECT b.NAME " +
            "FROM PUBLIC.BEACH AS b " +
            "INNER JOIN PUBLIC.LIFEGUARD AS l ON b.ID = l.BEACH_ID " +
            "GROUP BY b.NAME " +
            "HAVING SUM(l.SPEED) = (SELECT SUM(ll.SPEED) FROM PUBLIC.BEACH AS bb " +
            "INNER JOIN PUBLIC.LIFEGUARD AS ll ON bb.ID = ll.BEACH_ID " +
            "GROUP BY bb.NAME ORDER BY SUM(l.SPEED) DESC limit 1)",
            nativeQuery = true)
    List<String> findAllByMaxTotalSpeed();

}
