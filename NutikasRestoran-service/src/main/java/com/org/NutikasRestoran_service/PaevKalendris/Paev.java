package com.org.NutikasRestoran_service.PaevKalendris;

import com.org.NutikasRestoran_service.broneering.Broneering;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
public class Paev {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @OneToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn
    private List<Broneering> broneeringud;

    @Column
    private LocalDate aeg;


    public Paev(long id, List<Broneering> broneeringud, LocalDate aeg) {
        this.id = id;
        this.broneeringud = broneeringud;
        this.aeg = aeg;
    }

    public Paev() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Broneering> getBroneeringud() {
        return broneeringud;
    }

    public void setBroneeringud(List<Broneering> broneeringud) {
        this.broneeringud = broneeringud;
    }

    public LocalDate getAeg() {
        return aeg;
    }

    public void setAeg(LocalDate aeg) {
        this.aeg = aeg;
    }
}
