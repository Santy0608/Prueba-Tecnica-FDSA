import { Component, OnInit } from '@angular/core';
import { TipoHabitacion } from '../../domain/TipoHabitacion';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { TipoHabitacionService } from '../../services/tipo-habitacion.service';
import Swal from 'sweetalert2';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-tipo-habitacion',
  imports: [CommonModule, RouterModule, FormsModule],
  templateUrl: './tipo-habitacion.component.html',
  styleUrl: './tipo-habitacion.component.css'
})
export class TipoHabitacionComponent implements OnInit{

  tiposHabitaciones: TipoHabitacion[] = [];
  errors: any;
  terminoBusqueda: string = ';'
  tiposHabitacionesFiltrados: TipoHabitacion[] = [];

  constructor(private tipoHabitacionService: TipoHabitacionService, private router: Router){

  }

  ngOnInit(): void {
    this.listadoTiposHabitaciones();
  }

  listadoTiposHabitaciones(): void {
    this.tipoHabitacionService.listadoTiposHabitaciones().subscribe({
      next: (data) => {
        this.tiposHabitaciones = data;
        console.log('Tipos de habitaciones cargados:', data);
      },
      error: (err) => {
        console.error('Error al cargar tipos de habitaciones:', err);
      }
    });
  }

  onBuscar(): void {
    if (this.terminoBusqueda.trim() === '') {
      this.listadoTiposHabitaciones(); 
      return;
    }
    this.tipoHabitacionService.buscarPorNombre(this.terminoBusqueda).subscribe({
      next: (data) => this.tiposHabitaciones = data,
      error: (err) => console.error('Error al buscar:', err)
    });
  }

   OnSelectedTipoHabitacion(tipoHabitacion: TipoHabitacion): void{
      this.router.navigate(['/tipos-habitaciones/editar-tipo-habitacion', tipoHabitacion.idTipoHabitacion]);
    }
  
    eliminarTipoHabitacion(idTipoHabitacion: number): void {
  
      Swal.fire({
        title: "¿Eliminar Tipo Habitación?",
        text: "La habitación se eliminará del sistema.",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#d33",
        cancelButtonColor: "#3085d6",
        confirmButtonText: "Sí, eliminar",
        cancelButtonText: "Cancelar"
      }).then((result) => {
  
        if (result.isConfirmed) {
  
          this.tipoHabitacionService.eliminarTipoHabitacionPorId(idTipoHabitacion).subscribe({
  
            next: () => {
  
              Swal.fire(
                "Eliminado",
                "El tipo de habitación fue eliminada correctamente",
                "success"
              );
  
             this.listadoTiposHabitaciones();
  
            },
  
            error: (err) => {
              const mensaje = err.error?.mensaje || 'Error inesperado';
              Swal.fire("Error", mensaje, "error");
            }
  
          });
  
        }
  
      });
  
    }

}
