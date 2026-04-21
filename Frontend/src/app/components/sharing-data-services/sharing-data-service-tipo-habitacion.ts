import { EventEmitter, Injectable } from "@angular/core";
import { TipoHabitacion } from "../../domain/TipoHabitacion";


@Injectable({
  providedIn: 'root'
})
export class SharingDataServiceTipoHabitacion {

  private _nuevoTipoHabitacionEventEmitter: EventEmitter<TipoHabitacion> = new EventEmitter();
  
  private _idTipoHabitacionEventEmitter = new EventEmitter();
  
  private _buscarTipoHabitacionEventEmitter = new EventEmitter();

  private _seleccionarTipoHabitacionEventEmitter = new EventEmitter();

  private _erroresHabitacionFormEventEmitter = new EventEmitter();

  constructor() {

  }

  get erroresTipoHabitacionFormEventEmitter(){
    return this._erroresHabitacionFormEventEmitter;
  }

  get nuevoTipoHabitacionEventEmitter(): EventEmitter<TipoHabitacion>{
    return this._nuevoTipoHabitacionEventEmitter;
  }
  
  get idTipoHabitacionEventEmitter(): EventEmitter<Number>{
    return this._idTipoHabitacionEventEmitter;
  }

  get buscarTipoHabitacionEventEmitter(){
    return this._buscarTipoHabitacionEventEmitter;
  }

  get seleccionarTipoHabitacionEventEmitter(){
    return this._seleccionarTipoHabitacionEventEmitter;
  }


}