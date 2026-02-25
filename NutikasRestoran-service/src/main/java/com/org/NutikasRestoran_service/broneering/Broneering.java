package com.org.NutikasRestoran_service.broneering;

import com.org.NutikasRestoran_service.laud.Laud;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table
public class Broneering {

        @Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
        private long id;

        @ManyToOne
        @JoinColumn
        private Laud laud;

        @Column
        private String nimi;

        @Column
        private LocalDateTime aeg;

    public Broneering(long id, Laud laud, String nimi, LocalDateTime aeg) {
        this.id = id;
        this.laud = laud;
        this.nimi = nimi;
        this.aeg = aeg;
    }

    public Broneering() {}


    public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getNimi() {
            return nimi;
        }

        public void setNimi(String nimi) {
            this.nimi = nimi;
        }

        public Laud getLaud() {
            return laud;
        }

        public void setLaud(Laud laud) {
            this.laud = laud;
        }

        public LocalDateTime getAeg() {
            return aeg;
        }

        public void setAeg(LocalDateTime aeg) {
            this.aeg = aeg;
        }
}
