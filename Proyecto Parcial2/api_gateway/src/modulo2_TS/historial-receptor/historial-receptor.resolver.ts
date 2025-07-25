import axios from 'axios';

const API_URL = 'http://localhost:8082/api/historiales';

export const resolvers = {
  Query: {
    historialesReceptor: async () => {
      try {
        const res = await axios.get(API_URL);
        return res.data.map((h: any) => ({
          id: h.idHistorial,
          fechaEntrega: h.fechaEntrega,
          observacion: h.observacion,
          idReceptor: h.idReceptor,
        }));
      } catch (error: any) {
        const message = error?.response?.data?.message || error.message || 'Error desconocido';
        throw new Error('Error al consultar historiales: ' + message);
      }
    },
    historialReceptor: async (_: any, { id }: any) => {
      try {
        const res = await axios.get(`${API_URL}/${id}`);
        const h = res.data;
        return {
          id: h.idHistorial,
          fechaEntrega: h.fechaEntrega,
          observacion: h.observacion,
          idReceptor: h.idReceptor,
        };
      } catch (error: any) {
        const message = error?.response?.data?.message || error.message || 'Error desconocido';
        throw new Error('Error al consultar historial: ' + message);
      }
    },
  },
  Mutation: {
    crearHistorialReceptor: async (_: any, { input }: any) => {
      try {
        const res = await axios.post(API_URL, input);
        const h = res.data;
        return {
          id: h.idHistorial,
          fechaEntrega: h.fechaEntrega,
          observacion: h.observacion,
          idReceptor: h.idReceptor,
        };
      } catch (error: any) {
        const message = error?.response?.data?.message || error.message || 'Error desconocido';
        throw new Error('Error al crear historial: ' + message);
      }
    },
    actualizarHistorialReceptor: async (_: any, { id, input }: any) => {
      try {
        const res = await axios.put(`${API_URL}/${id}`, input);
        const h = res.data;
        return {
          id: h.idHistorial,
          fechaEntrega: h.fechaEntrega,
          observacion: h.observacion,
          idReceptor: h.idReceptor,
        };
      } catch (error: any) {
        const message = error?.response?.data?.message || error.message || 'Error desconocido';
        throw new Error('Error al actualizar historial: ' + message);
      }
    },
    eliminarHistorialReceptor: async (_: any, { id }: any) => {
      try {
        await axios.delete(`${API_URL}/${id}`);
        return "Historial eliminado correctamente";
      } catch (error: any) {
        const message = error?.response?.data?.message || error.message || 'Error desconocido';
        throw new Error('Error al eliminar historial: ' + message);
      }
    }
  }
};
