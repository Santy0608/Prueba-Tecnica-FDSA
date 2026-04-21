import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { Disponibilidad } from '../../domain/Disponibilidad';
import { DisponibilidadService } from '../../services/disponibilidad.service';
import { SharingDataServiceDisponibilidad } from '../sharing-data-services/sharing-data-service-disponibilidad';
import { TipoHabitacionService } from '../../services/tipo-habitacion.service';

@Component({
  selector: 'app-disponibilidad',
  imports: [CommonModule, RouterModule],
  templateUrl: './disponibilidad.component.html',
  styleUrl: './disponibilidad.component.css'
})
export class DisponibilidadComponent implements OnInit{

  disponibilidades: Disponibilidad[] = [];
  errors: any;

  constructor(private disponibilidadService: DisponibilidadService,
              private router: Router,
  ){

  }

  ngOnInit(): void {
    this.listadoDisponibilidades();;
  }

  listadoDisponibilidades(): void {
    this.disponibilidadService.listadoDisponibilidades().subscribe({
      next: (data) => {
        this.disponibilidades = data;
        console.log('Disponibilidades cargadas:', data);
      },
      error: (err) => {
          console.error('Error al cargar disponibilidades:', err);
      }
    });
  }
  
  OnSelectedDisponibilidad(disponibilidad: Disponibilidad): void{
    this.router.navigate(['/disponibilidades/editar-disponibilidad', disponibilidad.id]);
  }

}
