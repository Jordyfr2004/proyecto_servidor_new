import axios from 'axios';

const API_URL = 'http://localhost:8082/api/receptores';

export const resolvers = {
  Query: {
    // Endpoint de prueba para verificar conectividad
    testConnection: async () => {
      try {
        const res = await axios.get(API_URL);
        return `Conectado exitosamente. Encontrados ${res.data.length} receptores`;
      } catch (error: any) {
        console.error('Test de conexión falló:', error.message);
        return `Error de conexión: ${error.code} - ${error.message}`;
      }
    },
    receptores: async () => {
      try {
        const res = await axios.get(API_URL);
        // Mapear idReceptor -> id
        return res.data.map((r: any) => ({
          id: r.idReceptor,
          nombre: r.nombre,
          cedula: r.cedula,
          telefono: r.telefono,
          correo: r.correo,
          direccion: r.direccion,
          idsDirecciones: r.idsDirecciones || [],
          idsSolicitudes: r.idsSolicitudes || [],
          idsHistorial: r.idsHistorial || [],
        }));
      } catch (error: any) {
        console.error('Error en receptores:', error.message);
        const message = error?.response?.data?.message || error?.message || 'Error de conexión con el servidor';
        throw new Error(`Error al obtener receptores: ${message}`);
      }
    },
    receptor: async (_: any, { id }: any) => {
      try {
        const res = await axios.get(`${API_URL}/${id}`);
        const r = res.data;
        return {
          id: r.idReceptor,
          nombre: r.nombre,
          cedula: r.cedula,
          telefono: r.telefono,
          correo: r.correo,
          direccion: r.direccion,
          idsDirecciones: r.idsDirecciones || [],
          idsSolicitudes: r.idsSolicitudes || [],
          idsHistorial: r.idsHistorial || [],
        };
      } catch (error: any) {
        console.error('Error en receptor:', error.message);
        const message = error?.response?.data?.message || error?.message || 'Error de conexión con el servidor';
        throw new Error(`Error al obtener receptor: ${message}`);
      }
    },
  },
  Mutation: {
    crearReceptor: async (_: any, { input }: any) => {
      try {
        console.log('Datos enviados al servidor Java:', JSON.stringify(input, null, 2));
        const res = await axios.post(API_URL, input);
        const r = res.data;
        return {
          id: r.idReceptor,
          nombre: r.nombre,
          cedula: r.cedula,
          telefono: r.telefono,
          correo: r.correo,
          direccion: r.direccion,
          idsDirecciones: r.idsDirecciones || [],
          idsSolicitudes: r.idsSolicitudes || [],
          idsHistorial: r.idsHistorial || [],
        };
      } catch (error: any) {
        console.error('Error al crear receptor:', error.message);
        console.error('Respuesta del servidor:', error.response?.data);
        console.error('Status:', error.response?.status);
        console.error('Headers:', error.response?.headers);
        const message = error?.response?.data?.message || error?.response?.data || error?.message || 'Error de conexión con el servidor';
        throw new Error(`Error al crear receptor: ${JSON.stringify(message)}`);
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
          idsDirecciones: r.idsDirecciones || [],
          idsSolicitudes: r.idsSolicitudes || [],
          idsHistorial: r.idsHistorial || [],
        };
      } catch (error: any) {
        console.error('Error al actualizar receptor:', error.message);
        const message = error?.response?.data?.message || error?.message || 'Error de conexión con el servidor';
        throw new Error(`Error al actualizar receptor: ${message}`);
      }
    },
    eliminarReceptor: async (_: any, { id }: any) => {
      try {
        await axios.delete(`${API_URL}/${id}`);
        return "Receptor eliminado correctamente";
      } catch (error: any) {
        console.error('Error al eliminar receptor:', error.message);
        const message = error?.response?.data?.message || error?.message || 'Error de conexión con el servidor';
        throw new Error(`Error al eliminar receptor: ${message}`);
      }
    }
  }
};
