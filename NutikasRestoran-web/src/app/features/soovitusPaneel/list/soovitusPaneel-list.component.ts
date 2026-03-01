import { Component, OnInit } from '@angular/core';
import { SoovitusService } from '../soovitusPaneel.service';
import { inject } from '@angular/core';
import { Soovitus } from '../soovitus';
import { signal } from '@angular/core';
import { toSignal } from '@angular/core/rxjs-interop';
import { AsyncPipe } from '@angular/common';
import { computed } from '@angular/core';
import { OtsingService } from '../../otsinguPaneel/otsinguPaneel.service';


@Component({
  selector: 'soovitusPaneel-list',
  templateUrl: './soovitusPaneel-list.component.html',
  styleUrl: './soovitusPaneel-list.component.scss',
})
export class SoovitusPaneelComponent implements OnInit {



    getSoovitus(response: Soovitus[]){

    }
    

    ngOnInit(): void {
        console.log()
    }

}
