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
    { label: 'Hoteles', icon: '🏨', route: '/hoteles' },
    { label: 'Habitaciones', icon: '🛏️', route: '/tipos-habitaciones' },
    { label: 'Disponibilidad', icon: '📅', route: '/disponibilidades' },
    { label: 'Reservas', icon: '📋', route: '/reservas' }
  ];
}
