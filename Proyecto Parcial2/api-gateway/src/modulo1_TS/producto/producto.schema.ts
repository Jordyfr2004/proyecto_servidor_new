import { gql } from 'apollo-server';

export const typeDefs = gql`
  type TipoProducto {
    id: ID!
    nombre: String!
    descripcion: String!
  }

  type Producto {
    id: ID!
    nombre: String!
    categoria: String!
    peso: Float!
    unidad_medida: String!
    stock: Int!
    tipo: TipoProducto
  }

  input CrearProductoInput {
    nombre: String!
    categoria: String!
    peso: Float!
    unidad_medida: String!
    stock: Int!
    id_tipo: Int!
  }

  input UpdateProductoInput {
    nombre: String
    categoria: String
    peso: Float
    unidad_medida: String
    stock: Int
    id_tipo: Int
  }

  type Query {
    productos: [Producto]
    getProducto(id: ID!): Producto
  }

  type Mutation {
    crearProducto(input: CrearProductoInput!): Producto
    updateProducto(id: ID!, input: UpdateProductoInput!): Producto
    deleteProducto(id: ID!): String
  }
`;
