import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Disponibilidad } from '../domain/Disponibilidad';

@Injectable({
  providedIn: 'root'
})
export class DisponibilidadService {

  
  private url: string = 'http://localhost:8080/api/disponibilidades';

  constructor(private http: HttpClient) { 

  }

  listadoDisponibilidades(): Observable<Disponibilidad[]>{
    return this.http.get<Disponibilidad[]>(this.url);
  }
    
        
  buscarDisponibilidadPorId(id: number): Observable<Disponibilidad>{
    return this.http.get<Disponibilidad>(`${this.url}/${id}`);
  }
    
  guardarDisponibilidad(disponibilidad: Disponibilidad): Observable<Disponibilidad>{
    return this.http.post<Disponibilidad>(this.url, disponibilidad);
  }

  editarDisponibilidad(disponibilidad: Disponibilidad): Observable<Disponibilidad> {
    return this.http.put<Disponibilidad>(`${this.url}/${disponibilidad.id}`, disponibilidad);
  }



}
