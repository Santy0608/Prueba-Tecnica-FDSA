import { Routes } from '@angular/router';
import { MainLayoutComponent } from './components/main-layout/main-layout.component';
import { HotelComponent } from './components/hotel/hotel.component';
import { HotelFormComponent } from './components/hotel-form/hotel-form.component';
import { TipoHabitacionComponent } from './components/tipo-habitacion/tipo-habitacion.component';
import { TipoHabitacionFormComponent } from './components/tipo-habitacion-form/tipo-habitacion-form.component';
import { Disponibilidad } from './domain/Disponibilidad';
import { DisponibilidadComponent } from './components/disponibilidad/disponibilidad.component';
import { DisponibilidadFormComponent } from './components/disponibilidad-form/disponibilidad-form.component';

export const routes: Routes = [

    {
        path: '',
        component: MainLayoutComponent,
        children: [
            //Rutas para hoteles
            { path: 'hoteles', component: HotelComponent },
            { path: 'hoteles/crear', component: HotelFormComponent},
            { path: 'hoteles/editar-hotel/:idHotel', component: HotelFormComponent},
            { path: '', redirectTo: 'hoteles', pathMatch: 'full' },

            //Rutas para tipos de habitaciones
            { path: 'tipos-habitaciones', component: TipoHabitacionComponent},
            { path: 'tipos-habitaciones/crear', component: TipoHabitacionFormComponent},
            { path: 'tipos-habitaciones/editar-tipo-habitacion/:idTipoHabitacion', component: TipoHabitacionFormComponent},

            //Reglas para disponibilidades
            { path: 'disponibilidades', component: DisponibilidadComponent},
            { path: 'disponibilidades/configurar', component: DisponibilidadFormComponent},
            { path: 'disponibilidades/editar-disponibilidad/:id', component: DisponibilidadFormComponent}

        ]
    }

];
