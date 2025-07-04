import { Router, Request,Response } from "express"
import { crearDonante } from "../controllers/crear.donantes.controllers";
import { verDonantes1 } from "../controllers/verdonstes.controllers";
import { verDonantePorId } from "../controllers/ver.id.donantes";
import { actualizarDonante } from "../controllers/update.Controllers";
import { deleteDonante2 } from "../controllers/delete.Controllers";
import { crearDonacion } from "../controllers/creardonacion";

const router = Router();

//Ruta post
router.post('/donante/crear', crearDonante)

router.post('/donante/:donanteId/donacion', crearDonacion)


//Ruta get 
router.get('/donante/ver',verDonantes1)
router.get('/donante/ver/:id', verDonantePorId)


//Ruta put (actualizar)
router.put('/donante/cambio/:id',actualizarDonante)


//Ruta delete (eliminar)
router.delete('/donante/eliminar/:id', deleteDonante2)


export default router