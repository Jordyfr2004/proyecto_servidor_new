import { mergeTypeDefs } from '@graphql-tools/merge';

// ============= Módulo 1 =============
import { typeDefs as Donante } from './modulo1_TS/donante/donante.schema';
import { typeDefs as Donacion } from './modulo1_TS/donacion/donacion.schema';
import { typeDefs as Producto } from './modulo1_TS/producto/producto.schema';
import { typeDefs as TipoProducto } from './modulo1_TS/tipo-producto/tipo-producto.schema';
import { typeDefs as EstadoDonacion } from './modulo1_TS/estado-donacion/estado-donacion.schema';

// ============= Módulo 2 =============
import { receptorTypeDefs } from './modulo2_TS/receptor/receptor.schema';
import { solicitudTypeDefs } from './modulo2_TS/solicitud/solicitud.schema';
import { entregaTypeDefs } from './modulo2_TS/entrega/entrega.schema';
import { direccionTypeDefs } from './modulo2_TS/direccion/direccion.schema';
import { historialReceptorTypeDefs } from './modulo2_TS/historial-receptor/historial-receptor.schema'; // Asegúrate del nombre y ruta

// ============= Unificación ============
export const typeDefs = mergeTypeDefs([
  // Módulo 1
  Donante,
  Donacion,
  Producto,
  TipoProducto,
  EstadoDonacion,

  // Módulo 2
  receptorTypeDefs,
  solicitudTypeDefs,
  entregaTypeDefs,
  direccionTypeDefs,
  historialReceptorTypeDefs, // <--- la nueva entidad agregada aquí
]);
