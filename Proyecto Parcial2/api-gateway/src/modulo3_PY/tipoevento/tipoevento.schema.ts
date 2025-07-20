import { gql } from "apollo-server";


export const typeDefs = gql`
  type Administrador {
  id: ID!
  nombre: String!
  usuario: String!
  correo: String!
}

type TipoEvento {
  id: ID!
  tipo: String!
  admin_id: ID!
}

input CreateTipoEventoInput {
  tipo: String!
  admin_id: ID!
}

input UpdateTipoEventoInput {
  tipo: String
  admin_id: ID
}

type Query {
  TipoEventos: [TipoEvento]
  getTipoEvento(id: ID!): TipoEvento
}

type Mutation {
  createTipoEvento(input: CreateTipoEventoInput!): TipoEvento
  updateTipoEvento(id: ID!, input: UpdateTipoEventoInput!): TipoEvento
  deleteTipoEvento(id: ID!): String
}

`;

