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
        return self.evento_repository.create(nuevo_evento)