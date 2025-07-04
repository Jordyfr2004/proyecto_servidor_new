import { Request, Response } from 'express';
import { Donante } from '../entities/donante';
import { UbicacionDonante } from '../entities/Ubicacion';
import { EmpresaInfo } from '../entities/empresaInfo';

export const actualizarDonante = async (req: Request, res: Response) => {
  try {
    const { id } = req.params;
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

    const donante = await Donante.findOne({
      where: { id: Number(id) },
      relations: ['ubicacion', 'empresaInfo'],
    });

    if (!donante) {
      res.status(404).json({ message: 'Donante no encontrado.' });
    } else {
      // No permitir cambiar tipo de donante
      if (tipo_donante && tipo_donante !== donante.tipo_donante) {
        res.status(400).json({ message: 'No está permitido cambiar el tipo de donante.' });
      } else {
        // Actualizar ubicación si se proporciona
        if (ubicacion) {
          if (donante.ubicacion) {
            donante.ubicacion.provincia = ubicacion.provincia ?? donante.ubicacion.provincia;
            donante.ubicacion.ciudad = ubicacion.ciudad ?? donante.ubicacion.ciudad;
            donante.ubicacion.direccion = ubicacion.direccion ?? donante.ubicacion.direccion;
            await donante.ubicacion.save();
          } else {
            const nuevaUbicacion = UbicacionDonante.create({
              provincia: ubicacion.provincia ?? '',
              ciudad: ubicacion.ciudad ?? '',
              direccion: ubicacion.direccion ?? ''
            });
            await nuevaUbicacion.save();
            donante.ubicacion = nuevaUbicacion;
          }
        }

        // Actualizar empresaInfo solo si es donante jurídico
        if (donante.tipo_donante === 'juridico' && empresa) {
          if (donante.empresaInfo) {
            donante.empresaInfo.nombre_empresa = empresa.nombre_empresa ?? donante.empresaInfo.nombre_empresa;
            donante.empresaInfo.ruc = empresa.ruc ?? donante.empresaInfo.ruc;
            donante.empresaInfo.representante_legal = `${nombre ?? donante.nombre} ${apellido ?? donante.apellido}`;
            await donante.empresaInfo.save();
          } else if (empresa.nombre_empresa && empresa.ruc) {
            const nuevaEmpresa = EmpresaInfo.create({
              nombre_empresa: empresa.nombre_empresa,
              ruc: empresa.ruc,
              representante_legal: `${nombre ?? donante.nombre} ${apellido ?? donante.apellido}`
            });
            await nuevaEmpresa.save();
            donante.empresaInfo = nuevaEmpresa;
          }
        }

        // Actualizar datos generales si se proporcionan
        if (nombre !== undefined) donante.nombre = nombre;
        if (apellido !== undefined) donante.apellido = apellido;
        if (telefono !== undefined) donante.telefono = telefono;
        if (correo !== undefined) donante.correo = correo;
        if (password !== undefined) donante.password = password;

        await donante.save();
        res.status(200).json({ message: 'Donante actualizado con éxito.', donante });
      }
    }
  } catch (error) {
    console.error('Error al actualizar donante:', error);
    res.status(500).json({ message: 'Error interno del servidor.' });
  }
};



