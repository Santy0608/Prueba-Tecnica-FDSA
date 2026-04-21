import { Component, OnInit } from '@angular/core';
import { EstadoReserva, Reserva } from '../../domain/Reserva';
import { ReservaService } from '../../services/reserva.service';
import { Router } from '@angular/router';
import { SharingDataServiceReserva } from '../sharing-data-services/sharing-data-service-reserva';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-reserva',
  imports: [],
  templateUrl: './reserva.component.html',
  styleUrl: './reserva.component.css'
})
export class ReservaComponent implements OnInit{

  reservas: Reserva[] = [];
  errors: any;
  estadoReserva = EstadoReserva;

  constructor(private reservaService: ReservaService, private router: Router, private sharingDataService: SharingDataServiceReserva){

  }

  ngOnInit(): void {
    this.listadoReservas();
  }

  listadoReservas(): void {
    this.reservaService.listadoReservas().subscribe({
      next: (data) => this.reservas = data,
      error: (err) => console.error('Error al cargar reservas:', err)
    });
  }

  OnSelectedReserva(reserva: Reserva): void {
    this.sharingDataService.editarReservaEventEmitter.emit(reserva);
    this.router.navigate(['/reservas/editar-reserva', reserva.id]);
  }

  onCambiarEstado(reserva: Reserva, estado: EstadoReserva): void {
    Swal.fire({
      title: `¿Cambiar estado a ${estado}?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Sí, cambiar',
      cancelButtonText: 'Cancelar'
    }).then(result => {
      if (result.isConfirmed) {
        this.reservaService.actualizarEstado(reserva.id, estado).subscribe({
          next: () => {
            reserva.estado = estado;
            Swal.fire('¡Actualizado!', 'Estado cambiado correctamente.', 'success');
          },
          error: (err) => console.error('Error al cambiar estado:', err)
        });
      }
    });
  }

  eliminarReserva(id: number): void {
    Swal.fire({
      title: '¿Eliminar reserva?',
      text: 'Esta acción no se puede deshacer.',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Sí, eliminar',
      cancelButtonText: 'Cancelar'
    }).then(result => {
      if (result.isConfirmed) {
        this.reservaService.eliminarReserva(id).subscribe({
          next: () => {
            this.reservas = this.reservas.filter(r => r.id !== id);
            Swal.fire('¡Eliminado!', 'La reserva fue eliminada.', 'success');
          },
          error: (err) => console.error('Error al eliminar:', err)
        });
      }
    });
  }

  getBadgeClass(estado: EstadoReserva | undefined): string {
    switch (estado) {
      case EstadoReserva.CONFIRMADA: return 'badge-confirmada';
      case EstadoReserva.CANCELADA: return 'badge-cancelada';
      default: return 'badge-pendiente';
    }
  }


}
