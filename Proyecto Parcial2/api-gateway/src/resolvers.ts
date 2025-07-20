import { mergeResolvers } from '@graphql-tools/merge';

// Tus resolvers del módulo 1
import { resolvers as DonanteResolvers } from './modulo1_TS/donante/donante.resolver';
import { resolvers as DonacionResolvers } from './modulo1_TS/donacion/donacion.resolver';
import { resolvers as ProductoResolvers } from './modulo1_TS/producto/producto.resolver';
import { resolvers as TipoProductoResolvers } from './modulo1_TS/tipo-producto/tipo-producto.resolver';
import { resolvers as EstadoDonacionResolvers } from './modulo1_TS/estado-donacion/estado-donacion.resolver';

export const resolvers = mergeResolvers([
  DonanteResolvers,
  DonacionResolvers,
  ProductoResolvers,
  TipoProductoResolvers,
  EstadoDonacionResolvers
]);
