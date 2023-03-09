package be.vdab.conferantie.domain;

import java.time.LocalTime;

public class Sessie {
    private final long id;
    private final String naam;
    private final long dagId;
    private final LocalTime uur;
    private final long sprekerid;
    private int interesses;

    public Sessie(long id, String naam, long dagId, LocalTime uur, long sprekerid, int interesses) {
        this.id = id;
        this.naam = naam;
        this.dagId = dagId;
        this.uur = uur;
        this.sprekerid = sprekerid;
        this.interesses = interesses;
    }

    public Sessie(String naam, long dagId, LocalTime uur, long sprekerid, int interesses) {
        this.id = 0;
        this.naam = naam;
        this.dagId = dagId;
        this.uur = uur;
        this.sprekerid = sprekerid;
        this.interesses = interesses;
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public long getDagId() {
        return dagId;
    }

    public LocalTime getUur() {
        return uur;
    }

    public long getSprekerid() {
        return sprekerid;
    }

    public int getInteresses() {
        return interesses;
    }
}
