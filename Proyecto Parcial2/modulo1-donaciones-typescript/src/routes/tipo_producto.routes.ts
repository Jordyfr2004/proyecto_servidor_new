import { Router } from 'express';
import {
  getTiposProducto,
  createTipoProducto,
  updateTipoProducto,
  deleteTipoProducto
} from '../controllers/tipo_producto.controller';

const router = Router();

router.get('/', getTiposProducto);
router.post('/', createTipoProducto);
router.put('/:id', updateTipoProducto);
router.delete('/:id', deleteTipoProducto);

export default router;
