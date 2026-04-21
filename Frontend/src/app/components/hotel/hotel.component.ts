import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { Hotel } from '../../domain/Hotel';
import { HotelService } from '../../services/hotel.service';
import Swal from 'sweetalert2';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-hotel',
  imports: [CommonModule, RouterModule, FormsModule],
  templateUrl: './hotel.component.html',
  styleUrl: './hotel.component.css'
})
export class HotelComponent implements OnInit{

  hoteles: Hotel[] = [];
  errors: any;  
  terminoBusqueda: string = '';
  hotelesFiltrados: Hotel[] = [];

  constructor(private hotelService: HotelService, private router: Router){

  }

  ngOnInit(): void {
    this.listadoHoteles();
  }

  listadoHoteles(): void {
    this.hotelService.listadoHoteles().subscribe({
      next: (data) => {
        this.hoteles = data;
        console.log('Hoteles cargados:', data);
      },
      error: (err) => {
        console.error('Error al cargar hoteles:', err);
      }
    });
  }

  onBuscar(): void {
    if (this.terminoBusqueda.trim() === '') {
      this.listadoHoteles(); 
      return;
    }
    this.hotelService.buscarPorNombre(this.terminoBusqueda).subscribe({
      next: (data) => this.hoteles = data,
      error: (err) => console.error('Error al buscar:', err)
    });
  }

  OnSelectedHotel(hotel: Hotel): void{
    this.router.navigate(['/hoteles/editar-hotel', hotel.idHotel]);
  }

  eliminarHotel(idHotel: number): void {

    Swal.fire({
      title: "¿Eliminar Hotel?",
      text: "El hotel se eliminará del sistema.",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#d33",
      cancelButtonColor: "#3085d6",
      confirmButtonText: "Sí, eliminar",
      cancelButtonText: "Cancelar"
    }).then((result) => {

      if (result.isConfirmed) {

        this.hotelService.eliminarHotelPorId(idHotel).subscribe({

          next: () => {

            Swal.fire(
              "Eliminado",
              "El hotel fue eliminada correctamente",
              "success"
            );

           this.listadoHoteles();

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
