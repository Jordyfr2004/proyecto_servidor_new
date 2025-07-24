import { gql } from 'apollo-server';

export const typeDefs = gql`
  type Donacion {
    id: ID!
    fecha: String!
    cantidad: Float!
    descripcion: String
  }

  type Donante {
    id: ID!
    nombre: String!
    tipo: String!
    correo: String!
    telefono: String
    direccion: String
    fecha_registro: String
    donaciones: [Donacion]
  }

  input CrearDonanteInput {
    nombre: String!
    tipo: String!
    correo: String!
    telefono: String!
    direccion: String!
    fecha_registro: String!
  }

  input UpdateDonanteInput {
    nombre: String
    tipo: String
    correo: String
    telefono: String
    direccion: String
    fecha_registro: String
  }

  type Query {
    donantes: [Donante]
    getDonante(id: ID!): Donante
  }

  type Mutation {
    crearDonante(input: CrearDonanteInput!): Donante
    updateDonante(id: ID!, input: UpdateDonanteInput!): Donante
    deleteDonante(id: ID!): String
  }
`;
