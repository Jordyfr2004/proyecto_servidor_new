import { gql } from 'apollo-server';

export const entregaTypeDefs = gql`
  type Entrega {
    id: ID!
    fechaEntrega: String!
    observacion: String!
    idSolicitud: ID!
    idDonacion: ID!
  }

  input EntregaInput {
    fechaEntrega: String!
    observacion: String!
    idSolicitud: ID!
    idDonacion: ID!
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
