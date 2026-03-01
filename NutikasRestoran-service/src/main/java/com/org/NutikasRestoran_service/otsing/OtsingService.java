package com.org.NutikasRestoran_service.otsing;

import com.org.NutikasRestoran_service.PaevKalendris.Paev;
import com.org.NutikasRestoran_service.PaevKalendris.PaevFactory;
import com.org.NutikasRestoran_service.PaevKalendris.PaevService;
import com.org.NutikasRestoran_service.broneering.Broneering;
import com.org.NutikasRestoran_service.broneering.BroneeringService;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OtsingService {

    private final PaevService paevService;
    private final ObjectMapper objectMapper;
    private final PaevFactory paevFactory;

    public OtsingService(ObjectMapper objectMapper, BroneeringService broneeringService, PaevService paevService, PaevFactory paevFactory) {
        this.objectMapper = objectMapper;
        this.paevService = paevService;
        this.paevFactory = paevFactory;
    }

    public void looOtsing(String data) {
        System.out.println("Saadud otsing: " + data);
        Otsing otsing = objectMapper.readValue(data, Otsing.class); // Tõlgendame otsingu objektiks

        LocalDateTime kuupäev = otsing.getValikAeg(); // võtame Paeva



        if(paevService.kasPäevEksisteerib(kuupäev.toLocalDate())){ // kui saan, et Paev eksisteerib siis otsin välja selle Päeva broneeringud ja saadan

            List<Broneering> broneeringudResponse = paevService.getPaevBroneeringud(kuupäev.toLocalDate());
            // juhuuuu, töötab, sain need bronnid mis tol päeval on, nüüd vaja kuidagi saata frontendi
            System.out.println("Leitud broneeringud: " + broneeringudResponse.size());
            for (Broneering broneering : broneeringudResponse) {
                System.out.println("Broneering ID: " + broneering.getId() + ", Laud: "  + ", Aeg: " + broneering.getAeg());
            }


        } else { // kui Paeva ei eksisteeri, teen uue Päeva ja saadan tagasi uued, genereeritud broneeringud

            Paev uusPaev = new Paev (0, null, kuupäev.toLocalDate());
            paevService.save(uusPaev);
            List<Broneering> uuePäevaBronnnid = paevFactory.looPaev(uusPaev);
            paevService.lisaBroneeringList(uusPaev, uuePäevaBronnnid); // lisan ka uuele päevale kõik selle päeva broneeringud.

            System.out.println("genetud uued bronnid");
            for (Broneering broneering : uuePäevaBronnnid) {
                System.out.println("Broneering ID: " + broneering.getId() + ", Laud: " + ", Aeg: " + broneering.getAeg());
            }
        }

/*        public List<Broneering> looUusPaev(LocalDate date) { // saime otsingu data ja nüüd loome sellega uue päeva
            Paev uusPaev = new Paev(0, null, date);
            save(uusPaev); // lisasime uue paeva andmebaasi.
            //loadData.looPaev(uusPaev);
            return getPaevBroneeringud(date); // on ikka probleem noh
        }*/


    }

}
