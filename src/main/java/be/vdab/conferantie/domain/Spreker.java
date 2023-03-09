package be.vdab.conferantie.domain;

public class Spreker {
    private final long id;
    private final String voornaam;
    private final String familienaam;
    private final String titel;
    private final String firma;

    public Spreker(long id, String voornaam, String familienaam, String titel, String firma) {
        this.id = id;
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.titel = titel;
        this.firma = firma;
    }

    public Spreker(String voornaam, String familienaam, String titel, String firma) {
        this.id = 0;
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.titel = titel;
        this.firma = firma;
    }

    public long getId() {
        return id;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getFamilienaam() {
        return familienaam;
    }

    public String getTitel() {
        return titel;
    }

    public String getFirma() {
        return firma;
    }
}
