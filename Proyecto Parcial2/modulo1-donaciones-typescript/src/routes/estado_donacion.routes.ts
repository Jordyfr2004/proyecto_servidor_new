import { Router } from 'express';
import {
  getEstados,
  getEstado,
  createEstado,
  updateEstado,
  deleteEstado
} from '../controllers/estado_donacion.controller';

const router = Router();

router.get('/', getEstados);
router.get('/:id', getEstado);
router.post('/', createEstado);
router.put('/:id', updateEstado);
router.delete('/:id', deleteEstado);

export default router;
