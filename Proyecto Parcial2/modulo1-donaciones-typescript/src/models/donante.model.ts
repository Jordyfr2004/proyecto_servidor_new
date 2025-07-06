export interface Donante {
  id: number;
  nombre: string;
  tipo: string;
  correo: string;
  telefono: string;
  direccion?: string;
  fecha_registro: string;
}
