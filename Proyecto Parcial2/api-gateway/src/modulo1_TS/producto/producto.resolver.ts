import axios from 'axios';

export const resolvers = {
  Query: {
    productos: async () => {
      try {
        const response = await axios.get('http://localhost:3000/api/producto');
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
        const response = await axios.get(`http://localhost:3000/api/producto/${id}`);
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
        const response = await axios.post('http://localhost:3000/api/producto', input);
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
        const response = await axios.put(`http://localhost:3000/api/producto/${id}`, input);
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
        await axios.delete(`http://localhost:3000/api/producto/${id}`);
        return `Producto con ID ${id} eliminado correctamente.`;
      } catch (error) {
        console.error('Error al eliminar producto:', error);
        return `Error al eliminar producto con ID ${id}`;
      }
    }
  }
};
