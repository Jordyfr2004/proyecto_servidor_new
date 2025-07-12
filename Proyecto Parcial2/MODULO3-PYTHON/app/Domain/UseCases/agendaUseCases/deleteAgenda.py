from app.Domain.Interfaces.agendainterfaz.agendainterfaz import AgendaInterface

class DeleteAgendaUseCase:
    def __init__(self, agenda_repository: AgendaInterface):
        self.agenda_repository = agenda_repository

    def execute(self, agenda_id: int) -> bool:
        return self.agenda_repository.delete(agenda_id)