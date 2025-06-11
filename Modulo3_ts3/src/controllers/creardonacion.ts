import { Request, Response } from 'express';
import { Donacion } from '../entities/donacion';
import { Donante } from '../entities/donante';

export const crearDonacion = async (req: Request, res: Response) => {
  try {
    const { donanteId } = req.params; // lo sacamos de la ruta
    const { estado_donacion, descripcion_donacion } = req.body; // lo demás va en el body

    if (!estado_donacion || !descripcion_donacion) {
      res.status(400).json({ message: 'Faltan datos requeridos' });
      return;
    }

    // Convertir donanteId a número si es necesario
    const id = Number(donanteId);
    if (isNaN(id)) {
      res.status(400).json({ message: 'ID de donante inválido' });
      return;
    }

    const donante = await Donante.findOneBy({ id });
    if (!donante) {
      res.status(404).json({ message: 'Donante no encontrado' });
      return;
    }

    const donacion = new Donacion();
    donacion.estado_donacion = estado_donacion;
    donacion.descripcion_donacion = descripcion_donacion;
    donacion.donante = donante;

    await donacion.save();

    res.status(201).json({ message: 'Donación creada y asociada', donacion });
  } catch (error) {
    console.error(error);
    res.status(500).json({ message: 'Error interno' });
  }
};

