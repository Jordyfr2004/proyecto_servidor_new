import requests
from datetime import datetime
from app.Domain.Dtos.createEventoDto.createEventoDto import CreateEventoDTO
from app.Domain.entities.evento.eventoEntitie import Evento
from app.Domain.Interfaces.eventointerfaz.eventointerfaz import EventoInterface


class CreateEventoUseCase:
    def __init__(self, evento_repository: EventoInterface):
        self.evento_repository = evento_repository

    def execute(self, dto: CreateEventoDTO) -> Evento:
        nuevo_evento = Evento(
            descripcion=dto.descripcion, 
            tipo_evento_id= dto.tipo_evento_id)
        evento_creado = self.evento_repository.create(nuevo_evento)

        # 👇 Enviar notificación al WebSocket
        try:
            requests.post('http://localhost:3001/notificar', json={
                'evento': 'nuevo_evento',
                'mensaje': f'Nuevo evento registrado: {evento_creado.descripcion}',
                'fecha': datetime.now().isoformat()
            })
        except Exception as error:
            print(f'Error al enviar notificación WebSocket: {str(error)}')

        return evento_creado