import { gql } from 'apollo-server';

export const typeDefs = gql`
  type Receptor {
    id: ID!
    nombre: String!
    cedula: String!
    telefono: String!
    direccion: String!
    correo: String!
    idsDirecciones: [ID]
    idsSolicitudes: [ID]
    idsHistorial: [ID]
  }

  input ReceptorInput {
    nombre: String!
    cedula: String!
    telefono: String!
    direccion: String!
    correo: String!
    idsDirecciones: [ID]
    idsSolicitudes: [ID]
    idsHistorial: [ID]
  }

  type Query {
    testConnection: String
    receptores: [Receptor]
    receptor(id: ID!): Receptor
  }

  type Mutation {
    crearReceptor(input: ReceptorInput): Receptor
    actualizarReceptor(id: ID!, input: ReceptorInput): Receptor
    eliminarReceptor(id: ID!): String
  }
`;
