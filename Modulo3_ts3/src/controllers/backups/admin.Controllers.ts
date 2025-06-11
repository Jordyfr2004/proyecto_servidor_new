import { Request, Response } from "express";
import { Admin } from "../../entities/backups/admin";


export const page1  = (req: Request, res: Response) => {
    res.send('hola mundo');
}

export const createadmin = async (req: Request, res: Response): Promise<void> => {
    try {
        const { nombre, apellido, telefono, correo, password } = req.body;

        const admin = new Admin();
        admin.nombre = nombre;
        admin.apellido = apellido;
        admin.telefono = telefono;
        admin.correo = correo;
        admin.password = password;

        await admin.save();

        res.json(admin);
    } catch (error) {
        console.error(error);
        res.status(500).json({ mensaje: "Error al crear admin" });
    }
};


export  const verAdmin  =  async (req: Request, res: Response) => {

    try{
        const admin = await Admin.find()
        res.send(admin)
    } catch (error) {
        console.log(error);
        res.status(500).json({ mensaje: "Error al obtener los administradores" });
    }
}


export const updateAdmin = async (req: Request, res: Response) => {
  try {
    const idParam = parseInt(req.params.id);
    if (isNaN(idParam)) {
        res.status(400).json({ mensaje: "ID inválido en los parámetros" });
    }

    const admin = await Admin.findOneBy({ id: idParam });

    if (!admin) {
        res.status(404).json({ mensaje: "Administrador no encontrado" });
    }

    await Admin.update({ id: idParam }, req.body);

    res.status(200).json({ mensaje: "Administrador actualizado correctamente" });
  } catch (error) {
    console.error("Error al actualizar el administrador:", error);
    res.status(500).json({ mensaje: "Error interno del servidor" });
  }
};




export const deleteadmin = async (req: Request, res: Response) => {
  try {
    const id = parseInt(req.params.id);

    if (isNaN(id)) {
        res.status(400).json({ mensaje: "ID inválido" });
    }

    const result = await Admin.delete({ id });

    if (result.affected === 0) {
        res.status(404).json({ mensaje: "Administrador no encontrado" });
    }

    res.sendStatus(204).json({ mensaje: "adminsitrador eliminado" }); // Eliminado correctamente, sin contenido
  } catch (error) {
    console.error("Error al eliminar el administrador:", error);
    res.status(500).json({ mensaje: "Error interno del servidor" });
  }
};

export const getAdminById = async (req: Request, res: Response) => {
    try {
        const id = parseInt(req.params.id);
        if (isNaN(id)) {
            res.status(400).json({ mensaje: "ID inválido" });
        } else {
            const admin = await Admin.findOneBy({ id });

            if (!admin) {
                res.status(404).json({ mensaje: "Admin no encontrado" });
            } else {
                res.json({
                    id: admin.id,
                    nombre: admin.nombre,
                    apellido: admin.apellido,
                    correo: admin.correo
                });
            }
        }
    } catch (error) {
        console.error(error);
        res.status(500).json({ mensaje: "Error al obtener admin" });
    }
};
