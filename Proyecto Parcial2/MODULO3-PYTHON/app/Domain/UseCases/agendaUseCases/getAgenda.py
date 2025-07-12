from app.Domain.Interfaces.agendainterfaz.agendainterfaz import AgendaInterface

class GetAgendaUseCase:
    def __init__(self, agenda_repository: AgendaInterface):
        self.agenda_repository = agenda_repository

    def execute(self):
        return self.agenda_repository.get_all()