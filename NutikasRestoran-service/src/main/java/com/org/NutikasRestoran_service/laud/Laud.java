package com.org.NutikasRestoran_service.laud;

import com.org.NutikasRestoran_service.broneering.Broneering;
import jakarta.persistence.*;
import tools.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

@Entity
@Table
public class Laud {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @OneToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn
    private List<Broneering> bronnitud;

    @Column
    private boolean privaatne;

    @Column
    private boolean aknaKoht;

    @Column
    private boolean ligipääsetavus;

    @Column
    private long inimesteArv;

    @Column
    private String tsoon;

    public Laud(long id, List<Broneering> bronnitud, boolean privaatne, boolean aknaKoht, boolean ligipääsetavus, long inimesteArv, String tsoon) {
        this.id = id;
        this.bronnitud = bronnitud;
        this.privaatne = privaatne;
        this.aknaKoht = aknaKoht;
        this.ligipääsetavus = ligipääsetavus;
        this.inimesteArv = inimesteArv;
        this.tsoon = tsoon;
    }

    public Laud(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Broneering> getBronnitud() {
        return bronnitud;
    }

    public void setBronnitud(List<Broneering> bronnitud) {
        this.bronnitud = bronnitud;
    }

    public boolean getPrivaatne() {return privaatne;}

    public void setPrivaatne(boolean privaatne) {
        this.privaatne = privaatne;
    }

    public boolean getAknaKoht() {
        return aknaKoht;
    }

    public void setAknaKoht(boolean aknaKoht) {
        this.aknaKoht = aknaKoht;
    }

    public boolean getLigipääsetavus() {
        return ligipääsetavus;
    }

    public void setLigipääsetavus(boolean ligipääsetavus) {
        this.ligipääsetavus = ligipääsetavus;
    }

    public long getInimesteArv() {
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

}
