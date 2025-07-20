import axios from "axios";

const API_PYTHON_URL = 'http://localhost:5000/admin';

export const resolvers = {
  Query: {
    Eventos: async () => {
      try {
        const response = await axios.get(`${API_PYTHON_URL}/eventos`);
        return response.data.map((evento: any) => ({
          id: evento.id,
          descripcion: evento.descripcion,
          tipo_evento_id: evento.tipo_evento_id,
        }));
      } catch (error) {
        console.error('Error al obtener eventos:', error);
        return [];
      }
    },

    getEvento: async (_: any, { id }: any) => {
      try {
        const response = await axios.get(`${API_PYTHON_URL}/eventos/${id}`);
        const evento = response.data;
        return {
          id: evento.id,
          descripcion: evento.descripcion,
          tipo_evento_id: evento.tipo_evento_id,
        };
      } catch (error) {
        console.error('Error al obtener evento:', error);
        return null;
      }
    },
  },

  Mutation: {
    createEvento: async (_: any, { input }: any) => {
      try {
        const response = await axios.post(`${API_PYTHON_URL}/eventos/crear`, input);
        const evento = response.data;
        return {
          id: evento.id,
          descripcion: evento.descripcion,
          tipo_evento_id: evento.tipo_evento_id,
        };
      } catch (error) {
        console.error('Error al crear evento:', error);
        return null;
      }
    },

    updateEvento: async (_: any, { id, input }: any) => {
      try {
        const response = await axios.put(`${API_PYTHON_URL}/eventos/${id}`, input);
        const evento = response.data;
        return {
          id: evento.id,
          descripcion: evento.descripcion,
          tipo_evento_id: evento.tipo_evento_id,
        };
      } catch (error) {
        console.error('Error al actualizar evento:', error);
        return null;
      }
    },

    deleteEvento: async (_: any, { id }: any) => {
      try {
        await axios.delete(`${API_PYTHON_URL}/eventos/delete/${id}`);
        return "Evento eliminado correctamente";
      } catch (error) {
        console.error('Error al eliminar evento:', error);
        return "Error al eliminar evento";
      }
    }
  }
};
