package com.org.NutikasRestoran_service.broneering;

import com.org.NutikasRestoran_service.broneering.Broneering;
import com.org.NutikasRestoran_service.broneering.BroneeringRepository;
import com.org.NutikasRestoran_service.laud.Laud;
import com.org.NutikasRestoran_service.laud.LaudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BroneeringService {


    @Autowired
    private BroneeringRepository broneeringRepository;

    public List<Broneering> koikBroneeringud() {
        return broneeringRepository.findAll();
    }

    public Broneering save(Broneering laud) {
        return broneeringRepository.save(laud);
    }

    public Optional<Broneering> valiBroneering(long id) {
        return broneeringRepository.findById(id);
    }

    public BroneeringDTO looBroneeringDTO (Broneering broneering) {
        return new BroneeringDTO(
                broneering.getId(),
                broneering.getLaud().getId(),
                broneering.getNimi(),
                broneering.getAeg()
        );
    }

    public List<BroneeringDTO> looBroneeringDTOdLauaPõhjal(Laud laud){
        List<BroneeringDTO> lauaBroneeringDTOd = new java.util.ArrayList<>();
        for (Broneering broneering : laud.getBronnitud()) {
            lauaBroneeringDTOd.add(looBroneeringDTO(broneering));
        }
        return lauaBroneeringDTOd;
    }


}