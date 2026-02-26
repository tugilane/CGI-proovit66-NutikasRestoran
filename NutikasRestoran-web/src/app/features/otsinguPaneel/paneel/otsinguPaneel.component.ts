import { Component, OnInit, signal } from '@angular/core';

@Component({
    selector:'otsinguPaneel',
    imports: [],
    templateUrl: 'otsinguPaneel.component.html',
    styleUrl: 'otsinguPaneel.component.scss'
})

export class OtsinugPaneelComponent{

        listKuud = [
            "jaanuar",
            "veebruar",
            "märts",
            "aprill",
            "mai",
            "juuni",
            "juuli",
            "august",
            "september",
            "oktoober",
            "november",
            "detsember"
        ];

        täna = new Date()

        aasta0 = this.täna.getFullYear();
        aasta1 = this.aasta0 + 1;
        
        kuu = this.täna.getMonth();
        kuu0 = this.listKuud[this.kuu];
        kuu1 = this.listKuud[this.kuu + 1];
        kuu2 = this.listKuud[this.kuu + 2];
        kuu3 = this.listKuud[this.kuu + 3];

        array = Array.from("12345")

}