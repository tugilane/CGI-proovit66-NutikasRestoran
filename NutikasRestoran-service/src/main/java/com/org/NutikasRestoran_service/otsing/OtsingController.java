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

        @PostMapping("/otsing/paevjakell") // response
        public OtsingDTO otsingReqPaevJaKell(@RequestBody String body) {
            return otsingService.looOtsingKellaga(body);
        }

        @PostMapping("/otsing/paev") // response
        public ResponseEntity<String> otsingReqPaev(@RequestBody String body) {
        otsingService.looOtsingKoguPaev(body);
        return ResponseEntity.ok("Sain kätte päeva otsingu päringu");
        }


}
