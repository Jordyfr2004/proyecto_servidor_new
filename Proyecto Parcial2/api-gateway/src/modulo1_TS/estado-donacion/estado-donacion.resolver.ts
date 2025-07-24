import axios from 'axios';

export const resolvers = {
  Query: {
    estadosDonacion: async () => {
      try {
        const response = await axios.get('http://localhost:3000/api/estado-donacion');
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
        const response = await axios.get(`http://localhost:3000/api/estado-donacion/${id}`);
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
        const response = await axios.post('http://localhost:3000/api/estado-donacion', input);
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
        const response = await axios.put(`http://localhost:3000/api/estado-donacion/${id}`, input);
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
        await axios.delete(`http://localhost:3000/api/estado-donacion/${id}`);
        return `Estado de donación con ID ${id} eliminado correctamente.`;
      } catch (error) {
        console.error('Error al eliminar estado de donación:', error);
        return `Error al eliminar estado de donación con ID ${id}`;
      }
    }
  }
};
