import { Request, Response } from 'express';
import {
  registrarTipoProducto,
  listarTiposProducto,
  actualizarTipoProducto,
  borrarTipoProducto
} from '../services/tipo_producto.service';

export const getTiposProducto = async (_req: Request, res: Response) => {
  try {
    const tipos = await listarTiposProducto();
    res.json(tipos);
  } catch (error: any) {
    res.status(500).json({ error: error.message });
  }
};

export const createTipoProducto = async (req: Request, res: Response) => {
  try {
    const { nombre } = req.body;

    if (!nombre) {
      res.status(400).json({ error: 'Falta el campo nombre' });
      return;
    }

    const nuevo = await registrarTipoProducto({ nombre });
    res.status(201).json(nuevo);
  } catch (error: any) {
    res.status(500).json({ error: error.message });
  }
};

export const updateTipoProducto = async (req: Request, res: Response) => {
  try {
    const id = Number(req.params.id);
    const { nombre } = req.body;

    if (isNaN(id) || !nombre) {
      res.status(400).json({ error: 'ID inválido o falta el campo nombre' });
      return;
    }

    const actualizado = await actualizarTipoProducto(id, { nombre });
    res.json(actualizado);
  } catch (error: any) {
    res.status(500).json({ error: error.message });
  }
};

export const deleteTipoProducto = async (req: Request, res: Response) => {
  try {
    const id = Number(req.params.id);

    if (isNaN(id)) {
      res.status(400).json({ error: 'ID inválido' });
      return;
    }

    await borrarTipoProducto(id);
    res.status(204).send();
  } catch (error: any) {
    res.status(500).json({ error: error.message });
  }
};
