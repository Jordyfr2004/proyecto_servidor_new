import { gql } from 'apollo-server';

export const typeDefs = gql`
  type Entrega {
    id: ID!
    fechaEntrega: String!
    observacion: String!
    cantidadEntregada: Float
    unidad: String
    estadoEntrega: String
    idSolicitud: ID!
    idDonacion: ID!
    idsHistorialEntregas: [ID]
  }

  input EntregaInput {
    fechaEntrega: String!
    observacion: String!
    cantidadEntregada: Float
    unidad: String
    estadoEntrega: String
    idSolicitud: ID!
    idDonacion: ID!
    idsHistorialEntregas: [ID]
  }

  type Query {
    entregas: [Entrega]
    entrega(id: ID!): Entrega
  }

  type Mutation {
    crearEntrega(input: EntregaInput): Entrega
    actualizarEntrega(id: ID!, input: EntregaInput): Entrega
    eliminarEntrega(id: ID!): String
  }
`;
