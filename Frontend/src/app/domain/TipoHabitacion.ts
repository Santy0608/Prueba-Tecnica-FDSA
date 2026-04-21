export class TipoHabitacion{

    idTipoHabitacion!: number;
    nombre?: string;
    descripcion?: string;
    capacidad?: number;
    precioBase?: number; 
    activo?: boolean;
    createdAt?: Date;

    hotelId?: number;
    nombreHotel?: string;

}