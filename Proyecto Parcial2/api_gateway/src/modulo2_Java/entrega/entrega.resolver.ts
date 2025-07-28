

// --- INICIO CAMBIO: Notificación WebSocket en POST (crearEntrega) ---
import axios from 'axios';
import 'dotenv/config';
import { emitirNotificacionWebSocket } from '../../notificaciones'; // Importa la función para emitir notificaciones
// --- FIN CAMBIO ---

const API_JAVA_URL = process.env.API_JAVA_URL as string;
const API_URL = `${API_JAVA_URL}/entregas`;

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
        // --- INICIO CAMBIO: Emitir notificación WebSocket solo en POST ---
        emitirNotificacionWebSocket({
          tipo: 'nueva_entrega',
          data: e
        });
        // --- FIN CAMBIO ---
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
