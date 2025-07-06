import { Router } from 'express';
import {
  getDonaciones,
  createDonacion,
  updateDonacion,
  deleteDonacion
} from '../controllers/donacion.controller';

const router = Router();

router.get('/', getDonaciones);
router.post('/', createDonacion);
router.put('/:id', updateDonacion);
router.delete('/:id', deleteDonacion);

export default router;
