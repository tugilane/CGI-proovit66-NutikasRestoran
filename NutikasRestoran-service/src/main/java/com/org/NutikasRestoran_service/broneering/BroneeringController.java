package com.org.NutikasRestoran_service.broneering;

import com.org.NutikasRestoran_service.laud.Laud;
import com.org.NutikasRestoran_service.laud.LaudRepository;
import com.org.NutikasRestoran_service.laud.LaudService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BroneeringController {

    private final BroneeringService broneeringService;

    BroneeringController(BroneeringService broneeringService) {
        this.broneeringService = broneeringService;
    }

    @GetMapping("/broneeringud")
    public List<Broneering> getBroneeringudList(){
        return broneeringService.koikBroneeringud();
    }
}
