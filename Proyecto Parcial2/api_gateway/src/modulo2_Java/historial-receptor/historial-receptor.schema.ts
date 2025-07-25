import { gql } from 'apollo-server';

export const typeDefs = gql`
  type HistorialReceptor {
    id: ID!
    fechaEntrega: String!
    observacion: String!
    fechaRegistro: String
    fechaActualizacion: String
    tipoEvento: String
    estado: String
    detalleAdicional: String
    cantidadEntregada: Float
    unidad: String
    valorEstimado: Float
    usuarioRegistrador: String
    idReceptor: ID!
    idDonacion: ID
    idEntrega: ID
  }

  input HistorialReceptorInput {
    fechaEntrega: String!
    observacion: String!
    fechaRegistro: String
    fechaActualizacion: String
    tipoEvento: String
    estado: String
    detalleAdicional: String
    cantidadEntregada: Float
    unidad: String
    valorEstimado: Float
    usuarioRegistrador: String
    idReceptor: ID!
    idDonacion: ID
    idEntrega: ID
  }

  type Query {
    historialesReceptor: [HistorialReceptor]
    historialReceptor(id: ID!): HistorialReceptor
  }

  type Mutation {
    crearHistorialReceptor(input: HistorialReceptorInput): HistorialReceptor
    actualizarHistorialReceptor(id: ID!, input: HistorialReceptorInput): HistorialReceptor
    eliminarHistorialReceptor(id: ID!): String
  }
`;
