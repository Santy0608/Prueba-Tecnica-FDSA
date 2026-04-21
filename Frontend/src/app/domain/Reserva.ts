export class Reserva{

    id!: number;
    nombreCliente?: string;
    emailCliente?: string;
    telefonoCliente?: string;
    fechaCheckin?: string;
    fechaCheckout?: string;
    cantidadHabitaciones?: number;
    precioTotal?: number;
    estado?: EstadoReserva;
    notas?: string;
    createdAt?: Date;

    tipoHabitacionId?: number;
    nombreTipoHabitacion?: string;

}


export enum EstadoReserva {
    PENDIENTE = 'PENDIENTE',
    CONFIRMADA = 'CONFIRMADA',
    CANCELADA = 'CANCELADA'
}

