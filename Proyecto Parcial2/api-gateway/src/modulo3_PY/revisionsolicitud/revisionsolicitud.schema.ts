import { gql } from "apollo-server";

export const typeDefs = gql`
  type Administrador {
    id: ID!
    nombre: String!
    usuario: String!
    correo: String!
  }

  type RevisionSolicitud {
    id: ID!
    estado_revision: String!
    observacion: String
    admin_id: ID!
  }

  input CreateRevisionSolicitudInput {
    estado_revision: String!
    observacion: String
    admin_id: ID!
  }

  input UpdateRevisionSolicitudInput {
    estado_revision: String!
    observacion: String
    admin_id: ID!
  }

  type Query {
    RevisionSolicitud: [RevisionSolicitud]
    getRevisionSolicitud(id: ID!): RevisionSolicitud
  }

  type Mutation {
    createRevisionSolicitud(input: CreateRevisionSolicitudInput!): RevisionSolicitud
    updateRevisionSolicitud(id: ID!, input: UpdateRevisionSolicitudInput!): RevisionSolicitud
    deleteRevisionSolicitud(id: ID!): String
  }
`;