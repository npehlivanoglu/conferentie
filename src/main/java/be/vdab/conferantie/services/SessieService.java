package be.vdab.conferantie.services;

import be.vdab.conferantie.domain.Sessie;
import be.vdab.conferantie.repositories.SessieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SessieService {
    private final SessieRepository sessieRepository;

    public SessieService(SessieRepository sessieRepository) {
        this.sessieRepository = sessieRepository;
    }

    public List<Sessie> findAllByDagId(long id) {
        return sessieRepository.findAllByDagId(id);
    }

    public Optional<Sessie> findById(long id) {
        return sessieRepository.findById(id);
    }
}
