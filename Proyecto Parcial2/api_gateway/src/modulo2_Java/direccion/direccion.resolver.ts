import axios from 'axios';

const API_URL = 'http://localhost:8082/api/direcciones';

export const resolvers = {
  Query: {
    direcciones: async () => {
      const res = await axios.get(API_URL);
      return res.data.map((d: any) => ({
        id: d.idDireccion,
        calle: d.calle,
        ciudad: d.ciudad,
        provincia: d.provincia,
        referencia: d.referencia,
        codigoPostal: d.codigoPostal,
        latitud: d.latitud,
        longitud: d.longitud,
        esPrincipal: d.esPrincipal,
        fechaCreacion: d.fechaCreacion,
        fechaActualizacion: d.fechaActualizacion,
        idReceptor: d.idReceptor,
      }));
    },
    direccion: async (_: any, { id }: any) => {
      const res = await axios.get(`${API_URL}/${id}`);
      const d = res.data;
      return {
        id: d.idDireccion,
        calle: d.calle,
        ciudad: d.ciudad,
        provincia: d.provincia,
        referencia: d.referencia,
        codigoPostal: d.codigoPostal,
        latitud: d.latitud,
        longitud: d.longitud,
        esPrincipal: d.esPrincipal,
        fechaCreacion: d.fechaCreacion,
        fechaActualizacion: d.fechaActualizacion,
        idReceptor: d.idReceptor,
      };
    },
  },
  Mutation: {
    crearDireccion: async (_: any, { input }: any) => {
      try {
        const res = await axios.post(API_URL, input);
        const d = res.data;
        return {
          id: d.idDireccion,
          calle: d.calle,
          ciudad: d.ciudad,
          provincia: d.provincia,
          referencia: d.referencia,
          codigoPostal: d.codigoPostal,
          latitud: d.latitud,
          longitud: d.longitud,
          esPrincipal: d.esPrincipal,
          fechaCreacion: d.fechaCreacion,
          fechaActualizacion: d.fechaActualizacion,
          idReceptor: d.idReceptor,
        };
      } catch (error: any) {
        throw new Error('Error al crear dirección: ' + JSON.stringify(error.response?.data || error.message));
      }
    },
    actualizarDireccion: async (_: any, { id, input }: any) => {
      try {
        const res = await axios.put(`${API_URL}/${id}`, input);
        const d = res.data;
        return {
          id: d.idDireccion,
          calle: d.calle,
          ciudad: d.ciudad,
          provincia: d.provincia,
          referencia: d.referencia,
          codigoPostal: d.codigoPostal,
          latitud: d.latitud,
          longitud: d.longitud,
          esPrincipal: d.esPrincipal,
          fechaCreacion: d.fechaCreacion,
          fechaActualizacion: d.fechaActualizacion,
          idReceptor: d.idReceptor,
        };
      } catch (error: any) {
        throw new Error('Error al actualizar dirección: ' + JSON.stringify(error.response?.data || error.message));
      }
    },
    eliminarDireccion: async (_: any, { id }: any) => {
      try {
        await axios.delete(`${API_URL}/${id}`);
        return "Dirección eliminada correctamente";
      } catch (error: any) {
        throw new Error('Error al eliminar dirección: ' + JSON.stringify(error.response?.data || error.message));
      }
    },
  }
};
