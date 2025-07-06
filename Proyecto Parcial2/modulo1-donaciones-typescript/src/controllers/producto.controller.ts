import { Request, Response } from 'express';
import {
  registrarProducto,
  listarProductos,
  borrarProducto,
  actualizarProducto
} from '../services/producto.service';

export const getProductos = async (_req: Request, res: Response) => {
  try {
    const productos = await listarProductos();
    res.json(productos);
  } catch (error: any) {
    res.status(500).json({ error: error.message });
  }
};

export const createProducto = async (req: Request, res: Response) => {
  try {
    const { id_donacion, nombre, cantidad, unidad, tipo } = req.body;

    if (!id_donacion || !nombre || !cantidad || !unidad || !tipo) {
      res.status(400).json({ error: 'Faltan campos requeridos' });
      return;
    }

    const nuevo = await registrarProducto({ id_donacion, nombre, cantidad, unidad, tipo });
    res.status(201).json(nuevo);
  } catch (error: any) {
    res.status(500).json({ error: error.message });
  }
};

export const updateProducto = async (req: Request, res: Response) => {
  try {
    const id = Number(req.params.id);
    const { id_donacion, nombre, cantidad, unidad, tipo } = req.body;

    if (isNaN(id)) {
      res.status(400).json({ error: 'ID inválido' });
      return;
    }

    const actualizado = await actualizarProducto(id, { id_donacion, nombre, cantidad, unidad, tipo });

    if (!actualizado) {
      res.status(404).json({ error: 'Producto no encontrado' });
    } else {
      res.status(200).json(actualizado);
    }
  } catch (error: any) {
    res.status(500).json({ error: error.message });
  }
};

export const deleteProducto = async (req: Request, res: Response) => {
  try {
    const id = Number(req.params.id);
    if (isNaN(id)) {
      res.status(400).json({ error: 'ID inválido' });
      return;
    }

    await borrarProducto(id);
    res.status(204).send();
  } catch (error: any) {
    res.status(500).json({ error: error.message });
  }
};
