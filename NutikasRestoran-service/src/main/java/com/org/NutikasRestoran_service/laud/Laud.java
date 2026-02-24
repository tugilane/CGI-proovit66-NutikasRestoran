package com.org.NutikasRestoran_service.laud;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Laud {

    @Id
    private long id;
    private String bronnitud; // timestamp
    private int privaatne;
    private int aknaKoht;
    private int ligipääsetavus;
    private int inimesteArv;
    private String tsoon;

    public Laud(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBronnitud() {
        return bronnitud;
    }

    public void setBronnitud(String bronnitud) {
        this.bronnitud = bronnitud;
    }

    public int getPrivaatne() {
        return privaatne;
    }

    public void setPrivaatne(int privaatne) {
        this.privaatne = privaatne;
    }

    public int getAknaKoht() {
        return aknaKoht;
    }

    public void setAknaKoht(int aknaKoht) {
        this.aknaKoht = aknaKoht;
    }

    public int getLigipääsetavus() {
        return ligipääsetavus;
    }

    public void setLigipääsetavus(int ligipääsetavus) {
        this.ligipääsetavus = ligipääsetavus;
    }

    public int getInimesteArv() {
        return inimesteArv;
    }

    public void setInimesteArv(int inimesteArv) {
        this.inimesteArv = inimesteArv;
    }

    public String getTsoon() {
        return tsoon;
    }

    public void setTsoon(String tsoon) {
        this.tsoon = tsoon;
    }

    public Laud(long id, String bronnitud, int privaatne, int aknaKoht, int ligipääsetavus, int inimesteArv, String tsoon) {
        this.id = id;
        this.bronnitud = bronnitud;
        this.privaatne = privaatne;
        this.aknaKoht = aknaKoht;
        this.ligipääsetavus = ligipääsetavus;
        this.inimesteArv = inimesteArv;
        this.tsoon = tsoon;
    }
}
