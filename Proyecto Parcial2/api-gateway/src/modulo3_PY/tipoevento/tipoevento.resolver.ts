import axios from 'axios';
import 'dotenv/config';

const API_PYTHON_URL = process.env.API_PYTHON_URL as string;

export const resolvers = {
  Query: {
    TipoEventos: async () => {
      try {
        const response = await axios.get(`${API_PYTHON_URL}/tipos`);
        return response.data.map((tipoEvento: any) => ({
          id: tipoEvento.id,
          tipo: tipoEvento.tipo,
          admin_id: tipoEvento.admin_id,
        }));
      } catch (error) {
        console.error('Error al obtener tipos de eventos:', error);
        return [];
      }
    },

    getTipoEvento: async (_: any, { id }: any) => {
      try {
        const response = await axios.get(`${API_PYTHON_URL}/tipos/${id}`);
        const tipoEvento = response.data;
        return {
          id: tipoEvento.id,
          tipo: tipoEvento.tipo,
          admin_id: tipoEvento.admin_id,
        };
      } catch (error) {
        console.error('Error al obtener tipo de evento:', error);
        return null;
      }
    },
  },

  Mutation: {
    createTipoEvento: async (_: any, { input }: any) => {
      try {
        const response = await axios.post(`${API_PYTHON_URL}/tipos/crear`, input);
        const tipoEvento = response.data;
        return {
          id: tipoEvento.id,
          tipo: tipoEvento.tipo,
          admin_id: tipoEvento.admin_id,
        };
      } catch (error) {
        console.error('Error al crear tipo de evento:', error);
        return null;
      }
    },

    updateTipoEvento: async (_: any, { id, input }: any) => {
      try {
        const response = await axios.put(`${API_PYTHON_URL}/tipos/${id}`, input);
        const tipoEvento = response.data;
        return {
          id: tipoEvento.id,
          tipo: tipoEvento.tipo,
          admin_id: tipoEvento.admin_id,
        };
      } catch (error) {
        console.error('Error al actualizar tipo de evento:', error);
        return null;
      }
    },

    deleteTipoEvento: async (_: any, { id }: any) => {
      try {
        await axios.delete(`${API_PYTHON_URL}/tipos/delete/${id}`);
        return `Tipo de evento con ID ${id} eliminado correctamente`;
      } catch (error) {
        console.error('Error al eliminar tipo de evento:', error);
        return `Error al eliminar tipo de evento con ID ${id}`;
      }
    },
  },
};
