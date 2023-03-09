package be.vdab.conferantie.domain;

import java.time.LocalDate;

public class Dag {
    private final long id;
    private final LocalDate datum;

    public Dag(long id, LocalDate datum) {
        this.id = id;
        this.datum = datum;
    }

    public Dag(LocalDate datum) {
        this.id = 0;
        this.datum = datum;
    }

    public long getId() {
        return id;
    }

    public LocalDate getDatum() {
        return datum;
    }
}
