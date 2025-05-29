import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router'; // 👈 Importa Router

@Component({
  selector: 'app-abonarse',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './abonarse.component.html',
  styleUrls: ['./abonarse.component.css']
})
export class AbonarseComponent {
  equipoSeleccionado: string | null = null;

  constructor(private router: Router) {} // 👈 Inyecta el Router

  mostrarPanel(equipo: string) {
    this.equipoSeleccionado = equipo;
  }

  volver() {
    this.router.navigate(['/precios']); // 👈 Navega a la vista 'precios'
  }

  irAAbonado() {
    this.router.navigate(['/abonado']);
  }
}










