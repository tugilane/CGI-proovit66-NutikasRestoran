package com.org.NutikasRestoran_service.PaevKalendris;

import com.org.NutikasRestoran_service.broneering.Broneering;
import com.org.NutikasRestoran_service.laud.Laud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PaevRepository extends JpaRepository<Paev, Long> {

    boolean existsByAeg(LocalDate aeg);

    @Query("SELECT id FROM Paev WHERE aeg = :aeg") // otsime paeva, id põhjal
    Long findByAeg(@Param("aeg") LocalDate aeg);

    @Query("SELECT broneeringud FROM Paev WHERE aeg = :aeg")
    List<Broneering> findPaevaBroneeringud(@Param("aeg") LocalDate aeg);

    //@Query("SELECT id FROM Paev WHERE aeg = :aeg") // otsime paeva, id põhjal
    //boolean existsAeg(@Param("aeg") LocalDate aeg);



}