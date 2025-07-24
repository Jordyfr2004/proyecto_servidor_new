import axios from 'axios';

const API_URL = 'http://localhost:8082/api/receptores';

export const resolvers = {
  Query: {
    receptores: async () => {
      const res = await axios.get(API_URL);
      // Mapear idReceptor -> id
      return res.data.map((r: any) => ({
        id: r.idReceptor,
        nombre: r.nombre,
        cedula: r.cedula,
        telefono: r.telefono,
        correo: r.correo,
        direccion: r.direccion,
      }));
    },
    receptor: async (_: any, { id }: any) => {
      const res = await axios.get(`${API_URL}/${id}`);
      const r = res.data;
      return {
        id: r.idReceptor,
        nombre: r.nombre,
        cedula: r.cedula,
        telefono: r.telefono,
        correo: r.correo,
        direccion: r.direccion,
      };
    },
  },
  Mutation: {
    crearReceptor: async (_: any, { input }: any) => {
      try {
        const res = await axios.post(API_URL, input);
        const r = res.data;
        return {
          id: r.idReceptor,
          nombre: r.nombre,
          cedula: r.cedula,
          telefono: r.telefono,
          correo: r.correo,
          direccion: r.direccion,
        };
      } catch (error: any) {
        throw new Error('Error al crear receptor: ' + JSON.stringify(error.response?.data || error.message));
      }
    },
    actualizarReceptor: async (_: any, { id, input }: any) => {
      try {
        const res = await axios.put(`${API_URL}/${id}`, input);
        const r = res.data;
        return {
          id: r.idReceptor,
          nombre: r.nombre,
          cedula: r.cedula,
          telefono: r.telefono,
          correo: r.correo,
          direccion: r.direccion,
        };
      } catch (error: any) {
        throw new Error('Error al actualizar receptor: ' + JSON.stringify(error.response?.data || error.message));
      }
    },
    eliminarReceptor: async (_: any, { id }: any) => {
      try {
        await axios.delete(`${API_URL}/${id}`);
        return "Receptor eliminado correctamente";
      } catch (error: any) {
        throw new Error('Error al eliminar receptor: ' + JSON.stringify(error.response?.data || error.message));
      }
    }
  }
};
