import { Routes } from '@angular/router';
import { MainLayoutComponent } from './components/main-layout/main-layout.component';
import { HotelComponent } from './components/hotel/hotel.component';
import { HotelFormComponent } from './components/hotel-form/hotel-form.component';

export const routes: Routes = [

    {
        path: '',
        component: MainLayoutComponent,
        children: [
            { path: 'hoteles', component: HotelComponent },
            { path: 'hoteles/crear', component: HotelFormComponent},
            { path: 'hoteles/editar-hotel/:idHotel', component: HotelFormComponent},
            { path: '', redirectTo: 'hoteles', pathMatch: 'full' }
        ]
    }

];
