import { gql } from 'apollo-server';

export const historialReceptorTypeDefs = gql`
  type HistorialReceptor {
    id: ID!
    fechaEntrega: String!
    observacion: String!
    idReceptor: ID!
  }

  input HistorialReceptorInput {
    fechaEntrega: String!
    observacion: String!
    idReceptor: ID!
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
