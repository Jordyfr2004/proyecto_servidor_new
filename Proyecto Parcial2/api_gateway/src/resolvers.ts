import { mergeResolvers } from '@graphql-tools/merge';

// Tus resolvers del módulo 1
import { resolvers as DonanteResolvers } from './modulo1_TS/donante/donante.resolver';
import { resolvers as DonacionResolvers } from './modulo1_TS/donacion/donacion.resolver';
import { resolvers as ProductoResolvers } from './modulo1_TS/producto/producto.resolver';
import { resolvers as TipoProductoResolvers } from './modulo1_TS/tipo-producto/tipo-producto.resolver';
import { resolvers as EstadoDonacionResolvers } from './modulo1_TS/estado-donacion/estado-donacion.resolver';

// Resolvers del módulo 2
import { resolvers as ReceptorResolvers } from './modulo2_Java/receptor/receptor.resolver';
import { resolvers as DireccionResolvers } from './modulo2_Java/direccion/direccion.resolver';
import { resolvers as EntregaResolvers } from './modulo2_Java/entrega/entrega.resolver';
import { resolvers as SolicitudResolvers } from './modulo2_Java/solicitud/solicitud.resolver';
import { resolvers as HistorialReceptorResolvers } from './modulo2_Java/historial-receptor/historial-receptor.resolver';

// Resolvers del módulo 3
import { resolvers as AdministradoresResolvers } from './modulo3_PY/administrador/administrador.resolver';
import { resolvers as AgendasResolvers } from './modulo3_PY/agenda/agenda.resolver';
import { resolvers as EventosResolvers } from './modulo3_PY/evento/evento.resolver';
import { resolvers as TipoEventoResolvers } from './modulo3_PY/tipoevento/tipoevento.resolver';
import { resolvers as RevisionSolicitudResolvers } from './modulo3_PY/revisionsolicitud/revisionsolicitud.resolver';


export const resolvers = mergeResolvers([
  // Módulo 1
  DonanteResolvers,
  DonacionResolvers,
  ProductoResolvers,
  TipoProductoResolvers,
  EstadoDonacionResolvers,
  // Módulo 2
  ReceptorResolvers,
  DireccionResolvers,
  EntregaResolvers,
  SolicitudResolvers,
  HistorialReceptorResolvers,
  // Módulo 3
  AdministradoresResolvers,
  AgendasResolvers,
  EventosResolvers,
  TipoEventoResolvers,
  RevisionSolicitudResolvers
]);
