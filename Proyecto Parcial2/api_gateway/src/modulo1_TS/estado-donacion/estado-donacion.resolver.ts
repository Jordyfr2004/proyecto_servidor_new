import axios from 'axios';
import 'dotenv/config';

const API_TS_BASE_URL = process.env.API_TS_BASE_URL as string;

export const resolvers = {
  Query: {
    estadosDonacion: async () => {
      try {
        const response = await axios.get(`${API_TS_BASE_URL}/estado-donacion`);
        return response.data.map((estado: any) => ({
          id: estado.id_estado,
          nombre: estado.nombre,
          descripcion: estado.descripcion,
        }));
      } catch (error) {
        console.error('Error al obtener estados de donación:', error);
        return [];
      }
    },

    getEstadoDonacion: async (_: any, { id }: any) => {
      try {
        const response = await axios.get(`${API_TS_BASE_URL}/estado-donacion/${id}`);
        const estado = response.data;
        return {
          id: estado.id_estado,
          nombre: estado.nombre,
          descripcion: estado.descripcion,
        };
      } catch (error) {
        console.error('Error al obtener estado de donación:', error);
        return null;
      }
    }
  },

  Mutation: {
    crearEstadoDonacion: async (_: any, { input }: any) => {
      try {
        const response = await axios.post(`${API_TS_BASE_URL}/estado-donacion`, input);
        const estado = response.data;
        return {
          id: estado.id_estado,
          nombre: estado.nombre,
          descripcion: estado.descripcion,
        };
      } catch (error) {
        console.error('Error al crear estado de donación:', error);
        return null;
      }
    },

    updateEstadoDonacion: async (_: any, { id, input }: any) => {
      try {
        const response = await axios.put(`${API_TS_BASE_URL}/estado-donacion/${id}`, input);
        const estado = response.data;
        return {
          id: estado.id_estado,
          nombre: estado.nombre,
          descripcion: estado.descripcion,
        };
      } catch (error) {
        console.error('Error al actualizar estado de donación:', error);
        return null;
      }
    },

    deleteEstadoDonacion: async (_: any, { id }: any) => {
      try {
        await axios.delete(`${API_TS_BASE_URL}/estado-donacion/${id}`);
        return `Estado de donación con ID ${id} eliminado correctamente.`;
      } catch (error) {
        console.error('Error al eliminar estado de donación:', error);
        return `Error al eliminar estado de donación con ID ${id}`;
      }
    }
  }
};
