package com.org.NutikasRestoran_service.laud;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class LaudController {

    private final LaudService laudService;

    LaudController(LaudService laudService) {
        this.laudService = laudService;
    }

    @GetMapping("/lauad")
    public String getLauadList(){
        String vastus = laudService.looJson(laudService.looKõikLaudDTOd(laudService.koikLauad()));
        System.out.println(vastus);
        return vastus;
    }
}
