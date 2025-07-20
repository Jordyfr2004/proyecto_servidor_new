import { mergeTypeDefs } from '@graphql-tools/merge';

// Tus entidades del módulo 1
import { typeDefs as Donante } from './modulo1_TS/donante/donante.schema';
import { typeDefs as Donacion } from './modulo1_TS/donacion/donacion.schema';
import { typeDefs as Producto } from './modulo1_TS/producto/producto.schema';
import { typeDefs as TipoProducto } from './modulo1_TS/tipo-producto/tipo-producto.schema';
import { typeDefs as EstadoDonacion } from './modulo1_TS/estado-donacion/estado-donacion.schema';

// Entidades del Módulo 2 
import { receptorTypeDefs } from './modulo2_TS/receptor/receptor.schema';
import { solicitudTypeDefs } from './modulo2_TS/solicitud/solicitud.schema';
import { entregaTypeDefs } from './modulo2_TS/entrega/entrega.schema';
import { direccionTypeDefs } from './modulo2_TS/direccion/direccion.schema';
import { historialReceptorTypeDefs } from './modulo2_TS/historial-receptor/historial-receptor.schema';

// Entidades del módulo 3 python
import { typeDefs as Administrador } from './modulo3_PY/administrador/administrador.schema'
import { typeDefs as Agendas } from './modulo3_PY/agenda/agenda.schema';
import   { typeDefs as Eventos} from './modulo3_PY/evento/evento.schema';
import { typeDefs as TipoEvento } from './modulo3_PY/tipoevento/tipoevento.schema';
import { typeDefs as RevisionSolicitud } from './modulo3_PY/revisionsolicitud/revisionsolicitud.schema';

export const typeDefs = mergeTypeDefs([
  Donante,
  Donacion,
  Producto,
  TipoProducto,
  EstadoDonacion,
  receptorTypeDefs,
  solicitudTypeDefs,
  entregaTypeDefs,
  direccionTypeDefs,
  historialReceptorTypeDefs,
  Administrador,
  Agendas,
  Eventos,
  TipoEvento,
  RevisionSolicitud
]);
