package com.org.NutikasRestoran_service.laud;

import com.org.NutikasRestoran_service.broneering.BroneeringDTO;

import java.util.List;


public class LaudDTO{
    private long id;
    private BroneeringDTO[] bronnitud;
    private int privaatne;
    private int aknaKoht;
    private int ligipääsetavus;
    private int inimesteArv;
    private String tsoon;

    public LaudDTO(long id, BroneeringDTO[] bronnitud, int privaatne, int aknaKoht, int ligipääsetavus, int inimesteArv, String tsoon) {
        this.id = id;
        this.bronnitud = bronnitud;
        this.privaatne = privaatne;
        this.aknaKoht = aknaKoht;
        this.ligipääsetavus = ligipääsetavus;
        this.inimesteArv = inimesteArv;
        this.tsoon = tsoon;
    }
}

