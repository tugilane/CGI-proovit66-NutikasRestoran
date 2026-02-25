import { Component, OnInit, signal } from '@angular/core';
import { Laud } from '../laud';
import { LauadService } from '../lauad.service';
import { inject, Injectable } from "@angular/core";

@Component({
    selector:'laud-list',
    imports: [],
    templateUrl: 'laud-list.component.html',
    styleUrl: 'laud-list.component.scss'
})
export class laudListComponent implements OnInit{

    lauadService = inject(LauadService)
    lauadSaal = signal<Array<Laud>>([]);
    lauadTerass = signal<Array<Laud>>([]); 
    lauadPrivaatne = signal<Array<Laud>>([]);

    ngOnInit(): void { // kui komponent käivitub siis jagame lauad tsoonide alusel grupidesse
        this.lauadService.getLauad().subscribe((lauadData) => {

                let saal = <Array<Laud>>([]);
                let terass = <Array<Laud>>([]);
                let privaatneRuum = <Array<Laud>>([]);
            
            lauadData.forEach(laudElement => { // algul paneme arrayde sisse 
                switch(laudElement.tsoon){
                    case 'saal':
                        saal.push(laudElement)
                    break;
                    case 'terass':
                        terass.push(laudElement)
                    break;
                    case 'privaatneRuum':
                        privaatneRuum.push(laudElement)
                    break;
                }
                this.lauadSaal.set(saal) // ja siis teeme signalid
                this.lauadTerass.set(terass)
                this.lauadPrivaatne.set(privaatneRuum)

                console.log(lauadData)
            });
        })
    }

}
// tükk aega jamasin, et data HTMLis korralikult väljastada, leidsin lõpuks mingi India kuti youtubest, kasutab häid lahendusi
// https://www.youtube.com/watch?v=oUmVFHlwZsI