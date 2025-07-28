// --- INICIO CAMBIO: Archivo dedicado para WebSocket y notificaciones ---
import { Server as WebSocketServer } from 'ws';
import http from 'http';

let wss: WebSocketServer;
const wsClients: Set<any> = new Set();

export function inicializarWebSocket(serverHttp: http.Server) {
  wss = new WebSocketServer({ server: serverHttp });
  wss.on('connection', (ws) => {
    wsClients.add(ws);
    ws.on('close', () => wsClients.delete(ws));
  });
}

export function emitirNotificacionWebSocket(data: any) {
  const payload = JSON.stringify(data);
  wsClients.forEach((client) => {
    if (client.readyState === 1) {
      client.send(payload);
    }
  });
}
// --- FIN CAMBIO: Archivo dedicado para WebSocket y notificaciones ---
