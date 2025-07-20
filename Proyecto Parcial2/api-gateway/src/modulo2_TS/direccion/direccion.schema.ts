import { gql } from 'apollo-server';

export const direccionTypeDefs = gql`
  type Direccion {
    id: ID!
    calle: String!
    ciudad: String!
    provincia: String!
    referencia: String!
    idReceptor: ID!
  }

  input DireccionInput {
    calle: String!
    ciudad: String!
    provincia: String!
    referencia: String!
    idReceptor: ID!
  }

  type Query {
    direcciones: [Direccion]
    direccion(id: ID!): Direccion
  }

  type Mutation {
    crearDireccion(input: DireccionInput): Direccion
    actualizarDireccion(id: ID!, input: DireccionInput): Direccion
    eliminarDireccion(id: ID!): String
  }
`;
