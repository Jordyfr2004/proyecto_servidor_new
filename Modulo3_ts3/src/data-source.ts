import { DataSource } from "typeorm";
import * as dotenv from 'dotenv';
import { Donante } from "./entities/donante";
import { EmpresaInfo} from "./entities/empresaInfo";
import { UbicacionDonante } from "./entities/Ubicacion";
import { Producto } from "./entities/producto";
import { Donacion } from "./entities/donacion";

dotenv.config();

export const AppDataSource = new DataSource({
    type: 'postgres',
    host: process.env.DB_HOST,
    port: Number(process.env.DB_PORT),
    username: process.env.DB_USERNAME,
    password: process.env.DB_PASSWORD,
    database: process.env.DB_NAME,
    synchronize: false, // No modificar estructura
    logging: false,
    entities: [Donante,EmpresaInfo,UbicacionDonante,Producto,Donacion],
    migrations:['src/Migrations/*.ts'],
})