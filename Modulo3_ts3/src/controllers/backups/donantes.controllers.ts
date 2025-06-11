import { Request,Response } from "express";
import { Donante } from "../../entities/donante";

export const  page2 =  (req: Request, res: Response) => {
    res.send('no hay donantes');
}

export const createdonante =async (req: Request, res: Response) : Promise<void> => {
    try {
        const {nombre,apellido,tipo_donante,telefono,correo ,password } = req.body;

        const donante = new Donante();
        donante.nombre = nombre;
        donante.apellido = apellido;
        donante.tipo_donante = tipo_donante;
        donante.telefono = telefono;
        donante.correo = correo;
        donante.password = password;

        await donante.save();

        res.json(donante);
    } catch (error) {
        console.error(error);
        res.status(500).json({mensaje: "error al crear donante"})
    }
}


export const verDonantes = async (req: Request, res: Response) => {
    try{
        const donante = await Donante.find()
        res.send(donante)
    } catch (error) {
        console.log(error);
        res.status(500).json({mensaje: "Error al obtener donantes" })
    }
}

export const updateDonante = async (req: Request, res: Response) => {
    try {
        const idParam = parseInt(req.params.id);
        if(isNaN(idParam)) {
            res.status(400).json({ mensaje: "ID inválido en los parámetros" });
        }
        const donante = await Donante.findOneBy({ id: idParam });

        if (!donante) {
            res.status(404).json({ mensaje: "Donante no encontrado" });
        }
        await Donante.update({ id: idParam }, req.body);

        res.status(200).json({ mensaje: "Donante actualizado correctamente" });
    } catch (error) {
        console.error("Error al actualizar el Donante:", error);
        res.status(500).json({ mensaje: "Error interno del servidor" });
    }
};


export const deleteDonante = async (req: Request, res: Response) => {
    try {
        const id = parseInt(req.params.id);

        if (isNaN(id)){
            res.status(400).json({ mensaje: "ID inválido" });
        }

        const result = await Donante.delete({id});

        if (result.affected ===0){
            res.status(404).json({ mensaje: "Donante no encontrado" });
        }
         res.sendStatus(204).json({ mensaje: "Donante eliminado" }); // Eliminado correctamente, sin contenido
    } catch (error) {
        console.error("Error al eliminar el administrador:", error);
        res.status(500).json({ mensaje: "Error interno del servidor" });
    }
}