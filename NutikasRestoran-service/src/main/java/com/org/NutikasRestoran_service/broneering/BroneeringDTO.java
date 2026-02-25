package com.org.NutikasRestoran_service.broneering;

public class BroneeringDTO {


    private long id;

    private long laud;

    private String nimi;

    private String aeg;

    public BroneeringDTO(long id, long laud, String nimi, String aeg) {
        this.id = id;
        this.laud = laud;
        this.nimi = nimi;
        this.aeg = aeg;
    }
}
