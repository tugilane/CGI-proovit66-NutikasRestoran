package com.org.NutikasRestoran_service.otsing;

import com.org.NutikasRestoran_service.PaevKalendris.Paev;
import com.org.NutikasRestoran_service.PaevKalendris.PaevFactory;
import com.org.NutikasRestoran_service.PaevKalendris.PaevService;
import com.org.NutikasRestoran_service.broneering.Broneering;
import com.org.NutikasRestoran_service.broneering.BroneeringService;
import com.org.NutikasRestoran_service.laud.Laud;
import com.org.NutikasRestoran_service.laud.LaudService;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class OtsingService {

    private final BroneeringService broneeringService;
    private final PaevService paevService;
    private final ObjectMapper objectMapper;
    private final PaevFactory paevFactory;
    private final LaudService laudService;


    public OtsingService(ObjectMapper objectMapper, BroneeringService broneeringService, PaevService paevService, PaevFactory paevFactory, LaudService laudService) {
        this.objectMapper = objectMapper;
        this.broneeringService = broneeringService;
        this.paevService = paevService;
        this.paevFactory = paevFactory;
        this.laudService = laudService;
    }

    public OtsingDTO looOtsingKellaga(String data) {
        /*System.out.println("Saadud otsing: " + data);*/
        Otsing otsing = objectMapper.readValue(data, Otsing.class); // Tõlgendame otsingu objektiks

        OtsingDTO otsingResponse = broneeringuteOtsing(otsing, true);
        System.out.println("kellaajaga otsing");

        return otsingResponse;
    }

    public Otsing looOtsingKoguPaev(String data) {
        /*System.out.println("Saadud otsing: " + data);*/
        Otsing otsing = objectMapper.readValue(data, Otsing.class); // Tõlgendame otsingu objektiks

        System.out.println("paeva otsing");
        broneeringuteOtsing(otsing, false);

        return otsing;
    }

    private OtsingDTO broneeringuteOtsing(Otsing otsing, boolean kellaga) {
        LocalDateTime otsinguAeg = otsing.getValikAeg(); // võtame Paeva

        List<Broneering> broneeringudList = kinnitaPaev(otsinguAeg.toLocalDate()); // loome kas uue päeva või võtame juba olemas oleva päeva

        boolean aknaSoov = otsing.isValikAknaKoht();
        boolean ligipääsetavusSoov = otsing.isValikLigipääsetavus();
        long inimesteArvSoov = otsing.getValikInimesteArv();
        String tsoonSoov = otsing.getValikTsoon();
        LocalDateTime aegSoov = otsinguAeg;

        long laudasidKokku = laudService.koikLauad().size();

        HashMap<Laud, Integer> laudSkoorid = new HashMap<>();

        for (int i = 0; i < laudasidKokku; i++) {

            Laud käesolevLaud = laudService.valiLaud(i+1).get();
            int lauaSkoor = 0;
            long lauaInimesteVõimekus = käesolevLaud.getInimesteArv();

            if (inimesteArvSoov != lauaInimesteVõimekus){
                lauaSkoor += (int) (lauaInimesteVõimekus - inimesteArvSoov);
            }

            // täidame kliendi soovi range kontrolliga, näitame kliendile ainult eelistatut.
            if (tsoonSoov.equals(käesolevLaud.getTsoon()) || tsoonSoov.equals("vahetPole")){ // esmalt kontrollime tsooni
                if (lauaSkoor < 4 && lauaSkoor >= 0) {// kui lauas jääb üle rohkem kui 3 kohta siis ei näita kliendile lauda, ei näita ka lauda kui soov on suurem kui laua taha mahub
                    if ((aknaSoov && ligipääsetavusSoov) && (käesolevLaud.getAknaKoht() && käesolevLaud.getLigipääsetavus())) { // kui on soovitud aknaga ja hea ligipääsevusega, kontrollime kas leidub sellist
                    laudSkoorid.put(käesolevLaud, lauaSkoor); // OK, lisame valikusse
                } else if ((aknaSoov && !ligipääsetavusSoov) && käesolevLaud.getAknaKoht()) { // kui oli soov saada aknaga laud
                    laudSkoorid.put(käesolevLaud, lauaSkoor);
                } else if ((!aknaSoov && ligipääsetavusSoov) && käesolevLaud.getLigipääsetavus()) { // kui oli soov saada hea ligipääsetavusega laud
                    laudSkoorid.put(käesolevLaud, lauaSkoor);

                } else if (!aknaSoov && !ligipääsetavusSoov){
                    laudSkoorid.put(käesolevLaud, lauaSkoor);
                }
            }
            }
        }

                List<Map.Entry<Laud, Integer>> sorteeritudLauad = new ArrayList<>(laudSkoorid.entrySet());
                sorteeritudLauad.sort(Map.Entry.comparingByValue());

                List<Laud> sorteeritudLauadList = new ArrayList<>();

                for (Map.Entry<Laud, Integer> entry : sorteeritudLauad) {
                    sorteeritudLauadList.add(entry.getKey());
                }

                // aegSoov
                    List<Broneering> asjaKohasedBroneeringud = new ArrayList<>();
                    for (int i = 0; i < broneeringudList.size(); i++) {
                        LocalDateTime bronnitudAeg = broneeringudList.get(i).getAeg();
                        if (bronnitudAeg == aegSoov || (bronnitudAeg.isAfter(aegSoov) && bronnitudAeg.isBefore(aegSoov.plusHours(2)))){
                            asjaKohasedBroneeringud.add(broneeringudList.get(i));
                        }
                    }

                    List<Laud> võimalikBroneering = new ArrayList<>();
                    boolean laudVaba = true; // eeldame et laud on vaba
                    for (int i = 0; i < sorteeritudLauadList.size(); i++) {

                        for (Broneering broneering : asjaKohasedBroneeringud) {
                            if (broneering.getLaud() == sorteeritudLauadList.get(i)){
                                laudVaba = false; // saime teada et laud on hõivatud
                            }
                        }
                        if (laudVaba){
                            võimalikBroneering.add(sorteeritudLauadList.get(i));
                        }

                        laudVaba = true; // järgmise laua eeldus

                    }



        // nüüd on meil teada millal saab broneeringuid teha jeee
                    OtsingDTO otsingResponse = new OtsingDTO(
                            otsing.isValikAknaKoht(),
                            otsing.isValikLigipääsetavus(),
                            otsing.getValikInimesteArv(),
                            otsing.getValikTsoon(),
                            otsing.getValikAeg(),
                            võimalikBroneering
                    );
        for (Laud laud: otsingResponse.getResponseList()) {
            System.out.println("sobivad lauad on " + laud.getId());
        }

        return otsingResponse;
    }


    public List<Broneering> kinnitaPaev(LocalDate kuupäev) {

        List<Broneering> broneeringudList = new ArrayList<>();

        if(paevService.kasPäevEksisteerib(kuupäev)){ // kui saan, et Paev eksisteerib siis otsin välja selle Päeva broneeringud ja saadan

            broneeringudList = paevService.getPaevBroneeringud(kuupäev); // saime valitud päevaga seotud broneeringute listi

        } else { // kui Paeva ei eksisteeri, teen uue Päeva ja saadan tagasi uued, genereeritud broneeringud

            Paev uusPaev = new Paev (0, null, kuupäev);
            paevService.save(uusPaev);
            broneeringudList = paevFactory.looPaev(uusPaev); // Saime uue päeva ja listi selle broneeringutest
            paevService.lisaBroneeringList(uusPaev, broneeringudList); // lisan ka uuele päevale kõik selle päeva broneeringud.

            System.out.println("genetud uued broneeringud");

        }
        return broneeringudList;

    }

}
