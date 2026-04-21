import { EventEmitter, Injectable } from "@angular/core";
import { Reserva } from "../../domain/Reserva";


@Injectable({
  providedIn: 'root'
})
export class SharingDataServiceReserva {

  private _nuevaReservaEventEmitter: EventEmitter<Reserva> = new EventEmitter();
  
  private _idReservaEventEmitter = new EventEmitter();
  
  private _buscarReservaEventEmitter = new EventEmitter();

  private _seleccionarReservaEventEmitter = new EventEmitter();

  private _erroresReservaFormEventEmitter = new EventEmitter();

  private _listarTodasLasReservasEventEmitter = new EventEmitter<Reserva[]>();

  private _editarReservaEventEmitter = new EventEmitter<Reserva>();

  constructor() {

  }

  get listarTodasLasReservasEventEmitter(): EventEmitter<Reserva[]>{
    return this._listarTodasLasReservasEventEmitter;
  }

  get erroresReservaFormEventEmitter(){
    return this._erroresReservaFormEventEmitter;
  }

  get nuevaReservaEventEmitter(): EventEmitter<Reserva>{
    return this._nuevaReservaEventEmitter;
  }

  get editarReservaEventEmitter(): EventEmitter<Reserva>{
    return this._editarReservaEventEmitter;
  }
  
  get idReservaEventEmitter(): EventEmitter<Number>{
    return this._idReservaEventEmitter;
  }

  get buscarReservaEventEmitter(){
    return this._buscarReservaEventEmitter;
  }

  get seleccionarReservaEventEmitter(){
    return this._seleccionarReservaEventEmitter;
  }


}