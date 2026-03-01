package com.org.NutikasRestoran_service.otsing;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OtsingController {


        private final OtsingService otsingService;
        public OtsingController(OtsingService otsingService) {
            this.otsingService = otsingService;
        }

        @PostMapping("/otsing") // response
        public ResponseEntity<String> otsingReq(@RequestBody String body) {
            otsingService.looOtsing(body);
            return ResponseEntity.ok("Sain kätte otsingu päringu");
        }


}
