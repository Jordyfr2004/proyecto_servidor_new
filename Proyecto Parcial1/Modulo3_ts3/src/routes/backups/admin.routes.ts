import { Router, Request, Response } from "express";
import {createadmin, deleteadmin, page1, updateAdmin, verAdmin , getAdminById} from "../../controllers/backups/admin.Controllers";
const router = Router();

router.get('/',page1);

//Rutas post
router.post('/admin/crear',createadmin)

//Rutas get 

router.get('/admin/ver',verAdmin)

router.get("/admin/:id", getAdminById);

//Rutas put (actualizar)

router.put('/admin/cambio/:id',updateAdmin)

//Rutas delete (eliminar)

router.delete('/admin/eliminar/:id',deleteadmin)

export default router;
