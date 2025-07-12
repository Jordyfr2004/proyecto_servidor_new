from app.Domain.Interfaces.eventointerfaz.eventointerfaz import EventoInterface
from app.Domain.entities.evento.eventoEntitie import Evento


class GetEventoByIdUseCase:
    def __init__(self, evento_repository: EventoInterface):
        self.evento_repository = evento_repository

    def execute(self, evento_id: int) -> Evento:
        return self.evento_repository.get_by_id(evento_id)