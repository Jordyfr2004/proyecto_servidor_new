import { Request, Response } from 'express';
import { Donante } from '../entities/donante';

export const verDonantePorId = async (req: Request, res: Response) => {
  try {
    const { id } = req.params;

    const donante = await Donante.findOne({
      where: { id: Number(id) },
      relations: ['ubicacion', 'empresaInfo'],
    });

    if (!donante) {
      res.status(404).json({ message: 'Donante no encontrado.' });
    } else {
      res.status(200).json(donante);
    }
  } catch (error) {
    console.error('Error al obtener el donante:', error);
    res.status(500).json({ message: 'Error al obtener el donante.' });
  }
};
