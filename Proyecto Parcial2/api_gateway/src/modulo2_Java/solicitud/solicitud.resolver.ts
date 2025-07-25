
import axios from 'axios';
import 'dotenv/config';

const API_JAVA_URL = process.env.API_JAVA_URL as string;
const API_URL = `${API_JAVA_URL}/solicitudes`;

export const resolvers = {
  Query: {
    solicitudes: async () => {
      const res = await axios.get(API_URL);
      return res.data.map((s: any) => ({
        id: s.idSolicitud,
        tipoDonacion: s.tipoDonacion,
        descripcion: s.descripcion,
        fechaSolicitud: s.fechaSolicitud, // Puede venir en formato ISO
        estado: s.estado,
        idReceptor: s.idReceptor,
      }));
    },
    solicitud: async (_: any, { id }: any) => {
      const res = await axios.get(`${API_URL}/${id}`);
      const s = res.data;
      return {
        id: s.idSolicitud,
        tipoDonacion: s.tipoDonacion,
        descripcion: s.descripcion,
        fechaSolicitud: s.fechaSolicitud,
        estado: s.estado,
        idReceptor: s.idReceptor,
      };
    },
  },
  Mutation: {
    crearSolicitud: async (_: any, { input }: any) => {
      try {
        const res = await axios.post(API_URL, input);
        const s = res.data;
        return {
          id: s.idSolicitud,
          tipoDonacion: s.tipoDonacion,
          descripcion: s.descripcion,
          fechaSolicitud: s.fechaSolicitud,
          estado: s.estado,
          idReceptor: s.idReceptor,
        };
      } catch (error: any) {
        throw new Error('Error al crear solicitud: ' + JSON.stringify(error.response?.data || error.message));
      }
    },
    actualizarSolicitud: async (_: any, { id, input }: any) => {
      try {
        const res = await axios.put(`${API_URL}/${id}`, input);
        const s = res.data;
        return {
          id: s.idSolicitud,
          tipoDonacion: s.tipoDonacion,
          descripcion: s.descripcion,
          fechaSolicitud: s.fechaSolicitud,
          estado: s.estado,
          idReceptor: s.idReceptor,
        };
      } catch (error: any) {
        throw new Error('Error al actualizar solicitud: ' + JSON.stringify(error.response?.data || error.message));
      }
    },
    eliminarSolicitud: async (_: any, { id }: any) => {
      try {
        await axios.delete(`${API_URL}/${id}`);
        return "Solicitud eliminada correctamente";
      } catch (error: any) {
        throw new Error('Error al eliminar solicitud: ' + JSON.stringify(error.response?.data || error.message));
      }
    }
  }
};
