import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Reserva } from '../domain/Reserva';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment'; 


@Injectable({
  providedIn: 'root'
})
export class ReservaService {

  private url = `${environment.apiUrl}/reservas`;

  constructor(private http: HttpClient) {}

  listadoReservas(): Observable<Reserva[]> {
    return this.http.get<Reserva[]>(this.url);
  }

  buscarReservaPorId(id: number): Observable<Reserva> {
    return this.http.get<Reserva>(`${this.url}/${id}`);
  }

  guardarReserva(reserva: Reserva): Observable<Reserva> {
    return this.http.post<Reserva>(this.url, reserva);
  }

  editarReserva(reserva: Reserva): Observable<Reserva> {
    return this.http.put<Reserva>(`${this.url}/${reserva.id}`, reserva);
  }

  actualizarEstado(id: number, estado: string): Observable<void> {
    return this.http.patch<void>(`${this.url}/${id}/estado?estado=${estado}`, {});
  }

  eliminarReserva(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`);
  }

  filtrar(estado?: string, nombre?: string, fechaInicio?: string, fechaFin?: string): Observable<Reserva[]> {
    let params = new HttpParams();
    if (estado) params = params.set('estado', estado);
    if (nombre) params = params.set('nombre', nombre);
    if (fechaInicio) params = params.set('fechaInicio', fechaInicio);
    if (fechaFin) params = params.set('fechaFin', fechaFin);

    return this.http.get<Reserva[]>(`${this.url}/filtrar`, { params });
  }
}