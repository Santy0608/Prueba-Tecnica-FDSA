import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Disponibilidad } from '../domain/Disponibilidad';
import { environment } from '../../environments/environment'; 


@Injectable({
  providedIn: 'root'
})
export class DisponibilidadService {

  
  private url: string = `${environment.apiUrl}/disponibilidades`;

  constructor(private http: HttpClient) { 

  }

  listadoDisponibilidades(): Observable<Disponibilidad[]>{
    return this.http.get<Disponibilidad[]>(this.url);
  }
        
  buscarDisponibilidadPorId(id: number): Observable<Disponibilidad>{
    return this.http.get<Disponibilidad>(`${this.url}/${id}`);
  }

  filtrar(tipoHabitacionId?: number, fechaInicio?: string, fechaFin?: string): Observable<Disponibilidad[]> {
    let params = new HttpParams();
    if (tipoHabitacionId) params = params.set('tipoHabitacionId', tipoHabitacionId);
    if (fechaInicio) params = params.set('fechaInicio', fechaInicio);
    if (fechaFin) params = params.set('fechaFin', fechaFin);

    return this.http.get<Disponibilidad[]>(`${this.url}/filtrar`, { params });
  }
    
  guardarDisponibilidad(disponibilidad: Disponibilidad): Observable<Disponibilidad>{
    return this.http.post<Disponibilidad>(this.url, disponibilidad);
  }

  editarDisponibilidad(disponibilidad: Disponibilidad): Observable<Disponibilidad> {
    return this.http.put<Disponibilidad>(`${this.url}/${disponibilidad.id}`, disponibilidad);
  }



}
