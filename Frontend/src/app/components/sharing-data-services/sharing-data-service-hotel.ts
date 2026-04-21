import { EventEmitter, Injectable } from "@angular/core";
import { Hotel } from "../../domain/Hotel";



@Injectable({
  providedIn: 'root'
})
export class SharingDataServiceHotel {

  private _nuevoHotelEventEmitter: EventEmitter<Hotel> = new EventEmitter();
  
  private _idHotelEventEmitter = new EventEmitter();
  
  private _buscarHotelEventEmitter = new EventEmitter();

  private _seleccionarHotelEventEmitter = new EventEmitter();

  private _erroresHotelFormEventEmitter = new EventEmitter();

  constructor() {

  }

  get erroresHotelFormEventEmitter(){
    return this._erroresHotelFormEventEmitter;
  }

  get nuevoHotelEventEmitter(): EventEmitter<Hotel>{
    return this._nuevoHotelEventEmitter;
  }
  
  get idHotelEventEmitter(): EventEmitter<Number>{
    return this._idHotelEventEmitter;
  }

  get buscarHotelEventEmitter(){
    return this._buscarHotelEventEmitter;
  }

  get seleccionarHotelEventEmitter(){
    return this._seleccionarHotelEventEmitter;
  }


}
