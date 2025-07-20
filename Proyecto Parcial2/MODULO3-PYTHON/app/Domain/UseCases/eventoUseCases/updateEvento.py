from app.Domain.Dtos.createEventoDto.updateEventoDto import UpdateEventoDTO
from app.Domain.entities.evento.eventoEntitie import Evento
from app.Domain.Interfaces.eventointerfaz.eventointerfaz import EventoInterface


class UpdateEventoUseCase:
    def __init__(self, evento_repository: EventoInterface):
        self.evento_repository = evento_repository

    def execute(self, dto: UpdateEventoDTO ) -> Evento:
        actualizado = Evento(
            id=dto.id,
            descripcion=dto.descripcion, 
            tipo_evento_id= dto.tipo_evento_id)
        return self.evento_repository.update(actualizado)