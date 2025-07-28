import requests
from datetime import datetime
from app.Domain.Dtos.createAgendaDto.createAgendaDto import CreateAgendaDTO
from app.Domain.Interfaces.agendainterfaz.agendainterfaz import AgendaInterface
from app.Domain.entities.agenda.agendaEntitie import Agenda

class CreateAgendaUseCase:
    def __init__(self, agenda_repository: AgendaInterface ):
        self.agenda_repository = agenda_repository

    def execute(self, dto: CreateAgendaDTO) -> Agenda:
        nueva_agenda= Agenda(
            titulo=dto.titulo,
            descripcion=dto.descripcion,
            fecha=dto.fecha,
            admin_id =dto.admin_id
        )
        agenda_creada = self.agenda_repository.create(nueva_agenda)

        # 👇 Enviar notificación al WebSocket
        try:
            requests.post('http://localhost:3001/notificar', json={
                'evento': 'nueva_agenda',
                'mensaje': f'Nueva entrada en agenda creada: {agenda_creada.titulo}',
                'fecha': datetime.now().isoformat()
            })
        except Exception as error:
            print(f'Error al enviar notificación WebSocket: {str(error)}')

        return agenda_creada