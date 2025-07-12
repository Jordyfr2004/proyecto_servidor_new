from app.Domain.entities.evento.eventoEntitie import Evento
from app.Domain.Interfaces.eventointerfaz.eventointerfaz import EventoInterface


class UpdateEventoUseCase:
    def __init__(self, evento_repository: EventoInterface):
        self.evento_repository = evento_repository

    def execute(self, evento_id: int, descripcion: str, id_tipo_evento: int) -> Evento:
        actualizado = Evento(id=evento_id, descripcion=descripcion, id_tipo_evento=id_tipo_evento)
        return self.evento_repository.update(actualizado)