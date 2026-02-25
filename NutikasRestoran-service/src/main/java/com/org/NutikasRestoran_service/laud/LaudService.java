package com.org.NutikasRestoran_service.laud;

import com.org.NutikasRestoran_service.broneering.Broneering;
import com.org.NutikasRestoran_service.broneering.BroneeringDTO;
import com.org.NutikasRestoran_service.broneering.BroneeringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class LaudService {

    private final BroneeringService broneeringService;

    LaudService(BroneeringService broneeringService) {
        this.broneeringService = broneeringService;
    }

    @Autowired
    private LaudRepository laudRepository;

    public List<Laud> koikLauad() {
        return laudRepository.findAll();
    }

    public Laud save(Laud laud) {
        return laudRepository.save(laud);
    }

    public Optional<Laud> valiLaud(long id) {
        return laudRepository.findById(id);
    }

    public void lisaBroneering(Laud laud, Broneering broneering) {
        List<Broneering> broneeringudbroneeringudPraegu = laud.getBronnitud();
        broneeringudbroneeringudPraegu.add(broneering);
        laud.setBronnitud(broneeringudbroneeringudPraegu);
        laudRepository.save(laud);
    }

    public void lisaBroneeringList(Laud laud, List<Broneering> broneeringud) {
        List<Broneering> broneeringudPraegu = laud.getBronnitud();
        broneeringudPraegu.addAll(broneeringud);
        laud.setBronnitud(broneeringudPraegu);
        laudRepository.save(laud);
    }



    public LaudDTO looLaudDTO(Laud laud) {
        return new LaudDTO (
                laud.getId(),
                broneeringService.looBroneeringDTOdLauaPõhjal(laud),
                laud.getPrivaatne(),
                laud.getAknaKoht(),
                laud.getLigipääsetavus(),
                laud.getInimesteArv(),
                laud.getTsoon()
        );
    }

    public String[] looKõigiLaudadeDTOd(List<Laud> laudadeList) {
        String[] koigiLaudadeDTOd = new String[laudadeList.size()];
        for (int i = 0; i < laudadeList.size(); i++) {
            LaudDTO laudDTO = looLaudDTO(laudadeList.get(i));
            koigiLaudadeDTOd[i] = laudDTO.toString();
        }
        return koigiLaudadeDTOd;
    }

    public String[][] bronnidToStringMaatriks(Laud laud) {
        if (laud.getBronnitud() != null) {
            String[][] bronnidMaatriks = new String[laud.getBronnitud().size()][4];
            for (int i = 0; i < bronnidMaatriks.length; i++) {
                bronnidMaatriks[i][0] = String.valueOf(laud.getBronnitud().get(i).getId());
                bronnidMaatriks[i][1] = String.valueOf(laud.getBronnitud().get(i).getLaud().getId());
                bronnidMaatriks[i][2] = laud.getBronnitud().get(i).getNimi();
                bronnidMaatriks[i][3] = laud.getBronnitud().get(i).getAeg();
            }
            return bronnidMaatriks;
        } else {
            return null;
        }
    }



}
