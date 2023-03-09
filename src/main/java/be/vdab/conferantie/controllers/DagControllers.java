package be.vdab.conferantie.controllers;

import be.vdab.conferantie.domain.Dag;
import be.vdab.conferantie.domain.Sessie;
import be.vdab.conferantie.exceptions.SessieNietGevondenException;
import be.vdab.conferantie.services.DagService;
import be.vdab.conferantie.services.SessieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("dagen")
public class DagControllers {
    private final DagService dagService;
    private final SessieService sessieService;

    public DagControllers(DagService dagService, SessieService sessieService) {
        this.dagService = dagService;
        this.sessieService = sessieService;
    }

    @GetMapping
    List<Dag> findAll() {
        return dagService.findAll();
    }

    @GetMapping("{id}/sessies")
    List<Sessie> findAllSessiesByDagID(@PathVariable long id) {
        return sessieService.findAllByDagId(id);
    }

    @GetMapping("sessies/{id}")
    Sessie findSessieById(@PathVariable long id) {
        return sessieService.findById(id).orElseThrow(() -> new SessieNietGevondenException(id));
    }

}
