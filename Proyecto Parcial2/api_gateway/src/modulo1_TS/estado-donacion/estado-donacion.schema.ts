import { gql } from 'apollo-server';

export const typeDefs = gql`
  type EstadoDonacion {
    id: ID!
    nombre: String!
    descripcion: String!
  }

  input CrearEstadoDonacionInput {
    nombre: String!
    descripcion: String!
  }

  input UpdateEstadoDonacionInput {
    nombre: String
    descripcion: String
  }

  type Query {
    estadosDonacion: [EstadoDonacion]
    getEstadoDonacion(id: ID!): EstadoDonacion
  }

  type Mutation {
    crearEstadoDonacion(input: CrearEstadoDonacionInput!): EstadoDonacion
    updateEstadoDonacion(id: ID!, input: UpdateEstadoDonacionInput!): EstadoDonacion
    deleteEstadoDonacion(id: ID!): String
  }
`;
