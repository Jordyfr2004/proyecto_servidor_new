import { gql } from "apollo-server";

export const typeDefs = gql`

  type Agendas {
    id: ID!
    titulo: String!
    descripcion: String
    fecha: String
  }

  type Administrador {
    id: ID!
    nombre: String!
    usuario: String!
    correo: String!
    agendas: [Agendas]
  }

  input CreateAdministradorInput {
    nombre: String!
    usuario: String!
    correo: String!
    password: String!
  }


  input UpdateAdministradorInput {
    nombre: String
    usuario: String 
    correo: String
    password: String
  }

  type Query {
    Administradores: [Administrador]
    getAdministrador(id: ID!): Administrador
  }

  type Mutation {
    createAdministrador(input: CreateAdministradorInput!): Administrador
    updateAdministrador(id: ID!, input: UpdateAdministradorInput!): Administrador
    deleteAdministrador(id: ID!): String
  }
`;