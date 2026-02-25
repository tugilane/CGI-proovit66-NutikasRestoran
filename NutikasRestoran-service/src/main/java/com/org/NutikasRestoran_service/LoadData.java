package com.org.NutikasRestoran_service;

import com.org.NutikasRestoran_service.broneering.Broneering;
import com.org.NutikasRestoran_service.broneering.BroneeringService;
import com.org.NutikasRestoran_service.laud.Laud;
import com.org.NutikasRestoran_service.laud.LaudService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class LoadData {

        private final LaudService laudService;
        private final BroneeringService broneeringService;

        LoadData(LaudService laudService, BroneeringService broneeringService){
            this.laudService = laudService;
            this.broneeringService = broneeringService;
        }

    public int getRandomNumber(int min, int max) {
            return (int) ((Math.random() * (max - min)) + min);
    }

        public void laeLauad(LaudService laudService) { // genereerin laudade andmed

            int peaSaaliLaudu = 9; // siin vali mitu lauda saalis olla võiks
            int terrassiLaudu = 6; // siin vali mitu lauda terrassil olla võiks
            int privaatseidLaudu = 3; // siin vali mitu lauda privaatsetes ruumides olla võiks

            for (long i = 0; i < peaSaaliLaudu; i++) {
                laudService.save(new Laud(0,null, getRandomNumber(0, 2), getRandomNumber(0, 2), getRandomNumber(0, 2), getRandomNumber(2, 5), "saal"));
            }

            for (int i = 0; i < terrassiLaudu; i++) {
                laudService.save(new Laud(0,null, getRandomNumber(0, 2), getRandomNumber(0, 2), getRandomNumber(0, 2), getRandomNumber(2, 7), "terass"));
            }

            for (int i = 0; i < privaatseidLaudu; i++) {
                laudService.save(new Laud(0,null, 1, getRandomNumber(0, 2), getRandomNumber(0, 2), getRandomNumber(2, 11), "privaatneRuum"));
            }
        }

    public void laeBroneeringud(BroneeringService broneeringService, LocalDate kuupäev) { // genereerin broneeringute andmed

        int broneeringuteArv = 10000; // mitu broneeringut loome esimesel päeval

        LocalDateTime avamisAeg = LocalDateTime.of(kuupäev.getYear(), kuupäev.getMonth(), kuupäev.getDayOfMonth(), 12, 0);
        LocalDateTime enamEiSaaBronnidaAega = LocalDateTime.of(kuupäev.getYear(), kuupäev.getMonth(), kuupäev.getDayOfMonth(), 22, 0);
        LocalDateTime aeg = avamisAeg; // jooksev aeg

        int laudasidKokku = laudService.koikLauad().size(); // mitut lauda täidame
        int lauaLoendur = 0; // millisele lauale broneering luuakse
        int broneeringuLoendur = 0; // mitu broneeringut on juba loodud

        List<List<Broneering>> broneeringuteListid = new ArrayList<>(); // suursuur list mille pärast söödan laudadele
        for (int i = 0; i < laudasidKokku; i++) { //
            broneeringuteListid.add(new ArrayList<Broneering>());
        }

        while(broneeringuLoendur < broneeringuteArv) {

            Optional<Laud> laudKuhuLisanBronni = laudService.valiLaud(lauaLoendur);
            int loos = getRandomNumber(0, 3); // toimub loos, "kas teeme broneeringu sellele kellajale"
            if (laudKuhuLisanBronni.isPresent() && loos > 0) { // kui laud eksisteerib ja loosimine õnnestub siis broneerin laua. loos on hetkel seatud 2/3 tõenäosusega
                Broneering uusBronn = new Broneering(0, laudKuhuLisanBronni.get(), "Kylaline", aeg.toString());
                broneeringService.save(uusBronn);
                broneeringuteListid.get(lauaLoendur-1).add(uusBronn);

            }

            if (lauaLoendur <= laudasidKokku) {
                lauaLoendur++;
            } else {
                lauaLoendur = 0; // käisime kõik lauad läbi, nüüd alustan uuesti
                aeg = aeg.plusHours(2); // iga kahe tunni tagant luuakse uus broneering
                if (aeg.isAfter(enamEiSaaBronnidaAega)){ // kui aeg nii palju et ei saa enam bronnida
                    break;
                }
            }
            for (Broneering broneering : broneeringService.koikBroneeringud()) {
                System.out.println("Broneeringu id: " + broneering.getId() + " Broneeringu aeg: " + broneering.getAeg() + " Broneeringu laud: " + broneering.getLaud().getId());
            }
            broneeringuLoendur++;


        }

        for (int i = 0; i < laudasidKokku; i++) { // nüüd lisame kõik broneeringud laudadele
            laudService.lisaBroneeringList(laudService.valiLaud(i+1).get(),broneeringuteListid.get(i));
        }

        for (Laud laud : laudService.koikLauad()) {
            System.out.print(laud.getId() + " ");
            for (String[] strings : laudService.bronnidToStringMaatriks(laud)) {
                System.out.print("[");
                for (String string : strings) {
                    System.out.print(string + ", ");
                }
                System.out.print("]");
            }
        System.out.println(laud.getPrivaatne() + " " + laud.getAknaKoht() + " " + laud.getLigipääsetavus() + " " + laud.getInimesteArv() + " " + laud.getTsoon());
        }

    }
}
