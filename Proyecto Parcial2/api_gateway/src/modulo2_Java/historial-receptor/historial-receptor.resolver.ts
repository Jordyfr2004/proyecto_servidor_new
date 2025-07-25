
import axios from 'axios';
import 'dotenv/config';

const API_JAVA_URL = process.env.API_JAVA_URL as string;
const API_URL = `${API_JAVA_URL}/historiales`;

export const resolvers = {
  Query: {
    historialesReceptor: async () => {
      try {
        const res = await axios.get(API_URL);
        return res.data.map((h: any) => ({
          id: h.idHistorial,
          fechaEntrega: h.fechaEntrega,
          observacion: h.observacion,
          fechaRegistro: h.fechaRegistro,
          fechaActualizacion: h.fechaActualizacion,
          tipoEvento: h.tipoEvento,
          estado: h.estado,
          detalleAdicional: h.detalleAdicional,
          cantidadEntregada: h.cantidadEntregada,
          unidad: h.unidad,
          valorEstimado: h.valorEstimado,
          usuarioRegistrador: h.usuarioRegistrador,
          idReceptor: h.idReceptor,
          idDonacion: h.idDonacion,
          idEntrega: h.idEntrega,
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
          fechaRegistro: h.fechaRegistro,
          fechaActualizacion: h.fechaActualizacion,
          tipoEvento: h.tipoEvento,
          estado: h.estado,
          detalleAdicional: h.detalleAdicional,
          cantidadEntregada: h.cantidadEntregada,
          unidad: h.unidad,
          valorEstimado: h.valorEstimado,
          usuarioRegistrador: h.usuarioRegistrador,
          idReceptor: h.idReceptor,
          idDonacion: h.idDonacion,
          idEntrega: h.idEntrega,
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
          fechaRegistro: h.fechaRegistro,
          fechaActualizacion: h.fechaActualizacion,
          tipoEvento: h.tipoEvento,
          estado: h.estado,
          detalleAdicional: h.detalleAdicional,
          cantidadEntregada: h.cantidadEntregada,
          unidad: h.unidad,
          valorEstimado: h.valorEstimado,
          usuarioRegistrador: h.usuarioRegistrador,
          idReceptor: h.idReceptor,
          idDonacion: h.idDonacion,
          idEntrega: h.idEntrega,
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
          fechaRegistro: h.fechaRegistro,
          fechaActualizacion: h.fechaActualizacion,
          tipoEvento: h.tipoEvento,
          estado: h.estado,
          detalleAdicional: h.detalleAdicional,
          cantidadEntregada: h.cantidadEntregada,
          unidad: h.unidad,
          valorEstimado: h.valorEstimado,
          usuarioRegistrador: h.usuarioRegistrador,
          idReceptor: h.idReceptor,
          idDonacion: h.idDonacion,
          idEntrega: h.idEntrega,
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
