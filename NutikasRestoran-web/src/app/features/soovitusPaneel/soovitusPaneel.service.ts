import { Injectable, Signal, inject } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
//import { OtsingService } from "../otsinguPaneel/otsinguPaneel.service";
import { Soovitus } from "./soovitus";
import { SoovitusPaneelComponent } from "./list/soovitusPaneel-list.component";
import { tap } from "rxjs";
import { of } from "rxjs";
import { map } from "rxjs";
import { AsyncPipe } from '@angular/common';
import { signal } from "@angular/core";
import { BehaviorSubject } from "rxjs";

@Injectable({
    providedIn: 'root',
})

export class SoovitusService {

    //soovitusedResponse: Soovitus[] = [];

    //soovitusedDisplay = signal<Array<Soovitus>>([]);

    public dataStream = new BehaviorSubject(<Array<Soovitus>>([]))
    
    getDataStream() {
            return this.dataStream.asObservable();
        }

    //soovitusedObs$: Observable<Soovitus[]> = of(this.soovitusedResponse)

    getSoovitus(response: Soovitus[]) {
        this.dataStream.next(response)
    }

/*     subiSoovitusele() {
        return this.soovitusedDisplay
    } */
}