import { Request, Response } from 'express';
import {
  registrarDonacion,
  listarDonaciones,
  borrarDonacion
} from '../services/donacion.service';
import supabase from '../config/db';

export const getDonaciones = async (_req: Request, res: Response): Promise<void> => {
  try {
    const donaciones = await listarDonaciones();
    res.json(donaciones);
  } catch (error: any) {
    res.status(500).json({ error: error.message });
  }
};

export const createDonacion = async (req: Request, res: Response): Promise<void> => {
  try {
    const { id_donante, estado, prioridad } = req.body;

    if (!id_donante || !estado || !prioridad) {
      res.status(400).json({ error: 'Faltan campos requeridos' });
      return;
    }

    const nueva = await registrarDonacion({
      id_donante,
      estado,
      prioridad,
      fecha: new Date().toISOString().split('T')[0]
    });

    res.status(201).json(nueva);
  } catch (error: any) {
    res.status(500).json({ error: error.message });
  }
};

export const deleteDonacion = async (req: Request, res: Response): Promise<void> => {
  try {
    const id = Number(req.params.id);

    if (isNaN(id)) {
      res.status(400).json({ error: 'ID inválido' });
      return;
    }

    await borrarDonacion(id);
    res.status(204).send();
  } catch (error: any) {
    res.status(500).json({ error: error.message });
  }
};

export const updateDonacion = async (req: Request, res: Response): Promise<void> => {
  try {
    const id = Number(req.params.id);
    const { estado, prioridad } = req.body;

    if (isNaN(id)) {
      res.status(400).json({ error: 'ID inválido' });
      return;
    }

    if (!estado && !prioridad) {
      res.status(400).json({ error: 'Debe proporcionar al menos un campo para actualizar' });
      return;
    }

    const camposActualizar: any = {};
    if (estado) camposActualizar.estado = estado;
    if (prioridad) camposActualizar.prioridad = prioridad;

    const { data, error } = await supabase
      .from('donaciones')
      .update(camposActualizar)
      .eq('id', id)
      .select();

    if (error) throw error;
    if (!data || data.length === 0) {
      res.status(404).json({ error: 'Donación no encontrada' });
      return;
    }

    res.json(data[0]);
  } catch (error: any) {
    res.status(500).json({ error: error.message });
  }
};
