import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-precios',
  templateUrl: './precios.component.html',
  styleUrls: ['./precios.component.css']
})
export class PreciosComponent {
  imagenes: string[] = [
    'assets/abonomillos.png',
    'assets/abonomillosdos.png'
  ];
  indiceActual: number = 0;

  constructor(private router: Router) {}

  volverAInformacion() {
    this.router.navigate(['/informacion']);
  }

  irAAbonarse() {
    this.router.navigate(['/abonarse']);
  }
}
