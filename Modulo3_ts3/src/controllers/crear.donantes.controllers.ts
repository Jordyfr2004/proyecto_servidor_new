import { Request, Response } from 'express';
import { Donante } from '../entities/donante';
import { UbicacionDonante } from '../entities/Ubicacion';
import { EmpresaInfo } from '../entities/empresaInfo';

export const crearDonante = async (req: Request, res: Response) => {
  try {
    const {
      nombre,
      apellido,
      tipo_donante,
      telefono,
      correo,
      password,
      ubicacion,
      empresa
    } = req.body;

    if (!nombre || !apellido || !tipo_donante || !telefono || !correo || !password || !ubicacion) {
        res.status(400).json({ message: 'Faltan datos requeridos.' });
    }

    const nuevaUbicacion = UbicacionDonante.create({
      provincia: ubicacion.provincia,
      ciudad: ubicacion.ciudad,
      direccion: ubicacion.direccion
    });

    await nuevaUbicacion.save();

    let nuevaEmpresa: EmpresaInfo | undefined;

    if (tipo_donante === 'juridico') {
      if (!empresa?.nombre_empresa || !empresa?.ruc) {
        res.status(400).json({ message: 'Faltan datos de empresa para donante jurídico.' });
      }

      nuevaEmpresa = EmpresaInfo.create({
        nombre_empresa: empresa.nombre_empresa,
        ruc: empresa.ruc,
        representante_legal: `${nombre} ${apellido}`
      });

      await nuevaEmpresa.save();
    }

    const nuevoDonante = Donante.create({
      nombre,
      apellido,
      tipo_donante,
      telefono,
      correo,
      password,
      ubicacion: nuevaUbicacion,
      empresaInfo: nuevaEmpresa
    });

    await nuevoDonante.save();

    res.status(201).json({ message: 'Donante registrado con éxito.', donante: nuevoDonante });

  } catch (error) {
    console.error('Error al crear donante:', error);
    res.status(500).json({ message: 'Error interno del servidor.' });
  }
};
