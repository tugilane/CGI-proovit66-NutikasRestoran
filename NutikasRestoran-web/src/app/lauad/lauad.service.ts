import { inject, Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Laud } from "./laud";
import { Observable } from "rxjs";
import { iterator } from "rxjs/internal/symbol/iterator";

@Injectable({ providedIn: 'root' })
export class LauadService {

    http = inject(HttpClient)

    getLauad(): Observable<Laud[]> {
        return this.http.get<Array<Laud>>('https://corsproxy.io/?https://api.npoint.io/fde3b285189590158f84')
    }

}