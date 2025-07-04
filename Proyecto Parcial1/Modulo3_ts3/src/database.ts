import { AppDataSource } from "./data-source";
import 'reflect-metadata';



export const initDatabase = async() =>{
    try {
        await AppDataSource.initialize();
        console.log("Database initialized successfully")
        return AppDataSource;
    } catch(er) {
        console.error("error initializing database:", er);
        throw er;
    }
};