import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-abonado',
  templateUrl: './abonado.component.html',
  styleUrls: ['./abonado.component.css']
})
export class AbonadoComponent {

  constructor(private router: Router) {}

  cerrarSesion() {
    // Aquí puedes limpiar datos de sesión si es necesario
    this.router.navigate(['/bienvenida']);
  }
}
