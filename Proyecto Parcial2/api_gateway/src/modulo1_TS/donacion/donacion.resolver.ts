import axios from 'axios';
import 'dotenv/config';

const API_TS_BASE_URL = process.env.API_TS_BASE_URL as string;

export const resolvers = {
  Query: {
    donaciones: async () => {
      try {
        const response = await axios.get(`${API_TS_BASE_URL}/donacion`);
        return response.data.map((donacion: any) => ({
          id: donacion.id_donacion,
          fecha: donacion.fecha,
          cantidad: donacion.cantidad,
          descripcion: donacion.descripcion,
          donante: donacion.donante,
          productos: donacion.productos,
          estado: donacion.estado,
        }));
      } catch (error) {
        console.error('Error al obtener donaciones:', error);
        return [];
      }
    },

    getDonacion: async (_: any, { id }: any) => {
      try {
        const response = await axios.get(`${API_TS_BASE_URL}/donacion/${id}`);
        const donacion = response.data;
        return {
          id: donacion.id_donacion,
          fecha: donacion.fecha,
          cantidad: donacion.cantidad,
          descripcion: donacion.descripcion,
          donante: donacion.donante,
          productos: donacion.productos,
          estado: donacion.estado,
        };
      } catch (error) {
        console.error('Error al obtener donación:', error);
        return null;
      }
    },
  },

  Mutation: {
    crearDonacion: async (_: any, { input }: any) => {
      try {
        const response = await axios.post(`${API_TS_BASE_URL}/donacion`, input);
        const donacion = response.data;
        return {
          id: donacion.id_donacion,
          fecha: donacion.fecha,
          cantidad: donacion.cantidad,
          descripcion: donacion.descripcion,
          donante: donacion.donante,
          productos: donacion.productos,
          estado: donacion.estado,
        };
      } catch (error) {
        console.error('Error al crear donación:', error);
        return null;
      }
    },

    updateDonacion: async (_: any, { id, input }: any) => {
      try {
        const response = await axios.put(`${API_TS_BASE_URL}/donacion/${id}`, input);
        const donacion = response.data;
        return {
          id: donacion.id_donacion,
          fecha: donacion.fecha,
          cantidad: donacion.cantidad,
          descripcion: donacion.descripcion,
          donante: donacion.donante,
          productos: donacion.productos,
          estado: donacion.estado,
        };
      } catch (error) {
        console.error('Error al actualizar donación:', error);
        return null;
      }
    },

    deleteDonacion: async (_: any, { id }: any) => {
      try {
        await axios.delete(`${API_TS_BASE_URL}/donacion/${id}`);
        return `Donación con ID ${id} eliminada correctamente.`;
      } catch (error) {
        console.error('Error al eliminar donación:', error);
        return `Error al eliminar donación con ID ${id}`;
      }
    },
  },
};
