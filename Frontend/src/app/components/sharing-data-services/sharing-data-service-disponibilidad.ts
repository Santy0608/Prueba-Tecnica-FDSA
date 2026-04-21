import { EventEmitter, Injectable } from "@angular/core";
import { Disponibilidad } from "../../domain/Disponibilidad";



@Injectable({
  providedIn: 'root'
})
export class SharingDataServiceDisponibilidad {

  private _nuevaDisponibilidadEventEmitter: EventEmitter<Disponibilidad> = new EventEmitter();
  
  private _idDisponibilidadEventEmitter = new EventEmitter();
  
  private _buscarDisponibilidadEventEmitter = new EventEmitter();

  private _seleccionarDisponibilidadEventEmitter = new EventEmitter();

  private _erroresDisponibilidadFormEventEmitter = new EventEmitter();

  constructor() {

  }

  get erroresDisponibilidadFormEventEmitter(){
    return this._erroresDisponibilidadFormEventEmitter;
  }

  get nuevaDisponibilidadEventEmitter(): EventEmitter<Disponibilidad>{
    return this._nuevaDisponibilidadEventEmitter;
  }
  
  get idDisponibilidadEventEmitter(): EventEmitter<Number>{
    return this._idDisponibilidadEventEmitter;
  }

  get buscarDisponibilidadEventEmitter(){
    return this._buscarDisponibilidadEventEmitter;
  }

  get seleccionarDisponibilidadEventEmitter(){
    return this._seleccionarDisponibilidadEventEmitter;
  }


}