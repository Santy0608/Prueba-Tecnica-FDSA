import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule, NgForm, NgModel } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { TipoHabitacion } from '../../domain/TipoHabitacion';
import { SharingDataServiceTipoHabitacion } from '../sharing-data-services/sharing-data-service-tipo-habitacion';
import { TipoHabitacionService } from '../../services/tipo-habitacion.service';
import Swal from 'sweetalert2';
import { HotelService } from '../../services/hotel.service';
import { Hotel } from '../../domain/Hotel';

@Component({
  selector: 'app-tipo-habitacion-form',
  imports: [NgModel, CommonModule, RouterModule, FormsModule],
  templateUrl: './tipo-habitacion-form.component.html',
  styleUrl: './tipo-habitacion-form.component.css'
})
export class TipoHabitacionFormComponent implements OnInit{

  tipoHabitacion!: TipoHabitacion;
  tiposHabitaciones: TipoHabitacion[] = [];
  hoteles: Hotel[] = [];
  errors: any;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private sharingDataService: SharingDataServiceTipoHabitacion,
              private tipoHabitacionService: TipoHabitacionService,
              private hotelService: HotelService
  ){
    this.tipoHabitacion = new TipoHabitacion();
  }

  ngOnInit(): void {
    this.sharingDataService.erroresTipoHabitacionFormEventEmitter.subscribe(errors => this.errors = errors);
    this.sharingDataService.seleccionarTipoHabitacionEventEmitter.subscribe(tipoHabitacion => this.tipoHabitacion = tipoHabitacion);
    this.route.paramMap.subscribe(params => {
      const id:number = +(params.get('idTipoHabitacion') || '0');
      if (id > 0){
        this.tipoHabitacionService.buscarTipoHabitacionPorId(id).subscribe(hotel => this.tipoHabitacion = hotel);
      }
    })

    this.cargarListadoHoteles();
  }

   onSubmit(tipoHabitacionForm: NgForm): void {
    if (!this.tipoHabitacion.hotelId) {
      Swal.fire("Error", "Debe seleccionar un hotel", "error");
      return;
    }

    if (this.tipoHabitacion.idTipoHabitacion > 0) {
      this.tipoHabitacionService.editarTipoHabitacion(this.tipoHabitacion).subscribe({
        next: (tipoUpdated: TipoHabitacion) => {
          this.tiposHabitaciones = this.tiposHabitaciones.map(t =>
            t.idTipoHabitacion === tipoUpdated.idTipoHabitacion
              ? tipoUpdated
              : t
          );
          this.router.navigate(['/habitaciones']);
          Swal.fire("¡Actualizado!", "La configuración de la habitación se actualizó correctamente.", "success");
        },
        error: (err) => {
          this.sharingDataService.erroresTipoHabitacionFormEventEmitter.emit(err);
          console.error('Error al actualizar:', err);
        }
      });
    } else {
      this.tipoHabitacionService.guardarTipoHabitacion(this.tipoHabitacion).subscribe({
        next: (tipoNew: TipoHabitacion) => {
          this.tiposHabitaciones = [...this.tiposHabitaciones, tipoNew];
          this.router.navigate(['/habitaciones']);
          Swal.fire("¡Creado!", "El nuevo tipo de habitación ha sido registrado.", "success");
        },
        error: (err) => {
          this.sharingDataService.erroresTipoHabitacionFormEventEmitter.emit(err);
          console.error('Error al crear:', err);
        }
      });
    }
  }

  cargarListadoHoteles(): void{
    this.hotelService.listadoHoteles().subscribe(models => {
      this.hoteles = models;
      console.log('Hoteles cargados: ', this.hoteles);
    }, error => {
      console.error('Error al cargar hoteles:', error);
    });
  }

}
