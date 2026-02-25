package com.org.NutikasRestoran_service;

import com.org.NutikasRestoran_service.broneering.BroneeringService;
import com.org.NutikasRestoran_service.laud.Laud;
import com.org.NutikasRestoran_service.laud.LaudService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class NutikasRestoranServiceApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(NutikasRestoranServiceApplication.class, args);
		LaudService laudService = context.getBean(LaudService.class);
		BroneeringService broneeringService = context.getBean(BroneeringService.class);
		LoadData loadData = context.getBean(LoadData.class);

		LocalDate kuupäev = LocalDate.now();

		loadData.laeLauad(laudService);
		loadData.laeBroneeringud(broneeringService, kuupäev);

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
