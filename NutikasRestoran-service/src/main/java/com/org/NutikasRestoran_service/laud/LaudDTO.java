package com.org.NutikasRestoran_service.laud;

import com.org.NutikasRestoran_service.broneering.Broneering;
import com.org.NutikasRestoran_service.broneering.BroneeringDTO;

import java.util.List;


public class LaudDTO{

    private long id;
    private List<BroneeringDTO> bronnitud;
    private boolean privaatne;
    private boolean aknaKoht;
    private boolean ligipääsetavus;
    private long inimesteArv;
    private String tsoon;

    public LaudDTO(long id, List<BroneeringDTO> bronnitud, boolean privaatne, boolean aknaKoht, boolean ligipääsetavus, long inimesteArv, String tsoon) {
        this.id = id;
        this.bronnitud = bronnitud;
        this.privaatne = privaatne;
        this.aknaKoht = aknaKoht;
        this.ligipääsetavus = ligipääsetavus;
        this.inimesteArv = inimesteArv;
        this.tsoon = tsoon;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<BroneeringDTO> getBronnitud() {
        return bronnitud;
    }

    public void setBronnitud(List<BroneeringDTO> bronnitud) {
        this.bronnitud = bronnitud;
    }

    public boolean getPrivaatne() {
        return privaatne;
    }

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

    public long getInimesteArv() {return inimesteArv;}

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

