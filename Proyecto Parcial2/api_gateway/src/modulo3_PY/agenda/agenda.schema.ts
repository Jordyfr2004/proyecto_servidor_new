import { gql } from "apollo-server";

export const typeDefs = gql`
  type Administrador {
    id: ID!
    nombre: String!
    usuario: String!
    correo: String!
  }

  type Agendas {
    id: ID!
    titulo: String!
    descripcion: String
    fecha: String
    admin_id: ID!
    
  }

  input CreateAgendasInput {
    titulo: String!
    descripcion: String
    fecha: String!
    admin_id: ID!
  }

  input UpdateAgendasInput {
    titulo: String
    descripcion: String
    fecha: String
    admin_id: ID!
  }
    
  type Query {
    Agendas: [Agendas]
    getAgendas(id: ID!): Agendas
  }

  type Mutation {
    createAgendas(input: CreateAgendasInput!): Agendas
    updateAgendas(id: ID!, input: UpdateAgendasInput!): Agendas
    deleteAgendas (id: ID!): String
  }

`;