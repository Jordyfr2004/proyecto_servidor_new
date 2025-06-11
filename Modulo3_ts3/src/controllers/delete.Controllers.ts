import { Request, Response } from 'express';
import { Donante } from '../entities/donante';
import { EmpresaInfo } from '../entities/empresaInfo';
import { UbicacionDonante } from '../entities/Ubicacion';

export const deleteDonante2 = async (req: Request, res: Response) => {
  try {
    const id = parseInt(req.params.id);

    if (isNaN(id)) {
      res.status(400).json({ mensaje: "ID inválido" });
    } else {
      const donante = await Donante.findOne({
        where: { id },
        relations: ['empresaInfo', 'ubicacion'],
      });

      if (!donante) {
        res.status(404).json({ mensaje: "Donante no encontrado" });
      } else {
        const empresa = donante.empresaInfo;
        const ubicacion = donante.ubicacion;

        await Donante.delete({ id });

        if (empresa) {
          const otrosConEmpresa = await Donante.count({
            where: { empresaInfo: { id: empresa.id } }
          });
          if (otrosConEmpresa === 0) {
            await EmpresaInfo.delete({ id: empresa.id });
          }
        }

        if (ubicacion) {
          const otrosConUbicacion = await Donante.count({
            where: { ubicacion: { id: ubicacion.id } }
          });
          if (otrosConUbicacion === 0) {
            await UbicacionDonante.delete({ id: ubicacion.id });
          }
        }

        res.sendStatus(204); // Eliminado correctamente, sin contenido
      }
    }
  } catch (error) {
    console.error("Error al eliminar el donante:", error);
    res.status(500).json({ mensaje: "Error interno del servidor" });
  }
};


