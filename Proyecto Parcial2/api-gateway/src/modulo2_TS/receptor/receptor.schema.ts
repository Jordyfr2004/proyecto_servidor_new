import { gql } from 'apollo-server';

export const receptorTypeDefs = gql`
  type Receptor {
    id: ID!
    nombre: String!
    cedula: String!
    telefono: String
    correo: String
    direccion: String
  }

  input ReceptorInput {
    nombre: String!
    cedula: String!
    telefono: String
    correo: String
    direccion: String
  }

  type Query {
    receptores: [Receptor]
    receptor(id: ID!): Receptor
  }

  type Mutation {
    crearReceptor(input: ReceptorInput): Receptor
    actualizarReceptor(id: ID!, input: ReceptorInput): Receptor
    eliminarReceptor(id: ID!): String
  }
`;
