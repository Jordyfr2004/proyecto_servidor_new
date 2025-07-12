from app.Domain.Interfaces.agendainterfaz.agendainterfaz import AgendaInterface
from app.Domain.entities.agenda.agendaEntitie import Agenda

class GetAgendaByIdUseCase:
    def __init__(self, agenda_repository: AgendaInterface):
        self.agenda_repository = agenda_repository

    def execute(self, agenda_id:int) -> Agenda:
        return self.agenda_repository.get_by_id(agenda_id)