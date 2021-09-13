package software.sagax.baywatch.service;

import software.sagax.baywatch.model.Beach;

import java.util.List;

public interface BeachService {

    Beach addBeach(String name);

    List<Beach> findAll();

    List<String> findAllBeachesWithLifeguardsLessThan(Integer n);

    List<String> findAllByMaxTotalSpeed();

}
