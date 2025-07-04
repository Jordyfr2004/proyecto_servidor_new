import { Request, Response } from 'express';
import { Donante } from '../entities/donante';

export const verDonantes1 = async (req: Request, res: Response) => {
  try {
    const donantes = await Donante.find({
      relations: ['ubicacion', 'empresaInfo'],
    });

    res.status(200).json(donantes);
  } catch (error) {
    console.error('Error al obtener donantes:', error);
    res.status(500).json({ message: 'Error al obtener los donantes.' });
  }
};
