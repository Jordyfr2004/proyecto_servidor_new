import express from 'express';
import cors from 'cors';
import dotenv from 'dotenv';
import donacionRoutes from './routes/donacion.routes';
import donanteRoutes from './routes/donante.routes';
import productoRoutes from './routes/producto.routes';
import tipoProductoRoutes from './routes/tipo_producto.routes';
import estadoDonacionRoutes from './routes/estado_donacion.routes';

dotenv.config();
const app = express();

app.use(cors());
app.use(express.json());

// Rutas
app.use('/api/donaciones', donacionRoutes);
app.use('/api/donantes', donanteRoutes);
app.use('/api/productos', productoRoutes); 
app.use('/api/tipos-producto', tipoProductoRoutes);
app.use('/estado-donacion', estadoDonacionRoutes);

const PORT = process.env.PORT || 3001;
app.listen(PORT, () => {
  console.log(`Servidor corriendo en http://localhost:${PORT}`);
});
