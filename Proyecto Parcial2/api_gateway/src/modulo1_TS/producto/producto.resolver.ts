import axios from 'axios';
import 'dotenv/config';

const API_TS_BASE_URL = process.env.API_TS_BASE_URL as string;

export const resolvers = {
  Query: {
    productos: async () => {
      try {
        const response = await axios.get(`${API_TS_BASE_URL}/producto`);
        return response.data.map((producto: any) => ({
          id: producto.id_producto,
          nombre: producto.nombre,
          categoria: producto.categoria,
          peso: producto.peso,
          unidad_medida: producto.unidad_medida,
          stock: producto.stock,
          tipo: producto.tipo,
        }));
      } catch (error) {
        console.error('Error al obtener productos:', error);
        return [];
      }
    },

    getProducto: async (_: any, { id }: any) => {
      try {
        const response = await axios.get(`${API_TS_BASE_URL}/producto/${id}`);
        const producto = response.data;
        return {
          id: producto.id_producto,
          nombre: producto.nombre,
          categoria: producto.categoria,
          peso: producto.peso,
          unidad_medida: producto.unidad_medida,
          stock: producto.stock,
          tipo: producto.tipo,
        };
      } catch (error) {
        console.error('Error al obtener producto:', error);
        return null;
      }
    }
  },

  Mutation: {
    crearProducto: async (_: any, { input }: any) => {
      try {
        const response = await axios.post(`${API_TS_BASE_URL}/producto`, input);
        const producto = response.data;
        return {
          id: producto.id_producto,
          nombre: producto.nombre,
          categoria: producto.categoria,
          peso: producto.peso,
          unidad_medida: producto.unidad_medida,
          stock: producto.stock,
          tipo: producto.tipo,
        };
      } catch (error) {
        console.error('Error al crear producto:', error);
        return null;
      }
    },

    updateProducto: async (_: any, { id, input }: any) => {
      try {
        const response = await axios.put(`${API_TS_BASE_URL}/producto/${id}`, input);
        const producto = response.data;
        return {
          id: producto.id_producto,
          nombre: producto.nombre,
          categoria: producto.categoria,
          peso: producto.peso,
          unidad_medida: producto.unidad_medida,
          stock: producto.stock,
          tipo: producto.tipo,
        };
      } catch (error) {
        console.error('Error al actualizar producto:', error);
        return null;
      }
    },

    deleteProducto: async (_: any, { id }: any) => {
      try {
        await axios.delete(`${API_TS_BASE_URL}/producto/${id}`);
        return `Producto con ID ${id} eliminado correctamente.`;
      } catch (error) {
        console.error('Error al eliminar producto:', error);
        return `Error al eliminar producto con ID ${id}`;
      }
    }
  }
};
