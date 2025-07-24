import { gql } from 'apollo-server';

export const typeDefs = gql`
  type TipoProducto {
    id: ID!
    nombre: String!
    descripcion: String!
  }

  input CrearTipoProductoInput {
    nombre: String!
    descripcion: String!
  }

  input UpdateTipoProductoInput {
    nombre: String
    descripcion: String
  }

  type Query {
    tiposProducto: [TipoProducto]
    getTipoProducto(id: ID!): TipoProducto
  }

  type Mutation {
    crearTipoProducto(input: CrearTipoProductoInput!): TipoProducto
    updateTipoProducto(id: ID!, input: UpdateTipoProductoInput!): TipoProducto
    deleteTipoProducto(id: ID!): String
  }
`;
