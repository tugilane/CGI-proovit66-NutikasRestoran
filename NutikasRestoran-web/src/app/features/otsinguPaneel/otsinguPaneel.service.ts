import { inject, Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Otsing } from "./otsing";
import { Observable } from "rxjs";
import { ErrorHandler } from "@angular/core";

@Injectable({ providedIn: 'root' })
export class OtsingService {

    http = inject(HttpClient)

    saadaOtsing(otsing: Otsing): void{
        console.log(otsing)
        this.http.post<any>('api/otsing', otsing).subscribe(response => {
            console.log("tegin post request- Otsing")
        })
    }

}