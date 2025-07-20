import { mergeTypeDefs } from '@graphql-tools/merge';

// Tus entidades del módulo 1
import { typeDefs as Donante } from './modulo1_TS/donante/donante.schema';
import { typeDefs as Donacion } from './modulo1_TS/donacion/donacion.schema';
import { typeDefs as Producto } from './modulo1_TS/producto/producto.schema';
import { typeDefs as TipoProducto } from './modulo1_TS/tipo-producto/tipo-producto.schema';
import { typeDefs as EstadoDonacion } from './modulo1_TS/estado-donacion/estado-donacion.schema';

export const typeDefs = mergeTypeDefs([
  Donante,
  Donacion,
  Producto,
  TipoProducto,
  EstadoDonacion
]);
