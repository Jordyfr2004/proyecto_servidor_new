import { gql } from "apollo-server";

export const typeDefs = gql`
  type Administrador {
    id: ID!
    nombre: String!
    usuario: String!
    correo: String!
  }

  type Evento {
    id: ID!
    descripcion: String
    tipo_evento_id: ID!
  }

   input CreateEventoInput {
    descripcion: String
    tipo_evento_id: ID!
  }

    input UpdateEventoInput {
    descripcion: String
    tipo_evento_id: ID!
    }

    type Query {
    Eventos: [Evento]
    getEvento(id: ID!): Evento
    }

    type Mutation {
    createEvento(input: CreateEventoInput!): Evento
    updateEvento(id: ID!, input: UpdateEventoInput!): Evento
    deleteEvento(id: ID!): String
    }

`;