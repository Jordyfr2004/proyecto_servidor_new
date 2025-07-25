import { gql } from 'apollo-server';

export const typeDefs = gql`
  type Donante {
    id: ID!
    nombre: String!
    correo: String!
    telefono: String
  }

  type Producto {
    id: ID!
    nombre: String!
    descripcion: String
    id_tipo: Int
  }

  type EstadoDonacion {
    id: ID!
    nombre: String!
  }

  type Donacion {
    id: ID!
    fecha: String!
    cantidad: Float!
    descripcion: String
    donante: Donante
    productos: [Producto]
    estado: EstadoDonacion
  }

  input CrearDonacionInput {
    fecha: String!
    cantidad: Float!
    descripcion: String
    id_donante: Int!
    productos_ids: [Int!]!   # ✅ CAMBIADO AQUÍ
    id_estado: Int!
  }

  input UpdateDonacionInput {
    fecha: String
    cantidad: Float
    descripcion: String
    id_donante: Int
    productos_ids: [Int!]   # ✅ CAMBIADO AQUÍ TAMBIÉN
    id_estado: Int
  }

  type Query {
    donaciones: [Donacion]
    getDonacion(id: ID!): Donacion
  }

  type Mutation {
    crearDonacion(input: CrearDonacionInput!): Donacion
    updateDonacion(id: ID!, input: UpdateDonacionInput!): Donacion
    deleteDonacion(id: ID!): String
  }
`;
