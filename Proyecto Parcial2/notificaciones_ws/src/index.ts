// src/index.ts

import express, { Request, Response } from 'express';
import { WebSocketServer, WebSocket } from 'ws'; // Importamos clase y tipo
import * as http from 'http';
import cors from 'cors';

const app = express();
const server = http.createServer(app);
const wss = new WebSocketServer({ server });

app.use(cors());
app.use(express.json());

// Almacenar los clientes conectados
const clients: Set<WebSocket> = new Set();

// WebSocket: cuando un cliente se conecta
wss.on('connection', (ws: WebSocket) => {
  console.log('🟢 Cliente WebSocket conectado');
  clients.add(ws);

  ws.on('close', () => {
    clients.delete(ws);
    console.log('🔴 Cliente WebSocket desconectado');
  });
});

// Ruta para recibir notificaciones desde otros módulos
app.post('/notificar', (req: Request, res: Response) => {
  const { evento, mensaje, fecha } = req.body;
  const payload = JSON.stringify({ evento, mensaje, fecha });

  clients.forEach((client: WebSocket) => {
    client.send(payload);
  });

  res.status(200).json({ status: 'ok', enviado_a: clients.size });
});

// Iniciar servidor
const PORT = 3001;
server.listen(PORT, () => {
  console.log(`🚀 Servidor WebSocket escuchando en http://localhost:${PORT}`);
});
