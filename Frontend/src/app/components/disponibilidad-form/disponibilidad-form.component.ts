import { Component, OnInit } from '@angular/core';
import { Disponibilidad } from '../../domain/Disponibilidad';
import { TipoHabitacion } from '../../domain/TipoHabitacion';
import { DisponibilidadService } from '../../services/disponibilidad.service';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { TipoHabitacionService } from '../../services/tipo-habitacion.service';
import { SharingDataServiceDisponibilidad } from '../sharing-data-services/sharing-data-service-disponibilidad';
import { FormsModule, NgForm } from '@angular/forms';
import Swal from 'sweetalert2';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-disponibilidad-form',
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './disponibilidad-form.component.html',
  styleUrl: './disponibilidad-form.component.css'
})
export class DisponibilidadFormComponent implements OnInit{

  disponibilidad!: Disponibilidad;
  disponibilidades: Disponibilidad[] = [];
  errors: any;
  tiposHabitaciones: TipoHabitacion[] = [];

  constructor(private disponibilidadService: DisponibilidadService,
              private router: Router,
              private route: ActivatedRoute,
              private tipoHabitacionService: TipoHabitacionService,
              private sharingDataService: SharingDataServiceDisponibilidad
  ){
    this.disponibilidad = new Disponibilidad();
  }

  ngOnInit(): void {
    this.sharingDataService.erroresDisponibilidadFormEventEmitter.subscribe(errors => this.errors = errors);
    this.sharingDataService.seleccionarDisponibilidadEventEmitter.subscribe(disponibilidad => this.disponibilidad = disponibilidad);
    this.route.paramMap.subscribe(params => {
      const id:number = +(params.get('id') || '0');
      if (id > 0){
        this.disponibilidadService.buscarDisponibilidadPorId(id).subscribe(disponibilidad => this.disponibilidad = disponibilidad);
      }
    })

    this.cargarListadoHabitaciones();
  }

  onSubmit(disponibilidadForm: NgForm): void {
    if (!this.disponibilidad.tipoHabitacionId) {
      Swal.fire("Error", "Debe seleccionar un tipo de habitacion", "error");
      return;
    }

    if (this.disponibilidad.id > 0) {
      this.disponibilidadService.editarDisponibilidad(this.disponibilidad).subscribe({
        next: (disponibilidadUpdated: Disponibilidad) => {
          this.disponibilidades = this.disponibilidades.map(d =>
            d.id === disponibilidadUpdated.id
              ? disponibilidadUpdated
              : d
          );
          this.router.navigate(['/disponibilidades']);
          Swal.fire("¡Actualizado!", "La configuración de la disponibilidad se actualizó correctamente.", "success");
        },
        error: (err) => {
          this.sharingDataService.erroresDisponibilidadFormEventEmitter.emit(err);
          console.error('Error al actualizar:', err);
        }
      });
    } else {
      this.disponibilidadService.guardarDisponibilidad(this.disponibilidad).subscribe({
        next: (disponibilidadNew: Disponibilidad) => {
          this.disponibilidades = [...this.disponibilidades, disponibilidadNew];
          this.router.navigate(['/disponibilidades']);
          Swal.fire("¡Creado!", "La nueva disponibilidad ha sido configurada.", "success");
        },
        error: (err) => {
          this.sharingDataService.erroresDisponibilidadFormEventEmitter.emit(err);
          console.error('Error al crear:', err);
        }
      });
    }
  }

   cargarListadoHabitaciones(): void{
    this.tipoHabitacionService.listadoTiposHabitaciones().subscribe(tiposHabitaciones => {
      this.tiposHabitaciones = tiposHabitaciones;
      console.log('Habitaciones cargadas: ', this.tiposHabitaciones);
    }, error => {
      console.error('Error al cargar habitaciones:', error);
    });
  }


}
