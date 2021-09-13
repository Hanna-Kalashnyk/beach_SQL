package software.sagax.baywatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import software.sagax.baywatch.model.Lifeguard;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LifeguardRepository extends JpaRepository<Lifeguard, Long> {

    //Get a list of the names of the lifeguards who have faster speed than their bosses
    @Query(value = "SELECT l.name FROM PUBLIC.LIFEGUARD AS l " +
            "INNER JOIN PUBLIC.LIFEGUARD AS boss " +
            "ON l.BOSS_ID = boss.ID " +
            "WHERE l.SPEED > boss.SPEED",
            nativeQuery = true)
    List<String> findWhenSpeedGreaterThanBoss();

    //Get a list of the names of the fastest lifeguards
    @Query(value = "SELECT l.name FROM lifeguard l " +
            "WHERE l.SPEED = (SELECT MAX(SPEED) FROM lifeguard)",
            nativeQuery = true)
    List<String> findFastestLifeguards();


    @Query(value = "SELECT boss.NAME FROM PUBLIC.LIFEGUARD AS boss " +
            "INNER JOIN PUBLIC.LIFEGUARD AS l " +
            "ON boss.ID = l.BOSS_ID " +
            "GROUP BY l.BOSS_ID " +
            "HAVING COUNT(l.ID) >= 1",
            nativeQuery = true)
    List<String> findBosses();

    void removeByName(String name);

    Optional<Lifeguard> findByName(String name);

    @Modifying
    @Query(value = "UPDATE PUBLIC.LIFEGUARD l " +
            "SET l.SPEED = :speed " +
            "WHERE l.NAME = :name",
            nativeQuery = true)
    void put(String name, Integer speed);

}
