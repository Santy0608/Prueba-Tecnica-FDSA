import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-sidebar',
  imports: [RouterLink, RouterLinkActive, CommonModule],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css'
})
export class SidebarComponent {
  menuItems = [
    { label: 'Hoteles', initials: 'H', route: '/hoteles' },
    { label: 'Tipos de Habitación', initials: 'T', route: '/tipos-habitaciones' },
    { label: 'Disponibilidad', initials: 'D', route: '/disponibilidades' },
    { label: 'Reservas', initials: 'R', route: '/reservas' }
  ];
}
