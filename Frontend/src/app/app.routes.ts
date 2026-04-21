import { Routes } from '@angular/router';
import { MainLayoutComponent } from './components/main-layout/main-layout.component';
import { HotelComponent } from './components/hotel/hotel.component';

export const routes: Routes = [

    {
        path: '',
        component: MainLayoutComponent,
        children: [
            { path: 'hoteles', component: HotelComponent },
            { path: '', redirectTo: 'hoteles', pathMatch: 'full' }
        ]
    }

];
