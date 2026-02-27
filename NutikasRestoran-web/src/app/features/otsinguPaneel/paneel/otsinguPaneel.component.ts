import { Component, OnInit, signal } from '@angular/core';
import { FormControl } from '@angular/forms';
import { OtsinguPaneelOtsing } from '../otsinguPaneelOtsing';
import { FormsModule } from '@angular/forms';

@Component({
    selector:'otsinguPaneel',
    imports: [FormsModule],
    templateUrl: 'otsinguPaneel.component.html',
    styleUrl: 'otsinguPaneel.component.scss'
})

export class OtsinguPaneelComponent implements OnInit {

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

        tänaAasta = this.täna.getFullYear();
        aasta0: number = this.tänaAasta;
        aasta1: number = this.tänaAasta + 1;
        
        kuuTana = this.täna.getMonth();
        kuu0 = this.listKuud[this.kuuTana];  
        kuudDisplay: string[] = []; // kuud mille väljastame valikusse

        tänaPäev: number = this.täna.getDate();
        paev0: number = this.tänaPäev;
        paevadDisplay: string[] = []; // päevad mille väljastame valikus
        
        checkBoxes = [false, false, false] // aken ligipääsetavus, privaatne

        aastaLaiv: string = this.aasta0.toString();
        kuuLaiv: string = this.kuu0;
        paevLaiv: string = this.paev0.toString() + ".";
        inimesteArvLaiv: string = "2";
        tsoonLaiv: string = "peasaal";
        kellHHLaiv: string = "12";
        kellMMLaiv: string = "00";
        
        misKuusidNaidata(): string[]{ // kui programm käivitati või kasutaja valib aasta siis selgitame mis kuusid kasutajale näidata.
            this.kuudDisplay = [];
            let aastaAlgus: number = 0; // aitab täita nimekirja olukorras kus on aasta vahetamine
            let kuuAbi: number = this.kuuTana; // hoiab järge käesolevast kuust

            if (parseInt(this.aastaLaiv) == this.aasta0){ // kui on käesolev aasta
                for (let i = 0; i <= 4; i++){
                    if (kuuAbi < 12) {
                        this.kuudDisplay.push(this.listKuud[kuuAbi]); // tõstame listi need kuud mis veel see aasta
                        kuuAbi++;
                    } else {
                        return this.kuudDisplay;
                    }
                }

            } else { // kui kasutaja valib järgmise aasta
                ("valis järgmise")
                for (let i = 0; i <= 4; i++){
                    if (kuuAbi >= 12){
                        this.kuudDisplay.push(this.listKuud[aastaAlgus]) // tõstame listi need mis on järgmisel aastal
                        aastaAlgus++;
                        kuuAbi++;
                    }
                    else {
                        kuuAbi++;
                    }
                }
            }

            return this.kuudDisplay;
        }

        //https://coreui.io/answers/how-to-get-the-number-of-days-in-a-month-in-javascript/
        //abifunktsioon, arvutame palju päevi on valitud kuus
        getDaysInMonth(year: number, month: number): number{
            let abi = new Date(year, month, 0).getDate();
            return abi; 
        }

        misPaeviNaidata(): string[] { // näitame õiget arvu päevi olenevalt sellest mis kasutaja valib

            this.paevadDisplay = [];
            let paeviKuus = this.getDaysInMonth(parseInt(this.aastaLaiv), this.listKuud.indexOf(this.kuuLaiv)+1)

            if (this.kuuLaiv == this.kuu0){ // kui vaatame käesolevat kuud
                for (let i = this.paev0; i <= paeviKuus; i++){
                    this.paevadDisplay.push(i.toString() + ".");
                }
                
            } else { // kui vaatame muud kasutaja valitud kuud
                for (let i = 1; i <= paeviKuus; i++){
                    this.paevadDisplay.push(i.toString() + ".")
                }
            }
            return this.paevadDisplay
        }    


        muudaCheckBox(id: number){
            if (this.checkBoxes[id]) {
                this.checkBoxes[id] = false;
            } else {
                this.checkBoxes[id] = true;
            }
        }



        getValikud(aken: string, ligipääsetav: string, aasta:string, kuu:string, paev: string){
            let otsing = {
                aken: aken,
                ligipääsetav: ligipääsetav,
                inimesteArv: this.inimesteArvLaiv,
                tsoon: this.tsoonLaiv,
                aeg: aasta+"-"+(1+this.listKuud.indexOf(kuu))+"-"+paev.split(".")[0]+"T"+this.kellHHLaiv+":"+this.kellMMLaiv+":00"
            }
            console.log(otsing)
        }

        ngOnInit(): void {

        }

}