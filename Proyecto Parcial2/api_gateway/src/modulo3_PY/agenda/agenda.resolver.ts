import axios from 'axios';
import 'dotenv/config';

const API_PYTHON_URL = process.env.APi_PYTHON_URL as string;

export const resolvers = {
    Query: {
        Agendas: async () => {
            try {
                const response = await axios.get(`${API_PYTHON_URL}/agenda`);
                return response.data.map((agenda: any) => ({
                    id: agenda.id,
                    titulo: agenda.titulo,
                    descripcion: agenda.descripcion,
                    fecha: agenda.fecha,
                    admin_id: agenda.admin_id,
                }));

            } catch (error) {
                console.error('Error al obtener agendas:', error);
                return [];
            } 
        },
    
        getAgendas: async (_: any, { id }: any) => {
            try {
                const response = await axios.get(`${API_PYTHON_URL}/agenda/${id}`);
                const agenda = response.data;
                return {
                    id: agenda.id,
                    titulo: agenda.titulo,
                    descripcion: agenda.descripcion,
                    fecha: agenda.fecha,
                    admin_id: agenda.admin_id,
                }
            } catch(error) {
                console.error('Error al obtener agenda:', error);
                return null;
            }
        },
    },

    Mutation: {
        createAgendas: async (_: any, { input }: any) => {
            try {
                const response = await axios.post(`${API_PYTHON_URL}/agenda/Crear`, input);
                const agenda = response.data;
                return {
                    id: agenda.id,
                    titulo: agenda.titulo,
                    descripcion: agenda.descripcion,
                    fecha: agenda.fecha,
                    admin_id: agenda.admin_id,
                };
            } catch (error) {
                console.error('Error al crear agenda:', error);
                return null;
            }
        },
    
        updateAgendas: async (_: any, { id, input }: any) => {
            try {
                const response = await axios.put(`${API_PYTHON_URL}/${id}`, input);
                const agenda = response.data;
                return {
                    id: agenda.id,
                    titulo: agenda.titulo,
                    descripcion: agenda.descripcion,
                    fecha: agenda.fecha,
                    admin_id: agenda.admin_id,
                };
            } catch (error) {
                console.error('Error al actualizar agenda:', error);
                return null;
            }
        },

        deleteAgendas: async (_: any, { id }: any) => {
            try {
                await axios.delete(`${API_PYTHON_URL}/agenda/delete/${id}`);
                return "Agenda eliminada correctamente";
            } catch (error) {
                console.error('Error al eliminar agenda:', error);
                return "Error al eliminar agenda";
            }
        }
    },
}