import axios from 'axios';

export const resolvers = {
  Query: {
    donaciones: async () => {
      try {
        const response = await axios.get('http://localhost:3000/api/donacion');
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
        const response = await axios.get(`http://localhost:3000/api/donacion/${id}`);
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
        const response = await axios.post('http://localhost:3000/api/donacion', input);
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
        const response = await axios.put(`http://localhost:3000/api/donacion/${id}`, input);
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
        await axios.delete(`http://localhost:3000/api/donacion/${id}`);
        return `Donación con ID ${id} eliminada correctamente.`;
      } catch (error) {
        console.error('Error al eliminar donación:', error);
        return `Error al eliminar donación con ID ${id}`;
      }
    },
  },
};
