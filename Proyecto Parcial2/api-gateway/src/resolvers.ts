import { mergeResolvers } from '@graphql-tools/merge';

// ============= Módulo 1 =============
import { resolvers as DonanteResolvers } from './modulo1_TS/donante/donante.resolver';
import { resolvers as DonacionResolvers } from './modulo1_TS/donacion/donacion.resolver';
import { resolvers as ProductoResolvers } from './modulo1_TS/producto/producto.resolver';
import { resolvers as TipoProductoResolvers } from './modulo1_TS/tipo-producto/tipo-producto.resolver';
import { resolvers as EstadoDonacionResolvers } from './modulo1_TS/estado-donacion/estado-donacion.resolver';

// ============= Módulo 2 =============
import { resolvers as ReceptorResolvers } from './modulo2_TS/receptor/receptor.resolver';
import { resolvers as SolicitudResolvers } from './modulo2_TS/solicitud/solicitud.resolver';
import { resolvers as EntregaResolvers } from './modulo2_TS/entrega/entrega.resolver';
import { resolvers as DireccionResolvers } from './modulo2_TS/direccion/direccion.resolver';
import { resolvers as HistorialReceptorResolvers } from './modulo2_TS/historial-receptor/historial-receptor.resolver';

// ============= Unificación ============
export const resolvers = mergeResolvers([
  // Módulo 1
  DonanteResolvers,
  DonacionResolvers,
  ProductoResolvers,
  TipoProductoResolvers,
  EstadoDonacionResolvers,

  // Módulo 2
  ReceptorResolvers,
  SolicitudResolvers,
  EntregaResolvers,
  DireccionResolvers,
  HistorialReceptorResolvers,
]);
