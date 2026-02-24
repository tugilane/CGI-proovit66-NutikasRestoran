package com.org.NutikasRestoran_service;

import com.org.NutikasRestoran_service.laud.Laud;
import com.org.NutikasRestoran_service.laud.LaudService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class NutikasRestoranServiceApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(NutikasRestoranServiceApplication.class, args);
		LaudService laudService = context.getBean(LaudService.class);

		laudService.save(new Laud(1L, "2024-06-01T19:00:00", 0, 1, 1, 4, "saal"));
		laudService.save(new Laud(2L, "2024-06-01T19:00:00", 0, 1, 1, 4, "saal"));
		laudService.save(new Laud(3L, "2024-06-01T19:00:00", 0, 1, 1, 4, "saal"));
		laudService.save(new Laud(4L, "2024-06-01T19:00:00", 0, 1, 1, 4, "saal"));
		laudService.save(new Laud(5L, "2024-06-01T19:00:00", 0, 1, 1, 4, "saal"));
		laudService.save(new Laud(6L, "2024-06-01T19:00:00", 0, 1, 0, 5, "terass"));
		laudService.save(new Laud(7L, "2024-06-01T19:00:00", 0, 1, 0, 5, "terass"));
		laudService.save(new Laud(8L, "2024-06-01T19:00:00", 0, 1, 0, 5, "terass"));
		laudService.save(new Laud(9L, "2024-06-01T19:00:00", 1, 1, 1, 6, "privaatRuum"));

		List<Laud> lauad = laudService.findAll();

		for (Laud laud : lauad) {
			System.out.println(laud.getId() + " " + laud.getBronnitud() + " " + laud.getPrivaatne() + " " + laud.getAknaKoht() + " " + laud.getLigipääsetavus() + " " + laud.getInimesteArv() + " " + laud.getTsoon());
		}
		Optional<Laud> uuendatav = laudService.findById(2);

		if (uuendatav.isPresent()) {
			Laud laud = uuendatav.get();
			laud.setBronnitud("2024-06-01T20:00:00");
			laudService.save(laud);
		}
	}

}
