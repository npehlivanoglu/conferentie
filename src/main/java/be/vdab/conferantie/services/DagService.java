package be.vdab.conferantie.services;

import be.vdab.conferantie.domain.Dag;
import be.vdab.conferantie.repositories.DagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DagService {
    private final DagRepository dagRepository;

    public DagService(DagRepository dagRepository) {
        this.dagRepository = dagRepository;
    }

    public List<Dag> findAll() {
        return dagRepository.findAll();
    }
}
