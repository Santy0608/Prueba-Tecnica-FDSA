import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { Disponibilidad } from '../../domain/Disponibilidad';
import { DisponibilidadService } from '../../services/disponibilidad.service';
import { SharingDataServiceDisponibilidad } from '../sharing-data-services/sharing-data-service-disponibilidad';
import { TipoHabitacionService } from '../../services/tipo-habitacion.service';
import { TipoHabitacion } from '../../domain/TipoHabitacion';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-disponibilidad',
  imports: [CommonModule, RouterModule, FormsModule],
  templateUrl: './disponibilidad.component.html',
  styleUrl: './disponibilidad.component.css'
})
export class DisponibilidadComponent implements OnInit{

  disponibilidades: Disponibilidad[] = [];
  errors: any;
  tiposHabitaciones: TipoHabitacion[] = [];
  tipoHabitacionIdFiltro?: number;
  fechaInicio: string = '';
  fechaFin: string = '';

  constructor(private disponibilidadService: DisponibilidadService,
              private router: Router,
              private tipoHabitacionService: TipoHabitacionService
  ){

  }

  ngOnInit(): void {
    this.listadoDisponibilidades();
    this.cargarTiposHabitacion();
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

  cargarTiposHabitacion(): void {
    this.tipoHabitacionService.listadoTiposHabitaciones().subscribe({
      next: (data) => this.tiposHabitaciones = data,
      error: (err) => console.error(err)
    });
  }

  onFiltrar(): void {
    this.disponibilidadService.filtrar(this.tipoHabitacionIdFiltro, this.fechaInicio, this.fechaFin).subscribe({
      next: (data) => this.disponibilidades = data,
      error: (err) => console.error(err)
    });
  }

  onLimpiarFiltros(): void {
    this.tipoHabitacionIdFiltro = undefined;
    this.fechaInicio = '';
    this.fechaFin = '';
    this.listadoDisponibilidades();
  }
  
  OnSelectedDisponibilidad(disponibilidad: Disponibilidad): void{
    this.router.navigate(['/disponibilidades/editar-disponibilidad', disponibilidad.id]);
  }

}
