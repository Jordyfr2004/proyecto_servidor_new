import axios from 'axios';

export const resolvers = {
  Query: {
    tiposProducto: async () => {
      try {
        const response = await axios.get('http://localhost:3000/api/tipo-producto');
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
        const response = await axios.get(`http://localhost:3000/api/tipo-producto/${id}`);
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
        const response = await axios.post('http://localhost:3000/api/tipo-producto', input);
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
        const response = await axios.put(`http://localhost:3000/api/tipo-producto/${id}`, input);
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
        await axios.delete(`http://localhost:3000/api/tipo-producto/${id}`);
        return `Tipo de producto con ID ${id} eliminado correctamente.`;
      } catch (error) {
        console.error('Error al eliminar tipo de producto:', error);
        return `Error al eliminar tipo de producto con ID ${id}`;
      }
    }
  }
};
