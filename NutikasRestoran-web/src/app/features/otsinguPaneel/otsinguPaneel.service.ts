import { inject, Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Otsing } from "./otsing";
import { BehaviorSubject, Observable } from "rxjs";
import { ErrorHandler } from "@angular/core";
import { SoovitusService } from "../soovitusPaneel/soovitusPaneel.service";
import { Soovitus } from "../soovitusPaneel/soovitus";
import { SoovitusPaneelComponent } from "../soovitusPaneel/list/soovitusPaneel-list.component";

@Injectable({ providedIn: 'root' })
export class OtsingService {

    private soovitusService = inject(SoovitusService);

    http = inject(HttpClient)

    saadaOtsingPaevJaKell(otsing: Otsing){
        console.log(otsing)
        this.http.post<Array<Soovitus>>('api/otsing/paevjakell', otsing).subscribe(response => {
            this.soovitusService.getSoovitus(response); // saaadan vastuse edasi soovituse paneelile
        })
    }

    saadaOtsingPaev(otsing: Otsing): void{
        console.log(otsing)
        this.http.post<any>('api/otsing/paev', otsing).subscribe(response => {
            console.log("tegin post request- OtsingPaev")
        })
    }

}