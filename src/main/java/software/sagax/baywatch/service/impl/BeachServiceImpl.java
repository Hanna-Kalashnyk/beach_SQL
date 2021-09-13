package software.sagax.baywatch.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import software.sagax.baywatch.model.Beach;
import software.sagax.baywatch.repository.BeachRepository;
import software.sagax.baywatch.service.BeachService;

import java.util.List;

@Service
@Transactional
public class BeachServiceImpl implements BeachService {

    private final BeachRepository beachRepository;

    public BeachServiceImpl(BeachRepository beachRepository) {
        this.beachRepository = beachRepository;
    }

    @Override
    public Beach addBeach(String name) {
        Beach beach = new Beach();
        beach.setName(name);
        return beachRepository.save(beach);
    }

    @Override
    public List<Beach> findAll() {
        return beachRepository.findAll();
    }

    @Override
    public List<String> findAllBeachesWithLifeguardsLessThan(Integer n) {
        return beachRepository.findAllBeachesWithLifeguardsLessThan(n);
    }

    @Override
    public List<String> findAllByMaxTotalSpeed() {
        return beachRepository.findAllByMaxTotalSpeed();
    }

}
