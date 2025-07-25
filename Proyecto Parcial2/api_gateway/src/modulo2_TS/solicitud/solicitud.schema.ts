import { gql } from 'apollo-server';

export const solicitudTypeDefs = gql`
  type Solicitud {
    id: ID!
    tipoDonacion: String!
    descripcion: String!
    fechaSolicitud: String!
    estado: String!
    idReceptor: ID!
  }

  input SolicitudInput {
    tipoDonacion: String!
    descripcion: String!
    fechaSolicitud: String!
    estado: String!
    idReceptor: ID!
  }

  type Query {
    solicitudes: [Solicitud]
    solicitud(id: ID!): Solicitud
  }

  type Mutation {
    crearSolicitud(input: SolicitudInput): Solicitud
    actualizarSolicitud(id: ID!, input: SolicitudInput): Solicitud
    eliminarSolicitud(id: ID!): String
  }
`;
