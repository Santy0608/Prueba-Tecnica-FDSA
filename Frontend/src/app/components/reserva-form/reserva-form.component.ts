import { Component, OnInit } from '@angular/core';
import { EstadoReserva, Reserva } from '../../domain/Reserva';
import { TipoHabitacion } from '../../domain/TipoHabitacion';
import { ReservaService } from '../../services/reserva.service';
import { SharingDataServiceReserva } from '../sharing-data-services/sharing-data-service-reserva';
import { ActivatedRoute, Router, RouterLink, RouterModule } from '@angular/router';
import { TipoHabitacionService } from '../../services/tipo-habitacion.service';
import Swal from 'sweetalert2';
import { FormsModule, NgForm } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-reserva-form',
  imports: [FormsModule, RouterModule, RouterLink, CommonModule],
  templateUrl: './reserva-form.component.html',
  styleUrl: './reserva-form.component.css'
})
export class ReservaFormComponent implements OnInit{

  reserva!: Reserva;
  tiposHabitaciones: TipoHabitacion[] = [];
  estados = Object.values(EstadoReserva);
  esEdicion = false;
  errors: any;

  constructor(private reservaService: ReservaService,
              private sharingDataService: SharingDataServiceReserva,
              private router: Router,
              private route: ActivatedRoute,
              private tipoHabitacionService: TipoHabitacionService
  ){
    this.reserva = new Reserva();
  }

  ngOnInit(): void {
    this.sharingDataService.erroresReservaFormEventEmitter.subscribe(errors => this.errors = errors);
    this.sharingDataService.seleccionarReservaEventEmitter.subscribe(reserva => this.reserva = reserva);
    this.route.paramMap.subscribe(params => {
      const id:number = +(params.get('id') || '0');
      if (id > 0){
        this.reservaService.buscarReservaPorId(id).subscribe(reserva => this.reserva = reserva);
      }
    })
    this.cargarTiposHabitacion();
  }

  cargarTiposHabitacion(): void {
    this.tipoHabitacionService.listadoTiposHabitaciones().subscribe({
      next: (data) => this.tiposHabitaciones = data,
      error: (err) => console.error('Error al cargar tipos:', err)
    });
  }

  onSubmit(reservaForm: NgForm): void {
    if (!this.reserva.tipoHabitacionId) {
      Swal.fire('Error', 'Debe seleccionar un tipo de habitación', 'error');
      return;
    }

    if (this.reserva.id > 0) {
      this.reservaService.editarReserva(this.reserva).subscribe({
        next: () => {
          this.router.navigate(['/reservas']);
          Swal.fire('¡Actualizado!', 'La reserva fue actualizada correctamente.', 'success');
        },
        error: (err) => {
          this.sharingDataService.erroresReservaFormEventEmitter.emit(err);
          console.error('Error al actualizar:', err);
        }
      });
    } else {
      this.reservaService.guardarReserva(this.reserva).subscribe({
        next: () => {
          this.router.navigate(['/reservas']);
          Swal.fire('¡Creado!', 'La reserva fue registrada correctamente.', 'success');
        },
        error: (err) => {
          this.sharingDataService.erroresReservaFormEventEmitter.emit(err);
          console.error('Error al crear:', err);
        }
      });
    }
  }

  onCancelar(): void {
    this.router.navigate(['/reservas']);
  }

}
