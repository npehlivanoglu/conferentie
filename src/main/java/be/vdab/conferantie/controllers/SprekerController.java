package be.vdab.conferantie.controllers;

import be.vdab.conferantie.domain.Spreker;
import be.vdab.conferantie.exceptions.SprekerNietGevondenException;
import be.vdab.conferantie.services.SprekerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sprekers")
public class SprekerController {
    private final SprekerService sprekerService;

    public SprekerController(SprekerService sprekerService) {
        this.sprekerService = sprekerService;
    }

    @GetMapping("{id}")
    Spreker findById(@PathVariable long id){
        return sprekerService.findById(id).orElseThrow(()-> new SprekerNietGevondenException(id));
    }
}
