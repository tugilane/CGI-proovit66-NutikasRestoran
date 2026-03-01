package com.org.NutikasRestoran_service;

import com.org.NutikasRestoran_service.PaevKalendris.Paev;
import com.org.NutikasRestoran_service.PaevKalendris.PaevFactory;
import com.org.NutikasRestoran_service.PaevKalendris.PaevService;
import com.org.NutikasRestoran_service.broneering.Broneering;
import com.org.NutikasRestoran_service.broneering.BroneeringService;
import com.org.NutikasRestoran_service.laud.Laud;
import com.org.NutikasRestoran_service.laud.LaudFactory;
import com.org.NutikasRestoran_service.laud.LaudService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class NutikasRestoranServiceApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(NutikasRestoranServiceApplication.class, args);
		LaudService laudService = context.getBean(LaudService.class);
		BroneeringService broneeringService = context.getBean(BroneeringService.class);
		PaevService paevService = context.getBean(PaevService.class);
		PaevFactory paevFactory = context.getBean(PaevFactory.class);
		LaudFactory laudFactory = context.getBean(LaudFactory.class);

		long start = System.currentTimeMillis(); // paneme ka taimeri

		int peaSaaliLaudu = 25; // siin vali mitu lauda saalis olla võiks
		int terrassiLaudu = 9; // siin vali mitu lauda terrassil olla võiks
		int privaatseidLaudu = 4; // siin vali mitu lauda privaatsetes ruumides olla võiks

		laudFactory.looLauad(peaSaaliLaudu, terrassiLaudu, privaatseidLaudu);

		LocalDateTime kuupäevTäna = LocalDateTime.now();
		Paev paevTäna = new Paev(0L, null, kuupäevTäna.toLocalDate());
		paevService.save(paevTäna);
		List<Broneering> tänaseBronnid = paevFactory.looPaev(paevTäna);
		paevService.lisaBroneeringList(paevTäna, tänaseBronnid);

		long stop = System.currentTimeMillis(); // vaatame kaua aega läks genemisel
		System.out.println("Andmete loomine võttis aega: " + (stop - start) + " ms");

/*
		loadData.laeBroneeringud(broneeringService, kuupäev)*/;

		/*Optional<Laud> uuendatav = laudService.valiLaud(2);

		if (uuendatav.isPresent()) {

			Broneering uusBronn = new Broneering(1L, null, "Jaanus", "18:00");

			Laud laud = uuendatav.get();
			laud.setBronnitud(List.of(uusBronn));
			laudService.save(laud);
			System.out.println(laud.getBronnitud());
		}*/
	}



}
