import axios from 'axios';
import 'dotenv/config';

const API_PYTHON_URL = process.env.API_PYTHON_URL as string;
export const resolvers = {
    Query: {
        RevisionSolicitud: async () => {
            try {
                const response = await axios.get(`${API_PYTHON_URL}/revisiones`);
                return response.data.map((revision: any) => ({
                    id: revision.id,
                    estado_revision: revision.estado_revision,
                    observacion: revision.observacion,
                    admin_id: revision.id_admin,
                }));
            } catch (error) {
                console.error('Error al obtener revisiones de solicitud:', error);
                return [];
            }
        },
        getRevisionSolicitud: async (_: any, { id }: any) => {
            try {
                const response = await axios.get(`${API_PYTHON_URL}/revisiones/${id}`);
                const revision = response.data;
                return {
                    id: revision.id,
                    estado_revision: revision.estado_revision,
                    observacion: revision.observacion,
                    admin_id: revision.id_admin,
                };
            } catch (error) {
                console.error('Error al obtener revisión de solicitud:', error);
                return null;
            }
        }
    },
    Mutation: {
      createRevisionSolicitud: async (_: any, { input }: any) => {
          try {
                    const response = await axios.post(`${API_PYTHON_URL}/revision/crear`, input);
                    const revision = response.data;
                    return {
                        id: revision.id,
                        estado_revision: revision.estado_revision,
                        observacion: revision.observacion,
                        admin_id: revision.admin_id,
                    };
                } catch (error) {
                    console.error('Error al crear revisión de solicitud:', error);
                    return null;
                }
            },
                

            updateRevisionSolicitud: async (_: any, { id, input }: any) => {
                try {
                    const response = await axios.put(`${API_PYTHON_URL}/revision/${id}`, input);
                    const revision = response.data;
                    return {
                        id: revision.id,
                        estado_revision: revision.estado_revision,
                        observacion: revision.observacion,
                        admin_id: revision.admin_id,
                    };
                } catch (error) {
                    console.error('Error al actualizar revisión de solicitud:', error);
                    return null;
                }
            },

            deleteRevisionSolicitud: async (_: any, { id }: any) => {
                try {
                    await axios.delete(`${API_PYTHON_URL}/revision/delete/${id}`);
                    return "Revisión de solicitud eliminada correctamente";
                } catch (error) {
                    console.error('Error al eliminar revisión de solicitud:', error);
                    return "Error al eliminar revisión de solicitud";
                }
            }
    
    }
            
}