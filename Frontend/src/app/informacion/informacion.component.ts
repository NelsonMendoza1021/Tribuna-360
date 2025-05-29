import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-informacion',
  imports: [],
  templateUrl: './informacion.component.html',
  styleUrl: './informacion.component.css'
})
export class InformacionComponent {
  imagenes: string[] = [
    'assets/equipos.jpg',
    'assets/millos.jpg',
    'assets/nacional.jpg',
    'assets/santafe.jpeg',
    'assets/junior.jpg',
    'assets/bucara.jpg',
    'assets/america.jpg'
  ];
  indiceActual: number = 0;

  constructor(private router: Router) {}

  cerrarSesion() {
    this.router.navigate(['/bienvenida']);
  }

  irAPrecios() {
    this.router.navigate(['/precios']);
  }

  anteriorImagen() {
    this.indiceActual = (this.indiceActual === 0) ? this.imagenes.length - 1 : this.indiceActual - 1;
  }

  siguienteImagen() {
    this.indiceActual = (this.indiceActual === this.imagenes.length - 1) ? 0 : this.indiceActual + 1;
  }
}
