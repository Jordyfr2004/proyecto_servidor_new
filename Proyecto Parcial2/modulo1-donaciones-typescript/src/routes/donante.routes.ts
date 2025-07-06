import { Router } from 'express';
import {
  getDonantes,
  createDonante,
  updateDonante,
  deleteDonante
} from '../controllers/donante.controller';

const router = Router();

router.get('/', getDonantes);
router.post('/', createDonante);
router.put('/:id', updateDonante);
router.delete('/:id', deleteDonante);

export default router;
