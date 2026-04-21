import { Component, OnInit } from '@angular/core';
import { Hotel } from '../../domain/Hotel';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { HotelService } from '../../services/hotel.service';
import { SharingDataServiceHotel } from '../sharing-data-services/sharing-data-service-hotel';
import { FormsModule, NgForm } from '@angular/forms';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-hotel-form',
  imports: [CommonModule, RouterModule, FormsModule],
  templateUrl: './hotel-form.component.html',
  styleUrl: './hotel-form.component.css'
})
export class HotelFormComponent implements OnInit{

  hotel!: Hotel;
  errors: any;

  constructor(private hotelService: HotelService,
              private sharingDataService: SharingDataServiceHotel,
              private router: Router,
              private route: ActivatedRoute
  ){
    this.hotel = new Hotel();
  }

  ngOnInit(): void {
    this.sharingDataService.erroresHotelFormEventEmitter.subscribe(errors => this.errors = errors);
    this.sharingDataService.seleccionarHotelEventEmitter.subscribe(hotel => this.hotel = hotel);
    this.route.paramMap.subscribe(params => {
      const id:number = +(params.get('idHotel') || '0');
      if (id > 0){
        this.hotelService.buscarHotelPorId(id).subscribe(hotel => this.hotel = hotel);
      }
    })
  }

  onSubmit(hotelForm: NgForm): void {

    if (hotelForm.invalid) return;

    console.log("Datos enviados:", this.hotel);

    if (this.hotel.idHotel > 0) {

      this.hotelService.editarHotel(this.hotel).subscribe({
        next: () => {
          Swal.fire({
            title: "Actualizado",
            text: "Hotel actualizado correctamente",
            icon: "success"
          }).then(() => {
            this.router.navigate(['/hoteles']);
          });
        },
        error: (err) => {
          const mensaje = err.error?.mensaje || 'Error inesperado';
          Swal.fire("Error", mensaje, "error");
        }
      });

    } else {

      this.hotelService.guardarHotel(this.hotel).subscribe({
        next: () => {
          Swal.fire({
            title: "Creado",
            text: "Hotel creado correctamente",
            icon: "success"
          }).then(() => {
            this.router.navigate(['/hoteles']);
          });
        },
        error: (err) => {
          const mensaje = err.error?.mensaje || 'Error inesperado';
          Swal.fire("Error", mensaje, "error");
        }
      });

    }

  }

}
