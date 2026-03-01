package com.org.NutikasRestoran_service.PaevKalendris;

import com.org.NutikasRestoran_service.broneering.Broneering;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PaevService {

    private final PaevRepository paevRepository;
    //private final LoadData loadData;

    public PaevService(PaevRepository paevRepository/*, LoadData loadData*/) {
        this.paevRepository = paevRepository;
        //this.loadData = loadData;
    }

    public Paev save(Paev paev) {
        return paevRepository.save(paev);
    }

    public Optional<Paev> valiPaevById(long id) {
        return paevRepository.findById(id);
    }

    public Long valiPaevByAeg(LocalDate date) {
        return paevRepository.findByAeg(date);
    }

    public List<Broneering> getPaevBroneeringud(LocalDate date) {
        return paevRepository.findPaevaBroneeringud(date);
    }



    public boolean kasPäevEksisteerib(LocalDate date) {
/*        System.out.println(date);
        System.out.println(valiPaev(1).get().getAeg());*/
        if (paevRepository.existsByAeg(date)){
            return true;
        }
        return false;
    }

    public void lisaBroneeringList(Paev paev, List<Broneering> koguPaevBroneeringud) {
        paev.setBroneeringud(koguPaevBroneeringud);
        paevRepository.save(paev);
    }
}
