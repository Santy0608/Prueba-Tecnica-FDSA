import { HttpErrorResponse, HttpInterceptorFn } from "@angular/common/http";
import { catchError, throwError } from "rxjs";
import Swal from "sweetalert2";


export const httpErrorInterceptor: HttpInterceptorFn = (req, next) => {
  return next(req).pipe(
    catchError((error: HttpErrorResponse) => {
      let mensaje = 'Ocurrió un error inesperado';

      switch (error.status) {
        case 0:
          mensaje = 'No se puede conectar con el servidor';
          break;
        case 400:
          mensaje = error.error?.message || 'Datos inválidos';
          break;
        case 404:
          mensaje = 'Recurso no encontrado';
          break;
        case 500:
          mensaje = error.error?.message || 'Error interno del servidor';
          break;
      }

      Swal.fire('Error', mensaje, 'error');
      return throwError(() => error);
    })
  );
};