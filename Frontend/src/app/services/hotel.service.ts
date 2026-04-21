import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Hotel } from '../domain/Hotel';

@Injectable({
  providedIn: 'root'
})
export class HotelService {

  private url: string = 'http://localhost:8080/api/hoteles';

  constructor(private http: HttpClient) { 

  }

  listadoHoteles(): Observable<Hotel[]>{
    return this.http.get<Hotel[]>(this.url);
  }
        
  buscarHotelPorId(id: number): Observable<Hotel>{
    return this.http.get<Hotel>(`${this.url}/${id}`);
  }

  buscarPorNombre(nombre: string): Observable<Hotel[]> {
    return this.http.get<Hotel[]>(`${this.url}/buscar?nombre=${nombre}`);
  }
    
  guardarHotel(hotel: Hotel){
    return this.http.post<Hotel>(this.url, hotel);
  }

  editarHotel(hotel: Hotel) {
    return this.http.put<Hotel>(`${this.url}/${hotel.idHotel}`, hotel);
  }

  eliminarHotelPorId(idHotel: number){
    return this.http.delete(`${this.url}/${idHotel}`);
  }

}
