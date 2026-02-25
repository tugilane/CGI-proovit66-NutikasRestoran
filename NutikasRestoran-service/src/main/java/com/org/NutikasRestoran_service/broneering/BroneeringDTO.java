package com.org.NutikasRestoran_service.broneering;

import java.time.LocalDateTime;

public class BroneeringDTO {

    private long id;
    private long laud;
    private String nimi;
    private LocalDateTime aeg;

    public BroneeringDTO(long id, long laud, String nimi, LocalDateTime aeg) {
        this.id = id;
        this.laud = laud;
        this.nimi = nimi;
        this.aeg = aeg;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLaud() {
        return laud;
    }

    public void setLaud(long laud) {
        this.laud = laud;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public LocalDateTime getAeg() {
        return aeg;
    }

    public void setAeg(LocalDateTime aeg) {
        this.aeg = aeg;
    }
}
