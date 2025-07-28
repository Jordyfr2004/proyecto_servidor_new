import * as dotenv from 'dotenv';
dotenv.config();


// --- INICIO CAMBIO: Integración Express + WebSocket para notificaciones ---
import express from 'express';
import http from 'http';
// --- INICIO CAMBIO: Usar archivo dedicado para WebSocket y notificaciones ---
import { inicializarWebSocket } from './notificaciones';
// --- FIN CAMBIO ---
import { ApolloServer } from 'apollo-server-express';
import { typeDefs } from './schema';
import { resolvers } from './resolvers';


const app = express();
app.use(express.json()); // Permite recibir JSON en el body
const serverHttp = http.createServer(app);
// --- INICIO CAMBIO: Endpoint /notificar para recibir POST y emitir notificación ---
import { emitirNotificacionWebSocket } from './notificaciones';

app.post('/notificar', (req, res) => {
  // Puedes personalizar la entidad según lo que envíe el microservicio
  // --- FORMATO CORRECTO PARA EL CLIENTE WEBSOCKET ---
  // El cliente espera un objeto con: evento, mensaje y fecha
  const entidad = req.body.entidad || 'entrega';
  const mensaje = req.body.mensaje || 'Nueva notificación';
  const fecha = new Date().toISOString();
  emitirNotificacionWebSocket({ evento: entidad, mensaje, fecha });
  // Ejemplo del objeto enviado:
  // {
  //   evento: 'entrega',
  //   mensaje: 'Nueva notificación',
  //   fecha: '2025-07-27T21:34:00.000Z'
  // }
  console.log('📩 Notificación recibida:', entidad, mensaje, fecha);
  res.status(200).json({ ok: true });
});
// --- FIN CAMBIO ---


// Inicializar WebSocket usando el archivo dedicado
inicializarWebSocket(serverHttp);

// ApolloServer sobre Express
const apolloServer = new ApolloServer({ typeDefs, resolvers });
apolloServer.start().then(() => {
  // @ts-ignore
  apolloServer.applyMiddleware({ app });
  const PORT = process.env.PORT || 4000;
  serverHttp.listen(PORT, () => {
    console.log(`🚀 Servidor GraphQL listo en http://localhost:${PORT}${apolloServer.graphqlPath}`);
    console.log(`🔔 WebSocket listo en ws://localhost:${PORT}`);
  });
});
// --- FIN CAMBIO: Integración Express + WebSocket ---
