import { Component } from '@angular/core';

@Component({
  selector: 'app-footer',
  template: `<div id="joon"></div>`,
  styles: `
  #joon{
    background-color: black; 
    padding: 10px; 
    min-width:90%;
    margin-left: auto;
    margin-right: auto;
    
  }
  `,
})
export class FooterComponent {}