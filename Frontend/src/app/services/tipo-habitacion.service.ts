import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TipoHabitacion } from '../domain/TipoHabitacion';
import { Hotel } from '../domain/Hotel';

@Injectable({
  providedIn: 'root'
})
export class TipoHabitacionService {

   private url: string = '${environment.apiUrl}/tipos-habitaciones';

  constructor(private http: HttpClient) { 

  }

  listadoTiposHabitaciones(): Observable<TipoHabitacion[]>{
    return this.http.get<TipoHabitacion[]>(this.url);
  }
    
        
  buscarTipoHabitacionPorId(id: number): Observable<TipoHabitacion>{
    return this.http.get<TipoHabitacion>(`${this.url}/${id}`);
  }
    
  guardarTipoHabitacion(tipoHabitacion: TipoHabitacion): Observable<TipoHabitacion>{
    return this.http.post<TipoHabitacion>(this.url, tipoHabitacion);
  }

  editarTipoHabitacion(tipoHabitacion: TipoHabitacion): Observable<TipoHabitacion> {
    return this.http.put<TipoHabitacion>(`${this.url}/${tipoHabitacion.idTipoHabitacion}`, tipoHabitacion);
  }

  eliminarTipoHabitacionPorId(idTipoHabitacion: number){
    return this.http.delete(`${this.url}/${idTipoHabitacion}`);
  }

  buscarPorNombre(nombre: string): Observable<TipoHabitacion[]> {
    return this.http.get<TipoHabitacion[]>(`${this.url}/buscar?nombre=${nombre}`);
  }

}
