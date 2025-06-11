import express from 'express';
import morgan from 'morgan';
import cors from 'cors';
import adminRoutes from './routes/backups/admin.routes';
import donanteRoutes from './routes/donante.routes';

const app = express();

app.use(express.json());
app.use(morgan('dev'));
app.use(cors());

app.use(adminRoutes)
app.use(donanteRoutes)

export default app;


