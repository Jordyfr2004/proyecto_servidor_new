import axios from 'axios';

export const resolvers = {
  Query: {
    donantes: async () => {
      try {
        const response = await axios.get('http://localhost:3000/api/donante');
        return response.data.map((donante: any) => ({
          id: donante.id_donante,
          nombre: donante.nombre,
          tipo: donante.tipo,
          correo: donante.correo,
          telefono: donante.telefono,
          direccion: donante.direccion,
          fecha_registro: donante.fecha_registro,
          donaciones: donante.donaciones?.map((d: any) => ({
            id: d.id_donacion,
            fecha: d.fecha,
            cantidad: d.cantidad,
            descripcion: d.descripcion,
          }))
        }));
      } catch (error) {
        console.error('Error al obtener donantes:', error);
        return [];
      }
    },

    getDonante: async (_: any, { id }: any) => {
      try {
        const response = await axios.get(`http://localhost:3000/api/donante/${id}`);
        const donante = response.data;
        return {
          id: donante.id_donante,
          nombre: donante.nombre,
          tipo: donante.tipo,
          correo: donante.correo,
          telefono: donante.telefono,
          direccion: donante.direccion,
          fecha_registro: donante.fecha_registro,
          donaciones: donante.donaciones?.map((d: any) => ({
            id: d.id_donacion,
            fecha: d.fecha,
            cantidad: d.cantidad,
            descripcion: d.descripcion,
          }))
        };
      } catch (error) {
        console.error('Error al obtener donante:', error);
        return null;
      }
    },
  },

  Mutation: {
    crearDonante: async (_: any, { input }: any) => {
      try {
        const response = await axios.post('http://localhost:3000/api/donante', input);
        const donante = response.data;
        return {
          id: donante.id_donante,
          nombre: donante.nombre,
          tipo: donante.tipo,
          correo: donante.correo,
          telefono: donante.telefono,
          direccion: donante.direccion,
          fecha_registro: donante.fecha_registro,
          donaciones: []
        };
      } catch (error) {
        console.error('Error al crear donante:', error);
        return null;
      }
    },

    updateDonante: async (_: any, { id, input }: any) => {
      try {
        const response = await axios.put(`http://localhost:3000/api/donante/${id}`, input);
        const donante = response.data;
        return {
          id: donante.id_donante,
          nombre: donante.nombre,
          tipo: donante.tipo,
          correo: donante.correo,
          telefono: donante.telefono,
          direccion: donante.direccion,
          fecha_registro: donante.fecha_registro,
          donaciones: donante.donaciones?.map((d: any) => ({
            id: d.id_donacion,
            fecha: d.fecha,
            cantidad: d.cantidad,
            descripcion: d.descripcion,
          }))
        };
      } catch (error) {
        console.error('Error al actualizar donante:', error);
        return null;
      }
    },

    deleteDonante: async (_: any, { id }: any) => {
      try {
        await axios.delete(`http://localhost:3000/api/donante/${id}`);
        return `Donante con ID ${id} eliminado correctamente.`;
      } catch (error) {
        console.error('Error al eliminar donante:', error);
        return `Error al eliminar donante con ID ${id}`;
      }
    }
  }
};
