package com.org.NutikasRestoran_service.otsing;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.org.NutikasRestoran_service.broneering.Broneering;
import com.org.NutikasRestoran_service.laud.Laud;

import java.time.LocalDateTime;
import java.util.List;

public class OtsingDTO {

    private boolean valikAknaKoht;
    private boolean valikLigipaasetavus;
    private long valikInimesteArv;
    private String valikTsoon;
    private LocalDateTime valikAeg;
    private List<Laud> responseList;

    // response konstruktor
    public OtsingDTO(boolean valikAknaKoht, boolean valikLigipaasetavus, long valikInimesteArv, String valikTsoon, LocalDateTime valikAeg, List<Laud> responseList) {
        this.valikAknaKoht = valikAknaKoht;
        this.valikLigipaasetavus = valikLigipaasetavus;
        this.valikInimesteArv = valikInimesteArv;
        this.valikTsoon = valikTsoon;
        this.valikAeg = valikAeg;
        this.responseList = responseList;
    };



    public OtsingDTO() {};

    public boolean isValikAknaKoht() {return valikAknaKoht;}

    public void setValikAknaKoht(boolean valikAknaKoht) {this.valikAknaKoht = valikAknaKoht;}

    public boolean isValikLigipääsetavus() {return valikLigipaasetavus;}

    public void setValikLigipääsetavus(boolean valikLigipääsetavus) {this.valikLigipaasetavus = valikLigipääsetavus;}

    public long getValikInimesteArv() {return valikInimesteArv;}

    public void setValikInimesteArv(long valikInimesteArv) {this.valikInimesteArv = valikInimesteArv;}

    public String getValikTsoon() {return valikTsoon;}

    public void setValikTsoon(String valikTsoon) {this.valikTsoon = valikTsoon;}

    public LocalDateTime getValikAeg() {return valikAeg;}

    public void setValikAeg(LocalDateTime valikAeg) {this.valikAeg = valikAeg;}

    public List<Laud> getResponseList() {
        return responseList;
    }

    public void setResponseList(List<Laud> responseList) {
        this.responseList = responseList;
    }
}
