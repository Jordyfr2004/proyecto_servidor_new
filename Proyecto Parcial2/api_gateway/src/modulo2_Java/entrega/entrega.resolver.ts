import axios from 'axios';

const API_URL = 'http://localhost:8082/api/entregas';

export const resolvers = {
  Query: {
    entregas: async () => {
      const res = await axios.get(API_URL);
      return res.data.map((e: any) => ({
        id: e.idEntrega,
        fechaEntrega: e.fechaEntrega,
        observacion: e.observacion,
        cantidadEntregada: e.cantidadEntregada,
        unidad: e.unidad,
        estadoEntrega: e.estadoEntrega,
        idSolicitud: e.idSolicitud,
        idDonacion: e.idDonacion,
        idsHistorialEntregas: e.idsHistorialEntregas,
      }));
    },
    entrega: async (_: any, { id }: any) => {
      const res = await axios.get(`${API_URL}/${id}`);
      const e = res.data;
      return {
        id: e.idEntrega,
        fechaEntrega: e.fechaEntrega,
        observacion: e.observacion,
        cantidadEntregada: e.cantidadEntregada,
        unidad: e.unidad,
        estadoEntrega: e.estadoEntrega,
        idSolicitud: e.idSolicitud,
        idDonacion: e.idDonacion,
        idsHistorialEntregas: e.idsHistorialEntregas,
      };
    },
  },
  Mutation: {
    crearEntrega: async (_: any, { input }: any) => {
      try {
        const res = await axios.post(API_URL, input);
        const e = res.data;
        return {
          id: e.idEntrega,
          fechaEntrega: e.fechaEntrega,
          observacion: e.observacion,
          cantidadEntregada: e.cantidadEntregada,
          unidad: e.unidad,
          estadoEntrega: e.estadoEntrega,
          idSolicitud: e.idSolicitud,
          idDonacion: e.idDonacion,
          idsHistorialEntregas: e.idsHistorialEntregas,
        };
      } catch (error: any) {
        throw new Error('Error al crear entrega: ' + JSON.stringify(error.response?.data || error.message));
      }
    },
    actualizarEntrega: async (_: any, { id, input }: any) => {
      try {
        const res = await axios.put(`${API_URL}/${id}`, input);
        const e = res.data;
        return {
          id: e.idEntrega,
          fechaEntrega: e.fechaEntrega,
          observacion: e.observacion,
          cantidadEntregada: e.cantidadEntregada,
          unidad: e.unidad,
          estadoEntrega: e.estadoEntrega,
          idSolicitud: e.idSolicitud,
          idDonacion: e.idDonacion,
          idsHistorialEntregas: e.idsHistorialEntregas,
        };
      } catch (error: any) {
        throw new Error('Error al actualizar entrega: ' + JSON.stringify(error.response?.data || error.message));
      }
    },
    eliminarEntrega: async (_: any, { id }: any) => {
      try {
        await axios.delete(`${API_URL}/${id}`);
        return "Entrega eliminada correctamente";
      } catch (error: any) {
        throw new Error('Error al eliminar entrega: ' + JSON.stringify(error.response?.data || error.message));
      }
    }
  }
};
