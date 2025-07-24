import axios from 'axios';
import 'dotenv/config';

const API_PYTHON_URL = process.env.API_PYTHON_URL as string;

export const resolvers = {
    Query: {
        Administradores: async () => {
            try {
                const response = await axios.get(`${API_PYTHON_URL}/all`);
                return response.data.map((admin: any) => ({
                    id: admin.id,
                    nombre: admin.nombre,
                    usuario: admin.usuario,
                    correo: admin.correo,
                    agendas: admin.agendas?.map((a: any) => ({
                        id: a.id,
                        titulo: a.titulo,
                        descripcion: a.descripcion,
                        fecha: a.fecha,
                    })),
                }));

            } catch (error) {
                console.error('Error al obtener administradores:', error);
                return [];
            }
        },

        getAdministrador: async (_: any, { id }: any) => {
            try{
                const response = await axios.get(`${API_PYTHON_URL}/ver/${id}`);
                const admin = response.data;
                return {
                    id: admin.id,
                    nombre: admin.nombre,
                    usuario: admin.usuario,
                    correo: admin.correo,
                    agendas: admin.agendas?.map((a: any) => ({
                        id: a.id,
                        titulo: a.titulo,
                        descripcion: a.descripcion,
                        fecha: a.fecha,
                    })),
                }
            }catch(error){
                console.error('Error al obtener administrador:', error);
                return null;
            }

        }
    },

    Mutation: {
        createAdministrador: async (_: any, { input }: any) => {
            try {
                const response = await axios.post(`${API_PYTHON_URL}/Crear`, input);
                const admin = response.data;
                return {
                    id: admin.id,
                    nombre: admin.nombre,
                    usuario: admin.usuario,
                    correo: admin.correo,
                    agendas:[],
                    revisiones: [],
                    tipos_eventos: []
                };
            } catch (error) {
                console.error('Error al crear administrador:', error);
                return null;
            }
        },
        updateAdministrador: async (_: any, { id, input }: any) => {
            try {
                const response = await axios.put(`${API_PYTHON_URL}/${id}`, input);
                const admin = response.data;
                return {
                    id: admin.id,
                    nombre: admin.nombre,
                    usuario: admin.usuario,
                    correo: admin.correo,
                    agendas: admin.agendas?.map((a: any) => ({
                        id: a.id,
                        titulo: a.titulo,
                        descripcion: a.descripcion,
                        fecha: a.fecha,
                    }))
                };
            } catch (error) {
                console.error('Error al actualizar administrador:', error);
                return null;
            }
        },

        deleteAdministrador: async (_: any, { id }: any) => {
            try {
                await axios.delete(`${API_PYTHON_URL}/${id}`);
                return `Administrador con ID ${id} eliminado correctamente.`;
            } catch (error) {
                console.error('Error al eliminar administrador:', error);
                return `Error al eliminar Administrador con ID ${id}`;
            }
        }
    },
}