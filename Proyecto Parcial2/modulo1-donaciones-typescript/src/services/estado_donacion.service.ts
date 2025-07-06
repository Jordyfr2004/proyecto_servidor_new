import { EstadoDonacion } from '../models/estado_donacion.model';

let estados: EstadoDonacion[] = [];
let nextId = 1;

export class EstadoDonacionService {
  findAll(): EstadoDonacion[] {
    return estados;
  }

  findOne(id: number): EstadoDonacion | undefined {
    return estados.find(e => e.id === id);
  }

  create(data: Omit<EstadoDonacion, 'id'>): EstadoDonacion {
    const nuevo: EstadoDonacion = { id: nextId++, ...data };
    estados.push(nuevo);
    return nuevo;
  }

  update(id: number, data: Partial<Omit<EstadoDonacion, 'id'>>): EstadoDonacion | undefined {
    const estado = estados.find(e => e.id === id);
    if (estado) {
      Object.assign(estado, data);
    }
    return estado;
  }

  delete(id: number): boolean {
    const index = estados.findIndex(e => e.id === id);
    if (index !== -1) {
      estados.splice(index, 1);
      return true;
    }
    return false;
  }
}

export const estadoDonacionService = new EstadoDonacionService();
