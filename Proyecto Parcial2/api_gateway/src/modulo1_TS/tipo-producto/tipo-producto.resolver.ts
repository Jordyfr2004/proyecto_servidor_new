import axios from 'axios';
import 'dotenv/config';

const API_TS_BASE_URL = process.env.API_TS_BASE_URL as string;

export const resolvers = {
  Query: {
    tiposProducto: async () => {
      try {
        const response = await axios.get(`${API_TS_BASE_URL}/tipo-producto`);
        return response.data.map((tipo: any) => ({
          id: tipo.id_tipo,
          nombre: tipo.nombre,
          descripcion: tipo.descripcion,
        }));
      } catch (error) {
        console.error('Error al obtener tipos de producto:', error);
        return [];
      }
    },

    getTipoProducto: async (_: any, { id }: any) => {
      try {
        const response = await axios.get(`${API_TS_BASE_URL}/tipo-producto/${id}`);
        const tipo = response.data;
        return {
          id: tipo.id_tipo,
          nombre: tipo.nombre,
          descripcion: tipo.descripcion,
        };
      } catch (error) {
        console.error('Error al obtener tipo de producto:', error);
        return null;
      }
    }
  },

  Mutation: {
    crearTipoProducto: async (_: any, { input }: any) => {
      try {
        const response = await axios.post(`${API_TS_BASE_URL}/tipo-producto`, input);
        const tipo = response.data;
        return {
          id: tipo.id_tipo,
          nombre: tipo.nombre,
          descripcion: tipo.descripcion,
        };
      } catch (error) {
        console.error('Error al crear tipo de producto:', error);
        return null;
      }
    },

    updateTipoProducto: async (_: any, { id, input }: any) => {
      try {
        const response = await axios.put(`${API_TS_BASE_URL}/tipo-producto/${id}`, input);
        const tipo = response.data;
        return {
          id: tipo.id_tipo,
          nombre: tipo.nombre,
          descripcion: tipo.descripcion,
        };
      } catch (error) {
        console.error('Error al actualizar tipo de producto:', error);
        return null;
      }
    },

    deleteTipoProducto: async (_: any, { id }: any) => {
      try {
        await axios.delete(`${API_TS_BASE_URL}/tipo-producto/${id}`);
        return `Tipo de producto con ID ${id} eliminado correctamente.`;
      } catch (error) {
        console.error('Error al eliminar tipo de producto:', error);
        return `Error al eliminar tipo de producto con ID ${id}`;
      }
    }
  }
};
