import { Request, Response } from 'express';
import { estadoDonacionService } from '../services/estado_donacion.service';

export const getEstados = (_req: Request, res: Response) => {
  res.json(estadoDonacionService.findAll());
};

export const getEstado = (req: Request, res: Response) => {
  const id = parseInt(req.params.id);
  const estado = estadoDonacionService.findOne(id);
  if (estado) res.json(estado);
  else res.status(404).json({ message: 'Estado no encontrado' });
};

export const createEstado = (req: Request, res: Response) => {
  const nuevo = estadoDonacionService.create(req.body);
  res.status(201).json(nuevo);
};

export const updateEstado = (req: Request, res: Response) => {
  const id = parseInt(req.params.id);
  const actualizado = estadoDonacionService.update(id, req.body);
  if (actualizado) res.json(actualizado);
  else res.status(404).json({ message: 'Estado no encontrado' });
};

export const deleteEstado = (req: Request, res: Response) => {
  const id = parseInt(req.params.id);
  const eliminado = estadoDonacionService.delete(id);
  if (eliminado) res.json({ message: 'Eliminado correctamente' });
  else res.status(404).json({ message: 'Estado no encontrado' });
};
