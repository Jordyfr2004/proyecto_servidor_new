from app.Domain.Interfaces.eventointerfaz.eventointerfaz import EventoInterface

class GetEventoUseCase:
    def __init__(self, evento_repository: EventoInterface):
        self.evento_repository = evento_repository

    def execute(self):
        return self.evento_repository.get_all()