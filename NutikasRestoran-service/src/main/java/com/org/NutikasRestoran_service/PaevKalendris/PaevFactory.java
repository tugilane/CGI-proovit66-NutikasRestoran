package com.org.NutikasRestoran_service.PaevKalendris;

import com.org.NutikasRestoran_service.broneering.Broneering;
import com.org.NutikasRestoran_service.broneering.BroneeringService;
import com.org.NutikasRestoran_service.laud.Laud;
import com.org.NutikasRestoran_service.laud.LaudService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
public class PaevFactory {

    private final LaudService laudService;
    private final BroneeringService broneeringService;

    public PaevFactory(LaudService laudService, BroneeringService broneeringService) {
        this.laudService = laudService;
        this.broneeringService = broneeringService;
    }

    /**
     * Random number generator
     */
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public List<Broneering> looPaev(Paev paev){
        LocalDateTime kuupäev = paev.getAeg().atStartOfDay();

        LocalDateTime avamisAeg = LocalDateTime.of(kuupäev.getYear(), kuupäev.getMonth(), kuupäev.getDayOfMonth(), 12, 0);
        LocalDateTime jooksevAeg = avamisAeg; // abiks broneeringute genereerimisel

        int laudasidKokku = this.laudService.koikLauad().size(); // mitut lauda täidame
        int lauaLoendur = 0; // millisele lauale broneering luuakse

        List<List<Broneering>> broneeringuteListid = new ArrayList<>(); // list milles on suured listid laudade broneeringutest
        List<Broneering> koguPaevBroneeringud = new ArrayList<>(); // suursuur list milles on kõik selle päeva broneeringud
        for (int i = 0; i < laudasidKokku; i++) { //
            broneeringuteListid.add(new ArrayList<Broneering>());
        }

        int loos;
        for (int j = 0; j < laudasidKokku; j++) {
            Optional<Laud> laudKuhuLisanBronni = laudService.valiLaud(j+1); // laud millele lisan broneeringud

            if (laudKuhuLisanBronni.isPresent()) { // kui laud eksisteerib.

                for (int i = 0; i < 5; i++) {
                    loos = getRandomNumber(0, 3); // toimub loos, "kas teeme broneeringu sellele kellajale".
                    if (loos > 0){ // kui loos õnnestub siis teeme broneeringu. tõenäosus et bronnitakse on 2/3
                        Broneering uusBronn = new Broneering(0, paev, laudKuhuLisanBronni.get(), "Kylaline", jooksevAeg);
                        broneeringService.save(uusBronn);
                        broneeringuteListid.get(j).add(uusBronn);
                        koguPaevBroneeringud.add(uusBronn);
                    }
                    jooksevAeg = jooksevAeg.plusHours(2); // liigume kaks tundi edasi, et luua uus broneering
                }
                jooksevAeg = avamisAeg; // järgmise laua puhul alustame uuesti hommikust pihta.
            }
        }

        for (int i = 0; i < laudasidKokku; i++) { // nüüd lisame kõik broneeringud laudadele
            laudService.lisaBroneeringList(laudService.valiLaud(i + 1).get(), broneeringuteListid.get(i));
        }

        for (Broneering broneering : broneeringService.koikBroneeringud()) {
            System.out.println("Broneeringu id: " + broneering.getId() + " Broneeringu aeg: " + broneering.getAeg() + " Broneeringu laud: " + broneering.getLaud().getId());
        }
        return koguPaevBroneeringud;
    }
}
