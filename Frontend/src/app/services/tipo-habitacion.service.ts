import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TipoHabitacion } from '../domain/TipoHabitacion';

@Injectable({
  providedIn: 'root'
})
export class TipoHabitacionService {

   private url: string = 'http://localhost:8080/api/tipos-habitaciones';

  constructor(private http: HttpClient) { 

  }

  listadoTiposHabitaciones(): Observable<TipoHabitacion[]>{
    return this.http.get<TipoHabitacion[]>(this.url);
  }
    
        
  buscarTipoHabitacionPorId(id: number): Observable<TipoHabitacion>{
    return this.http.get<TipoHabitacion>(`${this.url}/${id}`);
  }
    
  guardarTipoHabitacion(tipoHabitacion: TipoHabitacion){
    return this.http.post(this.url, tipoHabitacion);
  }

  editarTipoHabitacion(tipoHabitacion: TipoHabitacion) {
    return this.http.put(`${this.url}/${tipoHabitacion.idTipoHabitacion}`, tipoHabitacion);
  }

  eliminarTipoHabitacionPorId(idTipoHabitacion: number){
    return this.http.delete(`${this.url}/${idTipoHabitacion}`);
  }

}
