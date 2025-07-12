from app.Domain.Interfaces.eventointerfaz.eventointerfaz import EventoInterface

class DeleteEventoUseCase:
    def __init__(self, evento_repository: EventoInterface):
        self.evento_repository = evento_repository

    def execute(self, evento_id: int) -> bool:
        return self.evento_repository.delete(evento_id)