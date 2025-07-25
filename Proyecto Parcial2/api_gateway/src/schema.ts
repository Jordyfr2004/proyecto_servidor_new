import { mergeTypeDefs } from '@graphql-tools/merge';

// Tus entidades del módulo 1
import { typeDefs as Donante } from './modulo1_TS/donante/donante.schema';
import { typeDefs as Donacion } from './modulo1_TS/donacion/donacion.schema';
import { typeDefs as Producto } from './modulo1_TS/producto/producto.schema';
import { typeDefs as TipoProducto } from './modulo1_TS/tipo-producto/tipo-producto.schema';
import { typeDefs as EstadoDonacion } from './modulo1_TS/estado-donacion/estado-donacion.schema';


// Entidades del módulo 2 Java
import { typeDefs as Receptor } from './modulo2_Java/receptor/receptor.schema';
import { typeDefs as Direccion } from './modulo2_Java/direccion/direccion.schema';
import { typeDefs as Entrega } from './modulo2_Java/entrega/entrega.schema';
import { typeDefs as Solicitud } from './modulo2_Java/solicitud/solicitud.schema';
import { typeDefs as HistorialReceptor } from './modulo2_Java/historial-receptor/historial-receptor.schema';

// Entidades del módulo 3 python
import { typeDefs as Administrador } from './modulo3_PY/administrador/administrador.schema'
import { typeDefs as Agendas } from './modulo3_PY/agenda/agenda.schema';
import   { typeDefs as Eventos} from './modulo3_PY/evento/evento.schema';
import { typeDefs as TipoEvento } from './modulo3_PY/tipoevento/tipoevento.schema';
import { typeDefs as RevisionSolicitud } from './modulo3_PY/revisionsolicitud/revisionsolicitud.schema';

export const typeDefs = mergeTypeDefs([
  // Módulo 1
  Donante,
  Donacion,
  Producto,
  TipoProducto,
  EstadoDonacion,
  // Módulo 2
  Receptor,
  Direccion,
  Entrega,
  Solicitud,
  HistorialReceptor,
  // Módulo 3
  Administrador,
  Agendas,
  Eventos,
  TipoEvento,
  RevisionSolicitud
]);
