import { Routes } from '@angular/router';

import { BienvenidaComponent } from './bienvenida/bienvenida.component';
import { LoginComponent } from './login/login.component';
import { InformacionComponent } from './informacion/informacion.component';
import { PreciosComponent } from './precios/precios.component';
import { RegistroComponent } from './registro/registro.component'; // <-- Agrega esta línea
import { AbonarseComponent } from './abonarse/abonarse.component'; // <-- Asegúrate de importar el componente Abonarse
import { AbonadoComponent } from './abonado/abonado.component';





export const routes: Routes = [
    { path: '', component: BienvenidaComponent },
    { path: 'bienvenida', component: BienvenidaComponent },
    { path: 'login', component: LoginComponent },
    { path: 'informacion', component: InformacionComponent },
    { path: 'precios', component: PreciosComponent },
    { path: 'registro', component: RegistroComponent }, // <-- Agrega esta línea
    { path: 'abonarse', component: AbonarseComponent }, // <-- Agrega esta línea
    { path: 'abonado', component: AbonadoComponent } // <-- Agrega esta línea}
    
];





