import { Request, Response } from 'express';
import { registrarDonante, listarDonantes, borrarDonante } from '../services/donante.service';
import supabase from '../config/db';

export const getDonantes = async (_req: Request, res: Response): Promise<void> => {
  try {
    const donantes = await listarDonantes();
    res.json(donantes);
  } catch (error: any) {
    res.status(500).json({ error: error.message });
  }
};

export const createDonante = async (req: Request, res: Response): Promise<void> => {
  try {
    const { nombre, tipo, correo, telefono, direccion } = req.body;

    if (!nombre || !tipo || !correo || !telefono) {
      res.status(400).json({ error: 'Faltan campos requeridos' });
      return;
    }

    const nuevo = await registrarDonante({
      nombre,
      tipo,
      correo,
      telefono,
      direccion,
      fecha_registro: new Date().toISOString().split('T')[0]
    });

    res.status(201).json(nuevo);
  } catch (error: any) {
    res.status(500).json({ error: error.message });
  }
};

export const deleteDonante = async (req: Request, res: Response): Promise<void> => {
  try {
    const id = Number(req.params.id);
    if (isNaN(id)) {
      res.status(400).json({ error: 'ID inválido' });
      return;
    }

    await borrarDonante(id);
    res.status(204).send();
  } catch (error: any) {
    res.status(500).json({ error: error.message });
  }
};

export const updateDonante = async (req: Request, res: Response): Promise<void> => {
  try {
    const id = Number(req.params.id);
    const { nombre, tipo, correo, telefono, direccion } = req.body;

    if (isNaN(id)) {
      res.status(400).json({ error: 'ID inválido' });
      return;
    }

    if (!nombre && !tipo && !correo && !telefono && !direccion) {
      res.status(400).json({ error: 'Debe enviar al menos un campo para actualizar' });
      return;
    }

    const camposActualizar: any = {};
    if (nombre) camposActualizar.nombre = nombre;
    if (tipo) camposActualizar.tipo = tipo;
    if (correo) camposActualizar.correo = correo;
    if (telefono) camposActualizar.telefono = telefono;
    if (direccion) camposActualizar.direccion = direccion;

    const { data, error } = await supabase
      .from('donantes')
      .update(camposActualizar)
      .eq('id', id)
      .select();

    if (error) throw error;

    if (!data || data.length === 0) {
      res.status(404).json({ error: 'Donante no encontrado' });
      return;
    }

    res.json(data[0]);
  } catch (error: any) {
    res.status(500).json({ error: error.message });
  }
};
