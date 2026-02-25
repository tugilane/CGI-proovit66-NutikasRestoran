import { inject, Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Laud } from "./laud";
import { Observable } from "rxjs";

@Injectable({ providedIn: 'root' })
export class LauadService {

    http = inject(HttpClient)

    getLauad(): Observable<Laud[]> {
        return this.http.get<Array<Laud>>('api/lauad')
    }

}