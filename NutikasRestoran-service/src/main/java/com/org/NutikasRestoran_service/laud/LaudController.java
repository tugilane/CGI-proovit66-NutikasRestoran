package com.org.NutikasRestoran_service.laud;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class LaudController {

    private final LaudRepository laudRepository;

    LaudController(LaudRepository laudRepository, LaudService laudService) {
        this.laudRepository = laudRepository;
    }

    @GetMapping("/lauad")
    public List<Laud> getLauadList(){
        return laudRepository.findAll();
    }
}
