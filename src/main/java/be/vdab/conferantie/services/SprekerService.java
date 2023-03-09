package be.vdab.conferantie.services;

import be.vdab.conferantie.domain.Spreker;
import be.vdab.conferantie.repositories.SprekerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SprekerService {
    private final SprekerRepository sprekerRepository;

    public SprekerService(SprekerRepository sprekerRepository) {
        this.sprekerRepository = sprekerRepository;
    }

    public Optional<Spreker> findById(long id) {
        return sprekerRepository.findById(id);
    }
}
