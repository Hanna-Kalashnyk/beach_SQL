package software.sagax.baywatch.service;


import javassist.NotFoundException;
import software.sagax.baywatch.model.Beach;
import software.sagax.baywatch.model.Lifeguard;

import java.util.List;
import java.util.Map;

public interface LifeguardService {

    List<Lifeguard> findAll();

    Lifeguard addLifeguard(String name, Integer speed, Beach beach, Lifeguard boss);

    Lifeguard addLifeguard(Lifeguard lifeguard);

    List<String> findWhenSpeedGreaterThanBoss();

    List<String> findFastestLifeguards();

    List<String> findBosses();

    void fireLifeguard(String name) throws NotFoundException;

    void changeSpeed(String name, Integer speed) throws NotFoundException;

    Map<Integer, Long> groupAndCountBySpeed();

}
